package main.java.com.right.github.client.ui;

import main.java.com.right.github.client.core.ClientPartCore;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.frame.MainFrame;
import main.java.com.right.github.client.ui.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.core.TextureManager;
import main.java.com.right.github.shared.packet.ExitPacket;
import main.java.com.right.github.world.level.block.state.BlockState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Queue;

public class ClientUI extends ClientPartCore {
    private MainFrame frame;
    private MainPanel panel;

    // Debug用背景
    private final BufferedImage BACKGROUND_LOADING = TextureManager.getTexture("background/loading.png");

    public ClientUI(ExitPacket exitPacket){
        frame = new MainFrame(exitPacket);
        panel =new MainPanel();

        frame.getContentPane().add(panel);

        frame.setBackground(Color.WHITE);	// windowの背景色設定
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setBounds(0, 0, Configs.SCREEN_WIDTH + 10, Configs.SCREEN_HEIGHT + 35);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Hoge");
        frame.setVisible(true);

        panel.drawBackground(BACKGROUND_LOADING);
    }

    @Override
    public void start(Queue<Packet> pAddressToSend) {
        super.start(pAddressToSend);
    }

    @Override
    public void update() {
        while (!getReceiveBox().isEmpty()){
            Packet packet = getReceiveBox().poll();
            if (packet instanceof reRenderBackGround reRenderBackGround){
                // Background レイヤーの上書き
                panel.drawBackground(reRenderBackGround.getBackGround());
            } else if(packet instanceof renderBackground renderBackground){
                // Background レイヤーに上から追加
                panel.drawBackground(renderBackground.getBackground());
            }else if (packet instanceof reRenderEntities reRenderEntities) {
                // 一つ一つ取り出してEntityレイヤーに描画
                panel.drawEntities(reRenderEntities.getEntities());
            } else if (packet instanceof reRenderBlocks reRenderBlocks) {
                panel.drawBlocks(reRenderBlocks.getBlockStates());
            }
        }

        if (frame.getFlagAction() < 0){
            sendPacket(new ClientNet.KeyInputChanged(frame.getFlagAction(), frame.getMouseX(), frame.getMouseY()));
            frame.checked();
        }

        panel.rewrite();
    }

    @Override
    public void end() {
        super.end();
    }

    // バックグラウンドを再描画
    public static class reRenderBackGround extends Packet{
        private final BufferedImage backGround;
        public reRenderBackGround(BufferedImage pBackGround){
            backGround = pBackGround;
        }
        public BufferedImage getBackGround() {return backGround;}
    }
    // バックグラウンドに追加
    public static class renderBackground extends Packet{
        private final BufferedImage background;
        public renderBackground(BufferedImage pBackground){
            background = pBackground;
        }
        public BufferedImage getBackground() {return background;}
    }
    // エンティティを再描画
    public static class reRenderEntities extends Packet{
        private final ArrayList<DisplayObjectData> entities;
        public reRenderEntities(ArrayList<DisplayObjectData> pEntities){
            entities = pEntities;
        }
        public ArrayList<DisplayObjectData> getEntities() {return entities;}
    }
    // ブロック(ステージ)を再描画
    public static class reRenderBlocks extends Packet{
        public final BlockState[][] blockStates;
        public reRenderBlocks(BlockState[][] pBlockStates){
            blockStates = pBlockStates;
        }
        public BlockState[][] getBlockStates() {return blockStates;}
    }
}
