package main.java.com.right.github.client;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.ClientUI;
//import main.java.com.right.github.client.ui.frame.MainFrame;
//import main.java.com.right.github.client.ui.panel.MainPanel;
//import main.java.com.right.github.core.Configs;
//import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.shared.packet.*;
//import main.java.com.right.github.world.level.block.Blocks;

//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
import java.util.Queue;

public class Client {
//    private MainFrame frame;
//    private MainPanel panel;
    private ExitPacket exitPacket;
//    private boolean isRunning;
//    private Insets insets;

//    private int timer = 0;
//
//    private int[][] blockTypes = new int[15][20];
//    private long[][] blockStates = new long[15][20];
//
//    private ArrayList<DisplayObjectData> entities = new ArrayList<>();

    // こっちに移行予定
    private final ClientNet clientNet;
    private final ClientData clientData;
    private final ClientUI clientUI;

    public Client(Queue<BasePacket> pPacketsClientToServer, Queue<BasePacket> pPacketsServerToClient){
//        isRunning = true;
        exitPacket = new ExitPacket();
//        MainFrame frame = new MainFrame(exitPacket);
//
//        panel =new MainPanel();
//        frame.getContentPane().add(panel);
//
//        frame.setBackground(Color.BLACK);	// windowの背景色設定
//        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        insets = frame.getInsets();
//        frame.setBounds(0, 0, Configs.SCREEN_WIDTH + 10, Configs.SCREEN_HEIGHT + 35);
//        frame.setLocationRelativeTo(null);
//        frame.setTitle("Hoge");
//        frame.setVisible(true);
//        this.frame = frame;

        // 移行予定
        clientNet = new ClientNet(pPacketsClientToServer, pPacketsServerToClient, exitPacket);
        clientData = new ClientData();
        clientUI = new ClientUI(exitPacket);
    }

    public void start(Queue<BasePacket> packetsClientToServer) {
        clientNet.start(clientData.getReceiveBox());
        clientData.start(clientUI.getReceiveBox());
        clientUI.start(clientNet.getReceiveBox());
    }
    public void update(Queue<BasePacket> pReceivedPackets, Queue<BasePacket> pPacketsToSend){
//        checkPackets(pReceivedPackets);
//        checkInputs(pPacketsToSend);
//        renderEntities();
//        panel.rewrite();


        clientNet.update();
        clientData.update();
        clientUI.update();

//        if (exitPacket.getFlag()){
//            isRunning = false;
//            pPacketsToSend.add(exitPacket);
//            Logs.Info("Client stopping...");
//        }
    }
    public void cleanUP(){
        clientNet.end();
        clientData.end();
        clientUI.end();
        Logs.Successful("Client is Properly Stopped.");
    }

//    private void checkPackets(Queue<BasePacket> pPackets){
//        while (! pPackets.isEmpty()){
//            BasePacket currentPacket = pPackets.poll();
//            if (currentPacket instanceof ExitPacket pExitPacket){
//                isRunning = false;
//            } else if (currentPacket instanceof LevelPacket levelPacket) {
//                if (levelPacket instanceof LevelPacket.BlockStatesPacket blockStatesPacket){
//                    if (blockStatesPacket instanceof LevelPacket.BlockStatesPacket.SendAllStatsPacket sendAllStatsPacket){
//                        blockTypes = sendAllStatsPacket.getTypes();
//                        blockStates = sendAllStatsPacket.getStates();
//                        renderBackground();
//                        renderEntities();
//                        renderStage();
//                    }
//                }else if (levelPacket instanceof PlayerPacket.SendStatePacket sendStatePacket) {
//                    entities.add(sendStatePacket.getDisplayObjectData());
//                    renderEntities();
//                }
//            }
//        }

//    }

//    private void checkInputs(Queue<BasePacket> pPacketsToSend){
//        pPacketsToSend.add(new KeyInputsPacket(frame.flagRight, frame.flagLeft, frame.flagJump, frame.flagShot, frame.mouseX, frame.mouseY));
//    }
//
//    private void renderBackGround(){
//        Graphics backGroundGraphics = panel.background.getGraphics();
//        backGroundGraphics.setColor(Color.WHITE);
//        backGroundGraphics.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
//    }
//    private void renderEntities(){
//        panel.entities = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
//        Graphics entitiesGraphics = panel.entities.getGraphics();
//
//        entitiesGraphics.setColor(Color.BLUE);
//        for (DisplayObjectData displayObjectData: entities){
//            timer ++;
//            entitiesGraphics.drawImage(displayObjectData.getImage(), (int) displayObjectData.getX(), (int) displayObjectData.getY(), displayObjectData.getWidth(), displayObjectData.getHeight(), frame);
//        }
//    }
//    private void renderStage(){
//        Graphics stageGraphics = panel.blocks.getGraphics();
//        stageGraphics.setColor(new Color(0, 0, 0, 0));
//        stageGraphics.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
//
//        stageGraphics.setColor(Color.BLACK);
//        for (int y = 0; y < Configs.STAGE_HEIGHT; y++) {
//            for (int x = 0; x < Configs.STAGE_WIDTH; x++) {
//                if (blockTypes[y][x] != 0){
//                    stageGraphics.drawImage(Blocks.blocks.get(blockTypes[y][x]).getTEXTURE(blockStates[y][x]), x * Configs.BLOCK_SIZE, y * Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, panel);
//                }
//            }
//        }
//    }


    public boolean isRunning(){return clientData.isRunning();}

}
