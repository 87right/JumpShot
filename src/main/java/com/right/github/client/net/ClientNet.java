package main.java.com.right.github.client.net;

import main.java.com.right.github.client.core.ClientPartCore;
import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.shared.packet.*;

import java.util.Queue;

public class ClientNet extends ClientPartCore {
    private Queue<BasePacket> packetsClientToServer;
    private Queue<BasePacket> packetsServerToClient;

    private ExitPacket exitPacket;

    public ClientNet(Queue<BasePacket> pPacketsClientToServer, Queue<BasePacket> pPacketsServerToClient, ExitPacket pExitPacket){
        packetsClientToServer = pPacketsClientToServer;
        packetsServerToClient = pPacketsServerToClient;

        exitPacket = pExitPacket;
    }

    @Override
    public void start(Queue<Packet> pAddressToSend) {
        super.start(pAddressToSend);
    }

    @Override
    public void update() {
        super.update();

        while (! packetsServerToClient.isEmpty()){
            BasePacket currentPacket = packetsServerToClient.poll();
            if (currentPacket instanceof ExitPacket){
                exitPacket.turnOnFlag();
            } else if (currentPacket instanceof LevelPacket levelPacket) {
                if (levelPacket instanceof LevelPacket.BlockStatesPacket blockStatesPacket){
                    if (blockStatesPacket instanceof LevelPacket.BlockStatesPacket.SendAllStatsPacket sendAllStatsPacket){
                        sendPacket(new ClientData.UpdateAllBlockStates(sendAllStatsPacket.getBlockStates()));
                    }
                }else if (levelPacket instanceof PlayerPacket.SendStatePacket sendStatePacket) {
                    sendPacket(new ClientData.AddEntity(sendStatePacket.getDisplayObjectData()));
                }
            }
        }
        if (exitPacket.getFlag()){
            packetsClientToServer.add(exitPacket);
        }
        while (! getReceiveBox().isEmpty()){
            Packet packet = getReceiveBox().poll();
            if (packet instanceof KeyInputChanged keyInputChanged){
                packetsClientToServer.add(new KeyInputsPacket(keyInputChanged.getContent(), keyInputChanged.getMouseX(), keyInputChanged.getMouseY()));
            }
        }
    }

    @Override
    public void end() {
        super.end();
    }

    public static class KeyInputChanged extends Packet{
        private final int content;
        private final int mouseX;
        private final int mouseY;
        public KeyInputChanged(int content, int mouseX, int mouseY){
            this.content = content;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
        public int getContent() {return content;}
        public int getMouseX() {return mouseX;}
        public int getMouseY() {return mouseY;}
    }
}
