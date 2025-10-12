package main.java.com.right.github.client.ui.panelmanager.panel;


import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.world.level.block.Blocks;
import main.java.com.right.github.world.level.block.state.BlockState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainPanel extends JPanel {


    private final BufferedImage background;
    private final BufferedImage entities;
    private final BufferedImage blocks;
    private final BufferedImage particles;
    private final BufferedImage ui;

    private final Graphics2D graBackground;
    private final Graphics2D graEntities;
    private final Graphics2D graBlocks;
    private final Graphics2D graParticles;
    private final Graphics2D graUI;

    public MainPanel(){
        super();
        this.background = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.entities = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.blocks = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.particles = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.ui = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        this.graBackground = (Graphics2D) background.getGraphics();
        this.graEntities = (Graphics2D) entities.getGraphics();
        this.graBlocks = (Graphics2D) blocks.getGraphics();
        this.graParticles = (Graphics2D) particles.getGraphics();
        this.graUI = (Graphics2D) ui.getGraphics();

    }

    public void paintComponent(Graphics g){
        BufferedImage image = new BufferedImage(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics screen = image.getGraphics();
        screen.drawImage(background, 0, 0, this);
        screen.drawImage(entities, 0, 0, this);
        screen.drawImage(blocks, 0, 0, this);
        screen.drawImage(particles, 0, 0, this);
        screen.drawImage(ui, 0, 0, this);

        g.drawImage(image, 0, 0, this);
    }
    public void rewrite(){
        paintComponent(getGraphics());
    }

    // 全部を透明にするやつら
    public void clearBackground(){
        graBackground.setComposite(AlphaComposite.Clear);
        graBackground.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        graBackground.setComposite(AlphaComposite.SrcOver);
    }
    public void clearEntities(){
        graEntities.setComposite(AlphaComposite.Clear);
        graEntities.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        graEntities.setComposite(AlphaComposite.SrcOver);
    }
    public void clearBlocks(){
        graBlocks.setComposite(AlphaComposite.Clear);
        graBlocks.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        graBlocks.setComposite(AlphaComposite.SrcOver);
    }
    public void clearParticles(){
        graParticles.setComposite(AlphaComposite.Clear);
        graParticles.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        graParticles.setComposite(AlphaComposite.SrcOver);
    }
    public void clearUI(){
        graUI.setComposite(AlphaComposite.Clear);
        graUI.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        graUI.setComposite(AlphaComposite.SrcOver);
    }

    // 便利なAPI
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
        clearEntities();
        for (DisplayObjectData displayObjectData : pEntities){
            drawEntity(displayObjectData);
        }
    }

    public void drawBlocks(BufferedImage[][] pBlockTextures){
        if (pBlockTextures.length != 15 || pBlockTextures[0].length != 20){
            throw new IllegalArgumentException("Invalid Size of Block Texture");
        }
        clearBlocks();
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
        clearBlocks();

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 20; x++) {
                graBlocks.drawImage(Blocks.blocks.get(pBlockStates[y][x].getType()).getTEXTURE(pBlockStates[y][x].getState()), x * Configs.BLOCK_SIZE, y * Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, this);
            }
        }
    }

    public void drawGrayParticle(int weakness){
        graParticles.setColor(new Color(weakness, weakness, weakness, weakness));
        graParticles.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
    }

    public void drawProgress(int x, int y, int width, int height, int pProgress){
        float progress;
        if (pProgress < 0){
            progress = 0;
        } else if (pProgress > 100) {
            progress = 1.0f;
        }else {
            progress = pProgress / 100.0f;
        }

        int indentX = (int) Math.min(width * 0.1f, 10.0f);
        int indentY = (int) Math.min(height * 0.1f, 10.0f);

        graUI.setColor(Color.GRAY);
        graUI.fillRect(x, y, width, height);

        graUI.setColor(Color.WHITE);
        graUI.fillRect(x + indentX, y + indentY, (int) ((width - 2 * indentX) * progress), height - 2 * indentY);
    }
    public void drawText(int x, int y, String content, int size){
        graUI.setFont(new Font("Arial", Font.BOLD, size));
        graUI.drawString(content, x, y);
    }
}
