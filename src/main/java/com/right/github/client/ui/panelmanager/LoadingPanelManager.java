package main.java.com.right.github.client.ui.panelmanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.TextureManager;

import java.awt.image.BufferedImage;

public class LoadingPanelManager extends AbstractPanelManager{
    private final BufferedImage BACKGROUND_LOADING = TextureManager.getTexture("background/loading.png");
    private int temp = 0;

    @Override
    public void enter(MainPanel panel, ClientData clientData) {
        panel.drawBackground(BACKGROUND_LOADING);
    }

    @Override
    public void draw(MainPanel panel, ClientData clientData) {
        temp ++;
//        panel.drawProgress((int) (Configs.SCREEN_WIDTH * 0.25f), (int) (Configs.SCREEN_HEIGHT * 0.6f), (int) (Configs.SCREEN_WIDTH * 0.5f), 40, (int) (temp * 1.7f));
    }

    @Override
    public void exit(MainPanel panel, ClientData clientData) {
        panel.clearParticles();
        panel.clearUI();
    }
}
