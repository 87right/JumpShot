package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.TextureManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StageClearMenu extends AbstractMenu {
    private int timer;
    private static final BufferedImage STAGE_CLEAR_BG = TextureManager.getTexture("background/stage_clear.png");
    public StageClearMenu(MainFrame frame, MainPanel panel, ClientData clientData) {
        super(frame, panel, clientData);
    }

    @Override
    public void draw(ClientNet clientNet) {
        super.draw(clientNet);
        Graphics2D gra = panel.renderManager.getGraUI();
        if (timer <= 25){
            gra.drawImage(STAGE_CLEAR_BG, 800 - timer * 32, 0, 800, 600, panel);
        }else if (timer >= 75){
            gra.drawImage(STAGE_CLEAR_BG, -32 * (timer - 75), 0, 800, 600, panel);
        }else{
            gra.drawImage(STAGE_CLEAR_BG, 0, 0, 800, 600, panel);
        }
    }

    @Override
    public int input(ClientNet clientNet) {
        super.input(clientNet);
        timer ++;

        if (timer >= 100){
            clientData.requestedMenu = clientData.isFinished ? 2:0;
        }
        return 0;
    }

    @Override
    public void enter(ClientNet clientNet) {
        super.enter(clientNet);
        timer = 0;
        frame.keyCommand.active = false;
        Graphics2D gra = panel.renderManager.getGraParticles();
        gra.setColor(new Color(0, 0, 0, 64));
        gra.fillRect(0, 0, 800, 600);
        clientNet.keyInputChanged(0,0, 0);
        frame.keyCommand.resetState();
    }

    @Override
    public void exit(ClientNet clientNet) {
        super.exit(clientNet);
        Graphics2D gra1 = panel.renderManager.getGraBlocks();
        gra1.setComposite(AlphaComposite.Clear);
        gra1.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        Graphics2D gra2 = panel.renderManager.getGraParticles();
        gra2.setComposite(AlphaComposite.Clear);
        gra2.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        Graphics2D gra3 = panel.renderManager.getGraEntities();
        gra3.setComposite(AlphaComposite.Clear);
        gra3.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
    }
}
