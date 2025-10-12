package main.java.com.right.github.server;


import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.SelectableItem;
import main.java.com.right.github.shared.packet.*;
import main.java.com.right.github.server.game.Game;
import main.java.com.right.github.shared.Logs;

import java.util.Queue;

public class Server {
    private ExitPacket exitPacket;
    private boolean isRunning;

    private EnumServerType serverType = EnumServerType.GAME;

    private Game game;

    private int temp = 0;

    SelectableItem[] selectableItems = new SelectableItem[5];


    public Server(Queue<Packet> pPacketsToSend){
        isRunning = true;
        exitPacket = new ExitPacket();
        this.game = new Game(pPacketsToSend);
    }

    public void start(Queue<Packet> pPacketsToSend, EnumServerType pServerType) {
        serverType = pServerType;
        game.start(pPacketsToSend);

        selectableItems[0] = new SelectableItem("I", 1);
        selectableItems[1] = new SelectableItem("AM", 2);
        selectableItems[2] = new SelectableItem("AN", 3);
        selectableItems[3] = new SelectableItem("APPLE", 4);
        selectableItems[4] = new SelectableItem("WOOOW", 5);
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

        if (temp == 60){
            pPacketsToSend.add(new ClientModeRequestPacket(EnumUIModes.CHOOSING_ITEM_MODE));
            pPacketsToSend.add(new ClientModeSendDataPacket.ChoosingItem(selectableItems));
        } else if (temp < 60) {
            temp ++;
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
