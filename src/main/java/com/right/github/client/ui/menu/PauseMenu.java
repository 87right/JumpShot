package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.component.Button;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.Configs;

import java.awt.*;

public class PauseMenu extends AbstractMenu{
    public PauseMenu(MainFrame frame, MainPanel panel, ClientData clientData) {
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
                    clientData.requestedMenu = 0;
                    break;
                }
                case 2:{
                    clientData.requestedMenu = 3;
                    break;
                }
            }
        }
        return 0;
    }

    @Override
    public void enter(ClientNet clientNet) {
        super.enter(clientNet);
        Graphics2D gra = panel.renderManager.getGraParticles();
        gra.setColor(new Color(0, 0, 0, 64));
        gra.fillRect(0, 0, 800, 600);
        clientData.clearComponent();
        clientData.addComponent(new Button(300, 300, 200, 40, 1, "Go Back"));
        clientData.addComponent(new Button(300, 360, 200, 40, 2, "Title"));
    }

    @Override
    public void exit(ClientNet clientNet) {
        super.exit(clientNet);
        Graphics2D gra1 = panel.renderManager.getGraUI();
        gra1.setComposite(AlphaComposite.Clear);
        gra1.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        Graphics2D gra2 = panel.renderManager.getGraParticles();
        gra2.setComposite(AlphaComposite.Clear);
        gra2.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
    }
}
