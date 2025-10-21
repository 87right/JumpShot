package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;

public class AllMenu {
    public final MainGame MAIN_GAME;
    public final PauseMenu PAUSE_MENU;
    public final ResultMenu RESULT_MENU;
    public final TItleMenu TITLE_MENU;
    public final LoadingMenu LOADING_MENU;

    public AllMenu(MainFrame frame, MainPanel panel, ClientData clientData){
        MAIN_GAME = new MainGame(frame, panel, clientData);
        PAUSE_MENU = new PauseMenu(frame, panel, clientData);
        RESULT_MENU = new ResultMenu(frame, panel, clientData);
        TITLE_MENU = new TItleMenu(frame, panel, clientData);
        LOADING_MENU = new LoadingMenu(frame, panel, clientData);
    }
}
