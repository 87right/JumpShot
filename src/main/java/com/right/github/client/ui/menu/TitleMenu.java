package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.component.Button;
import main.java.com.right.github.client.ui.component.TextBox;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.TextureManager;

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
                    clientData.requestedMenu = 0;
                    clientNet.sendStartRequest();
                    break;
                }
            }
        }
        return 0;
    }

    @Override
    public void enter(ClientNet clientNet) {
        super.enter(clientNet);
        panel.renderManager.getGraBackground().drawImage(TITLE_BG, 0, 0, panel);
        clientData.clearComponent();
        clientData.addComponent(new Button(300, 300, 200, 40, 1, "START"));
        clientData.addComponent(new Button(300, 360, 200, 40, 1, "SETTING"));
        clientData.addComponent(new TextBox(300, 420, 200, 40 ));

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
