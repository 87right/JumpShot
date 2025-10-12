package main.java.com.right.github.server;


import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.ClientModeRequestPacket;
import main.java.com.right.github.server.game.Game;
import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.shared.packet.BasePacket;
import main.java.com.right.github.shared.packet.ExitPacket;
import main.java.com.right.github.shared.packet.KeyInputsPacket;

import java.util.Queue;

public class Server {
    private ExitPacket exitPacket;
    private boolean isRunning;

    private EnumServerType serverType = EnumServerType.GAME;

    private Game game;

    private int temp = 0;

    public Server(Queue<BasePacket> pPacketsToSend){
        isRunning = true;
        exitPacket = new ExitPacket();
        this.game = new Game(pPacketsToSend);
    }

    public void start(Queue<BasePacket> pPacketsToSend, EnumServerType pServerType){
        serverType = pServerType;
        game.start(pPacketsToSend);
    }
    public void update(Queue<BasePacket> pReceivedPackets, Queue<BasePacket> pPacketsToSend){
        checkPackets(pReceivedPackets);

        if (serverType == EnumServerType.GAME){
            game.update(pPacketsToSend);
        }


        if (exitPacket.getFlag()){
            isRunning = false;
            pPacketsToSend.add(exitPacket);
        }

        if (temp == 60){
            pPacketsToSend.add(new ClientModeRequestPacket(EnumUIModes.STAGE_MODE));
        } else if (temp < 60) {
            temp ++;
        }
    }

    private void checkPackets(Queue<BasePacket> pPackets){
        while (! pPackets.isEmpty()){
            BasePacket currentPacket = pPackets.poll();
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
