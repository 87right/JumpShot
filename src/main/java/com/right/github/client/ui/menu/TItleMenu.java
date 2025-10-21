package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.component.Button;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.TextureManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TItleMenu extends AbstractMenu{
    private static final BufferedImage TITLE_BG = TextureManager.getTexture("background/title.png");
    public TItleMenu(MainFrame frame, MainPanel panel, ClientData clientData) {
        super(frame, panel, clientData);
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public int input() {
        int input = super.input();
        if (input > 0){
            switch (input){
                case 1:{break;}
                case 2:{break;}
                case 3:{break;}
            }
        }
        return 0;
    }

    @Override
    public void enter() {
        super.enter();
        panel.renderManager.getGraBackground().drawImage(TITLE_BG, 0, 0, panel);
        clientData.clearComponent();
        clientData.addComponent(new Button(300, 300, 200, 40, 1, "START"));
        clientData.addComponent(new Button(300, 360, 200, 40, 1, "SETTING"));
        clientData.addComponent(new Button(300, 420, 200, 40, 1, "EXIT"));

        frame.keyCommand.active = true;
        frame.mouseMovement.active = true;
        frame.mouseClick.active = true;
    }
}
