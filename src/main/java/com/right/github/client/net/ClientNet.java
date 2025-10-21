package main.java.com.right.github.client.net;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.core.FloatObject;
import main.java.com.right.github.core.IntegerObject;
import main.java.com.right.github.core.SelectableItem;
import main.java.com.right.github.core.StringObject;
import main.java.com.right.github.shared.packet.ClientModeRequestPacket;
import main.java.com.right.github.shared.packet.*;

import java.util.Queue;

public class ClientNet{
    private final Queue<Packet> packetsClientToServer;
    private final Queue<Packet> packetsServerToClient;

    private final ExitPacket exitPacket;

    private ClientData clientData;

    public ClientNet(Queue<Packet> pPacketsClientToServer, Queue<Packet> pPacketsServerToClient, ExitPacket pExitPacket){
        super();
        packetsClientToServer = pPacketsClientToServer;
        packetsServerToClient = pPacketsServerToClient;

        exitPacket = pExitPacket;
    }


    public void start(ClientData clientData) {
        this.clientData = clientData;
    }

    public void update() {

        while (! packetsServerToClient.isEmpty()){
            Packet currentPacket = packetsServerToClient.poll();
            if (currentPacket instanceof ExitPacket){
                exitPacket.turnOnFlag();
            } else if (currentPacket instanceof LevelPacket levelPacket) {
                if (levelPacket instanceof LevelPacket.BlockStatesPacket blockStatesPacket){
                    if (blockStatesPacket instanceof LevelPacket.BlockStatesPacket.SendAllStatsPacket sendAllStatsPacket){
                        clientData.setBlockStates(sendAllStatsPacket.getBlockStates());
                    }
                }else if (levelPacket instanceof PlayerPacket.SendStatePacket sendStatePacket) {
                    clientData.addDisplayObjectData(sendStatePacket.getDisplayObjectData());
                } else if (levelPacket instanceof EntityPacket entityPacket) {
                    clientData.addDisplayObjectData(entityPacket.getDisplayObjectData());
                }

            } else if (currentPacket instanceof ClientModeSendDataPacket clientModeSendDataPacket) {
                if (clientModeSendDataPacket instanceof ClientModeSendDataPacket.ChoosingItem(
                        SelectableItem[] selectableItems,
                        IntegerObject responseAddress
                )){
                    clientData.setSelectableItems(selectableItems);
                    clientData.setResponseAddress(responseAddress);
                } else if (clientModeSendDataPacket instanceof ClientModeSendDataPacket.SelectingBlockPos(
                        IntegerObject responseAddress
                )) {
                    clientData.setSelectingBlockPosInput(responseAddress);
                } else if (clientModeSendDataPacket instanceof ClientModeSendDataPacket.InputtingText(
                        StringObject responseAddress
                )) {
                    clientData.setInputText(responseAddress);
                }else if (clientModeSendDataPacket instanceof ClientModeSendDataPacket.SelectingPos(
                        FloatObject x,
                        FloatObject y
                )) {
                    clientData.setSelectingPosXInput(x);
                    clientData.setSelectingPosYInput(y);
                }
            }
        }
        if (exitPacket.getFlag()){
            packetsClientToServer.add(exitPacket);
        }
    }

    public void end() {
    }

    public void keyInputChanged(int content, int mouseX, int mouseY){
        packetsClientToServer.add(new KeyInputsPacket(content, mouseX, mouseY));
    }
    public void response(){
        packetsClientToServer.add(new ClientModeSendDataPacket.Response());
    }
}
