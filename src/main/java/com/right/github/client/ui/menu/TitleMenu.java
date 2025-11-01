package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.component.Button;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.TextureManager;
import main.java.com.right.github.world.level.StageLib;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleMenu extends AbstractMenu{
    private static final BufferedImage TITLE_BG = TextureManager.getTexture("background/title.png");
    public TitleMenu(MainFrame frame, MainPanel panel, ClientData clientData) {
        super(frame, panel, clientData);
    }

    @Override
    public void draw(ClientNet clientNet) {
        super.draw(clientNet);
    }

    @Override
    public int input(ClientNet clientNet) {
        int input = super.input(clientNet);
        if (input > 0){
            switch (input){
                case 1:{
                    clientData.requestedMenu = 6;
                    break;
                }
            }
        }
        return 0;
    }

    @Override
    public void enter(ClientNet clientNet) {
        super.enter(clientNet);
        Graphics2D gra1 = panel.renderManager.getGraBlocks();
        gra1.setComposite(AlphaComposite.Clear);
        gra1.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        Graphics2D gra2 = panel.renderManager.getGraEntities();
        gra2.setComposite(AlphaComposite.Clear);
        gra2.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        panel.renderManager.getGraBackground().drawImage(TITLE_BG, 0, 0, panel);
        clientData.clearComponent();
        clientData.addComponent(new Button(300, 300, 200, 40, 1, "START"));

        frame.keyTyping.active = true;
        frame.mouseMovement.active = true;
        frame.mouseClick.active = true;
    }

    @Override
    public void exit(ClientNet clientNet) {
        super.exit(clientNet);
        clientData.clearComponent();
    }
}
