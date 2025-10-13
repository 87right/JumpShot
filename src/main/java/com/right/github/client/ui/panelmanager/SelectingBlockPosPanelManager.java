package main.java.com.right.github.client.ui.panelmanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.TextureManager;

import java.awt.image.BufferedImage;

public class SelectingBlockPosPanelManager extends AbstractPanelManager{
    private final BufferedImage BACKGROUND_TEST = TextureManager.getTexture("background/test.png");
    @Override
    public void enter(MainPanel panel, ClientData clientData) {
        panel.clearAll();
        panel.drawBackground(BACKGROUND_TEST);
        panel.drawBlocks(clientData.getBlockStates());
    }

    @Override
    public void draw(MainPanel panel, ClientData clientData) {
        if (clientData.isBlockChanged()){
            panel.drawBlocks(clientData.getBlockStates());
        }
        panel.clearParticles();
        panel.fillRect((clientData.getMouseX() / Configs.BLOCK_SIZE) * Configs.BLOCK_SIZE,
                (clientData.getMouseY() / Configs.BLOCK_SIZE) * Configs.BLOCK_SIZE,
                Configs.BLOCK_SIZE, Configs.BLOCK_SIZE,
                0, 0, 0, 64);
    }

    @Override
    public void exit(MainPanel panel, ClientData clientData) {

    }
}
