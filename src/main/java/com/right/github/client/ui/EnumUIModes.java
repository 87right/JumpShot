package main.java.com.right.github.client.ui;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.AbstractFrameManager;
import main.java.com.right.github.client.ui.framemanager.StageFrameManager;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;
import main.java.com.right.github.client.ui.panelmanager.AbstractPanelManager;
import main.java.com.right.github.client.ui.panelmanager.StagePanelManager;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;

public enum EnumUIModes {
    STAGE_MODE(new StageFrameManager(), new StagePanelManager());


    private final AbstractFrameManager frameManager;
    private final AbstractPanelManager panelManager;
    EnumUIModes(AbstractFrameManager frameManager, AbstractPanelManager panelManager){
        this.frameManager = frameManager;
        this.panelManager = panelManager;
    }

    public void draw(MainPanel panel, ClientData clientData){
        panelManager.draw(panel, clientData);
    }
    public void input(MainFrame frame, ClientNet clientNet, ClientData clientData){
        frameManager.input(frame, clientNet, clientData);
    }
}
