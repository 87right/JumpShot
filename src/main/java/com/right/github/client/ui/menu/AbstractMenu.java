package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.component.AbstractComponent;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.Configs;

import java.awt.*;
import java.util.ArrayList;

public abstract class AbstractMenu {
    protected final MainFrame frame;
    protected final MainPanel panel;
    protected final ClientData clientData;
    public AbstractMenu(MainFrame frame, MainPanel panel, ClientData clientData){
        this.frame = frame; this.panel = panel; this.clientData = clientData;
    }
    public void draw(ClientNet clientNet){
        Graphics2D gra = panel.renderManager.getGraUI();
        gra.setComposite(AlphaComposite.Clear);
        gra.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        gra = panel.renderManager.getGraEffects();
        gra.setComposite(AlphaComposite.Clear);
        gra.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        for (AbstractComponent component : clientData.getComponents()){
            component.draw(panel, clientData);
        }
    }
    public int input(ClientNet clientNet){
        for (AbstractComponent component : clientData.getComponents()){
            int input = component.input(frame, clientData);
            if (input >0){
                return input;
            }
        }
        return -1;
    }
    public void enter(ClientNet clientNet){}
    public void exit(ClientNet clientNet){}
}
