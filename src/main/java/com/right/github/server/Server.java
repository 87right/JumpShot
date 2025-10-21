package main.java.com.right.github.server;


import main.java.com.right.github.shared.packet.*;
import main.java.com.right.github.server.game.Game;
import main.java.com.right.github.shared.Logs;

import java.util.Queue;

public class Server {
    private ExitPacket exitPacket;
    private Queue<Packet> packetsServerToClient;
    private Queue<Packet> packetsClientToServer;
    private boolean isRunning;

    private EnumServerType serverType = EnumServerType.GAME;

    private Game game;

    public Server(Queue<Packet> packetsServerToClient, Queue<Packet> packetsClientToServer){
        isRunning = true;
        exitPacket = new ExitPacket();
        this.packetsServerToClient =  packetsServerToClient;
        this.packetsClientToServer = packetsClientToServer;
        this.game = new Game(packetsServerToClient);
    }

    public void start(Queue<Packet> pPacketsToSend, EnumServerType pServerType) {
        serverType = pServerType;
        game.start(pPacketsToSend);
//        pPacketsToSend.add(new ClientModeRequestPacket(EnumUIModes.STAGE_MODE));
    }
    public void update(Queue<Packet> pReceivedPackets, Queue<Packet> pPacketsToSend){
        checkPackets(pReceivedPackets);

        if (serverType == EnumServerType.GAME){
            game.update(pPacketsToSend);
        }


        if (exitPacket.getFlag()){
            isRunning = false;
            pPacketsToSend.add(exitPacket);
        }
    }

    private void checkPackets(Queue<Packet> pPackets){
        while (! pPackets.isEmpty()){
            Packet currentPacket = pPackets.poll();
            if (currentPacket instanceof ExitPacket){
                cleanUP();
                isRunning = false;
            } else if (currentPacket instanceof KeyInputsPacket pKeyInputsPacket) {
                game.keyInputs(pKeyInputsPacket);
            }
        }

    }

    public void cleanUP(){
        Logs.Successful("Server is Properly Stopped.");}

    public boolean isRunning(){return isRunning;}
}
