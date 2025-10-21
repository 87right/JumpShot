package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.TextureManager;

import java.awt.image.BufferedImage;

public class LoadingMenu extends AbstractMenu{
    private static final BufferedImage LOADING = TextureManager.getTexture("background/loading.png");
    public LoadingMenu(MainFrame frame, MainPanel panel, ClientData clientData) {
        super(frame, panel, clientData);
    }

    @Override
    public void enter() {
        super.enter();
        panel.renderManager.getGraBackground().drawImage(LOADING, 0, 0, panel);
    }
}
