package main.java.com.right.github.client.ui;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.*;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;
import main.java.com.right.github.client.ui.panelmanager.*;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;

public enum EnumUIModes {
    EMPTY_MODE(new EmptyFrameManager(), new EmptyPanelManager()),
    LOADING_MODE(new LoadingFrameManager(), new LoadingPanelManager()),
    STAGE_MODE(new StageFrameManager(), new StagePanelManager()),
    INPUTTING_TEXT_MODE(new InputtingTextFrameManager(), new InputtingTextPanelManager()),
    CHOOSING_ITEM_MODE(new ChoosingItemFrameManager(), new ChoosingItemPanelManager()),
    CHOOSING_PACKAGE_MODE(new ChoosingPackageFrameManager(), new ChoosingPackagePanelManager()),
    SETTING_MODE(new SettingFrameManager(), new SettingPanelManager()),
    SELECTING_BLOCK_POS_MODE(new SelectingBlockPosFrameManager(), new SelectingBlockPosPanelManager()),
    SELECTING_POS_MODE(new SelectingPosFrameManager(), new SelectingPosPanelManager()),
    ;


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
    public void enter(MainFrame frame, MainPanel panel, ClientNet clientNet, ClientData clientData){
        frameManager.enter(frame, clientNet, clientData);
        panelManager.enter(panel, clientData);
    }
    public void exit(MainFrame frame, MainPanel panel, ClientNet clientNet, ClientData clientData){
        frameManager.exit(frame, clientNet, clientData);
        panelManager.exit(panel, clientData);
    }
}
