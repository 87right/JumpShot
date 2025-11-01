package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.component.Button;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.TextureManager;

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
        clientData.endTime = System.currentTimeMillis();
        frame.mouseMovement.active = true;
        frame.keyCommand.active = false;
        panel.renderManager.getGraBackground().drawImage(STAGE_CLEAR_BG, 0, 0, panel);
        clientData.clearComponent();
        clientData.addComponent(new Button(200, 300, 400, 40, 1, "BACK TITLE"));
    }

    @Override
    public void exit(ClientNet clientNet) {
        super.exit(clientNet);
    }
}
