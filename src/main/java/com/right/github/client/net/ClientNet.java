package main.java.com.right.github.client.net;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.core.ClientModeRequestPacket;
import main.java.com.right.github.shared.packet.*;

import java.util.Queue;

public class ClientNet{
    private final Queue<BasePacket> packetsClientToServer;
    private final Queue<BasePacket> packetsServerToClient;

    private final ExitPacket exitPacket;

    private ClientData clientData;

    public ClientNet(Queue<BasePacket> pPacketsClientToServer, Queue<BasePacket> pPacketsServerToClient, ExitPacket pExitPacket){
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
            BasePacket currentPacket = packetsServerToClient.poll();
            if (currentPacket instanceof ExitPacket){
                exitPacket.turnOnFlag();
            } else if (currentPacket instanceof LevelPacket levelPacket) {
                if (levelPacket instanceof LevelPacket.BlockStatesPacket blockStatesPacket){
                    if (blockStatesPacket instanceof LevelPacket.BlockStatesPacket.SendAllStatsPacket sendAllStatsPacket){
                        clientData.setBlockStates(sendAllStatsPacket.getBlockStates());
                    }
                }else if (levelPacket instanceof PlayerPacket.SendStatePacket sendStatePacket) {
                    clientData.addDisplayObjectData(sendStatePacket.getDisplayObjectData());
                }
            } else if (currentPacket instanceof ClientModeRequestPacket clientModeRequestPacket) {
                clientData.requestUIMode(clientModeRequestPacket.getUiModes());
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
}
