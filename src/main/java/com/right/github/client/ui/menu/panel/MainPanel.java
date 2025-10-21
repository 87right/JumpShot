package main.java.com.right.github.client.ui.menu.panel;


import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.core.Configs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {
    private final ClientData clientData;

    private final BufferedImage LAYER_BACKGROUND; // ただの背景
    private final BufferedImage LAYER_ENTITIES; // 動的なモノ
    private final BufferedImage LAYER_BLOCKS; // 静的なモノ
    private final BufferedImage LAYER_PARTICLES; // より下位にかかるフィルタ ex.
    private final BufferedImage LAYER_UI; // ユーザーが画面上で操作するモノ ex. Menu
    private final BufferedImage LAYER_EFFECTS; // 主にUIにかかるフィルタ ex.ホバー時

    public final RenderManager renderManager;

    public MainPanel(ClientData clientData){
        super();
        this.clientData = clientData;
        this.LAYER_BACKGROUND = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.LAYER_ENTITIES = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.LAYER_BLOCKS = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.LAYER_PARTICLES = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.LAYER_UI = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.LAYER_EFFECTS = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        renderManager = new RenderManager(this);
    }

    public void paintComponent(Graphics g){
        int height = getHeight();
        int width = getWidth();
        if (height <= 0 || width <= 0){return;}
        if (clientData != null){clientData.screenWidth = width; clientData.screenHeight = height;}
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics screen = image.getGraphics();
        screen.drawImage(LAYER_BACKGROUND, 0, 0, width, height, this);
        screen.drawImage(LAYER_ENTITIES, 0, 0, width, height,  this);
        screen.drawImage(LAYER_BLOCKS, 0, 0, width, height,  this);
        screen.drawImage(LAYER_PARTICLES, 0, 0, width, height,  this);
        screen.drawImage(LAYER_UI, 0, 0, width, height,  this);
        screen.drawImage(LAYER_EFFECTS, 0, 0, width, height,  this);

        g.drawImage(image, 0, 0, this);
        screen.dispose();
    }
    public void rewrite(){
        paintComponent(getGraphics());
    }

    public BufferedImage getLayerBackground() {return LAYER_BACKGROUND;}
    public BufferedImage getLayerEntities() {return LAYER_ENTITIES;}
    public BufferedImage getLayerBlocks(){return LAYER_BLOCKS;}
    public BufferedImage getLayerParticles(){return LAYER_PARTICLES;}
    public BufferedImage getLayerUI(){return LAYER_UI;}
    public BufferedImage getLayerEffects(){return LAYER_EFFECTS;}
}
