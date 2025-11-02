package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.component.Button;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.TextureManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ResultMenu extends AbstractMenu{
    private static final BufferedImage STAGE_CLEAR_BG = TextureManager.getTexture("background/result.png");
    public ResultMenu(MainFrame frame, MainPanel panel, ClientData clientData) {
        super(frame, panel, clientData);
    }

    @Override
    public void draw(ClientNet clientNet) {
        super.draw(clientNet);
    }

    @Override
    public int input(ClientNet clientNet) {
        int input = super.input(clientNet);
        if (input == 1){
            clientData.requestedMenu = 3;
        }
        return 0;
    }

    @Override
    public void enter(ClientNet clientNet) {
        super.enter(clientNet);
        frame.mouseClick.consumeLMBReleased();
        frame.mouseMovement.active = true;
        frame.keyCommand.active = false;
        panel.renderManager.getGraBackground().drawImage(STAGE_CLEAR_BG, 0, 0, panel);
        clientData.clearComponent();
        clientData.addComponent(new Button(200, 300, 400, 40, 1, "BACK TITLE"));
        Graphics2D gra = panel.renderManager.getGraParticles();
        gra.setFont(new Font("Arial", Font.BOLD, 20));
        gra.drawString("Clear Time: "+(clientData.time / 30)+"."+(clientData.time % 30 / 3)+((clientData.time % 3) * 10 / 3)+"s", 200, 200);
    }

    @Override
    public void exit(ClientNet clientNet) {
        super.exit(clientNet);
        frame.mouseClick.consumeLMBReleased();
        Graphics2D gra1 = panel.renderManager.getGraParticles();
        gra1.setComposite(AlphaComposite.Clear);
        gra1.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
    }
}
