package main.java.com.right.github.server;


import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.IntegerObject;
import main.java.com.right.github.core.SelectableItem;
import main.java.com.right.github.core.StringObject;
import main.java.com.right.github.shared.packet.*;
import main.java.com.right.github.server.game.Game;
import main.java.com.right.github.shared.Logs;

import java.util.Objects;
import java.util.Queue;

public class Server {
    private ExitPacket exitPacket;
    private Queue<Packet> packetsServerToClient;
    private Queue<Packet> packetsClientToServer;
    private boolean isRunning;

    private EnumServerType serverType = EnumServerType.GAME;

    private Game game;

    private int temp = 0;
    private final IntegerObject responseChoosingItem = new IntegerObject(-1);
    private final IntegerObject responseSelectingBlockPos = new IntegerObject(-1);
    private final StringObject responseInputtingText = new StringObject("");

    SelectableItem[] selectableItems = new SelectableItem[5];


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
            pPacketsToSend.add(new ClientModeSendDataPacket.ChoosingItem(selectableItems, responseChoosingItem));
            temp = 61;
            System.out.println("check");
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
            } else if (currentPacket instanceof ClientModeSendDataPacket.Response()) {
                if (responseChoosingItem.getValue() == 4){
                    responseChoosingItem.setValue(-1);
                    packetsServerToClient.add(new ClientModeSendDataPacket.InputtingText(responseInputtingText));
                    packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.INPUTTING_TEXT_MODE));
                }
                if (responseSelectingBlockPos.getValue() > 0){
                    int value = responseSelectingBlockPos.getValue();
                    responseSelectingBlockPos.setValue(-1);
                    if ((value & 1) > 0){
                        System.out.println("lmb");
                    } else if ((value & 1 << 1) > 0) {
                        System.out.println("mmb");
                    } else if ((value & 1 << 2) > 0) {
                        System.out.println("rmb");
                    }
                    value = value >> 6;
                    System.out.println("x" + (value % Configs.STAGE_WIDTH) + " y:" + (value / Configs.STAGE_WIDTH));
                }
                if (! Objects.equals(responseInputtingText.getContent(), "")){
                    System.out.println(responseInputtingText.getContent());
                    responseInputtingText.setContent("");
                }
            }
        }

    }

    public void cleanUP(){
        Logs.Successful("Server is Properly Stopped.");}

    public boolean isRunning(){return isRunning;}
}
