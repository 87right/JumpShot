package main.java.com.right.github.client.net;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.shared.packet.*;
import main.java.com.right.github.world.level.StageLib;

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
                switch (levelPacket) {
                    case LevelPacket.BlockStatesPacket blockStatesPacket -> {
                        if (blockStatesPacket instanceof LevelPacket.BlockStatesPacket.SendAllStatsPacket sendAllStatsPacket) {
                            clientData.setBlockStates(sendAllStatsPacket.getBlockStates());
                        }
                    }
                    case PlayerPacket.SendStatePacket sendStatePacket -> {
                        clientData.clearDisplayObjectData();
                        clientData.addDisplayObjectData(sendStatePacket.getDisplayObjectData());
                    }
                    case EntityPacket entityPacket ->
                            clientData.addDisplayObjectData(entityPacket.getDisplayObjectData());
                    default -> {}
                }
            } else if (currentPacket instanceof StageClearPacket(boolean isLast)) {
                System.out.println("ClientNet.update Received Stage Clear Packet. is Last: "+ isLast);
                clientData.requestedMenu = 5;
                clientData.isFinished = isLast;
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

    public void sendStartRequest(String[] stageNames) {
        packetsClientToServer.add(new CSStartRequestPacket(stageNames));
    }
}
