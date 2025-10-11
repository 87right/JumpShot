package main.java.com.right.github.client.ui.panel;


import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.world.level.block.Blocks;
import main.java.com.right.github.world.level.block.state.BlockState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainPanel extends JPanel {


    private BufferedImage background;
    public BufferedImage entities;
    public BufferedImage blocks;

    private final Graphics2D graBackground;
    private final Graphics2D graEntities;
    private final Graphics2D graBlocks;

    private final Graphics screen;

    public MainPanel(){
        super();
        this.background = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.entities = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.blocks = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        this.graBackground = (Graphics2D) background.getGraphics();
        this.graEntities = (Graphics2D) entities.getGraphics();
        this.graBlocks = (Graphics2D) blocks.getGraphics();

        this.screen = getGraphics();
    }

    public void paintComponent(Graphics g){
        BufferedImage image = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics screen = image.getGraphics();
        screen.drawImage(background, 0, 0, this);
        screen.drawImage(entities, 0, 0, this);
        screen.drawImage(blocks, 0, 0, this);

        g.drawImage(image, 0, 0, this);
    }
    public void rewrite(){
        paintComponent(getGraphics());
    }

    public void drawBackground(BufferedImage pBackground){
        graBackground.drawImage(pBackground, 0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, this);
        rewrite();
    }
    public void drawEntity(BufferedImage pImage, int x, int y, int width, int height){
        graEntities.drawImage(pImage, x, y, width, height, this);
    }
    public void drawEntity(DisplayObjectData pEntity){
        drawEntity(pEntity.getImage(), (int) pEntity.getX(), (int) pEntity.getY(), pEntity.getWidth(), pEntity.getHeight());
    }
    public void drawEntities(ArrayList<DisplayObjectData> pEntities){
        graEntities.setComposite(AlphaComposite.Clear);
        graEntities.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        graEntities.setComposite(AlphaComposite.SrcOver);
        graEntities.setColor(Color.BLACK);

        for (DisplayObjectData displayObjectData : pEntities){
            drawEntity(displayObjectData);
        }
    }
    public void drawBlocks(BufferedImage[][] pBlockTextures){
        if (pBlockTextures.length != 15 || pBlockTextures[0].length != 20){
            throw new IllegalArgumentException("Invalid Size of Block Texture");
        }

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 20; x++) {
                graBlocks.drawImage(pBlockTextures[y][x], x * Configs.BLOCK_SIZE, y * Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, this);
            }
        }
    }
    public void drawBlocks(BlockState[][] pBlockStates){
        if (pBlockStates.length != 15 || pBlockStates[0].length != 20){
            throw new IllegalArgumentException("Invalid Size of Block Texture");
        }

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 20; x++) {
                graBlocks.drawImage(Blocks.blocks.get(pBlockStates[y][x].getType()).getTEXTURE(pBlockStates[y][x].getState()), x * Configs.BLOCK_SIZE, y * Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, this);
            }
        }
    }
}
