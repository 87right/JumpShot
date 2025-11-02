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

public class SelectStoryMenu extends AbstractMenu{
    private static final BufferedImage SELECT_STORY_BG = TextureManager.getTexture("background/select.png");
    public SelectStoryMenu(MainFrame frame, MainPanel panel, ClientData clientData) {
        super(frame, panel, clientData);
    }

    @Override
    public void draw(ClientNet clientNet) {
        super.draw(clientNet);
    }

    @Override
    public int input(ClientNet clientNet) {
        int input = super.input(clientNet);
        switch (input){
            case 1:{
                clientData.requestedMenu = 0;
                clientNet.sendStartRequest(StageLib.TUTORIAL);
                break;
            }
            case 2:{
                clientData.requestedMenu = 0;
                clientNet.sendStartRequest(StageLib.EASY);
                break;
            }
            case 3: {
                clientData.requestedMenu = 0;
                clientNet.sendStartRequest(StageLib.NORMAL);
                break;
            }
            case 4:{
                clientData.requestedMenu = 0;
                clientNet.sendStartRequest(StageLib.HARD);
                break;
            }
            case 5:{
                clientData.requestedMenu = 0;
                clientNet.sendStartRequest(StageLib.MYTHIC);
                break;
            }
            case 6:{
                clientData.requestedMenu = 0;
                clientNet.sendStartRequest(StageLib.RTA);
                break;
            }
        }
        clientData.time = 0;
        return -1;
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
        panel.renderManager.getGraBackground().drawImage(SELECT_STORY_BG, 0, 0, panel);
        clientData.clearComponent();
        clientData.addComponent(new Button(200, 200, 400, 40, 1, "TUTORIAL"));
        clientData.addComponent(new Button(200, 260, 400, 40, 2, "EASY"));
        clientData.addComponent(new Button(200, 320, 400, 40, 3, "NORMAL"));
        clientData.addComponent(new Button(200, 380, 400, 40, 4, "HARD"));
        clientData.addComponent(new Button(200, 440, 400, 40, 5, "MYTHIC"));
        clientData.addComponent(new Button(200, 500, 400, 40, 6, "RTA"));

        frame.keyTyping.active = true;
        frame.mouseMovement.active = true;
        frame.mouseClick.active = true;
    }

    @Override
    public void exit(ClientNet clientNet) {
        super.exit(clientNet);
    }
}
