package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;

public class AllMenu {
    public final MainGame MAIN_GAME;
    public final PauseMenu PAUSE_MENU;
    public final ResultMenu RESULT_MENU;
    public final TitleMenu TITLE_MENU;
    public final LoadingMenu LOADING_MENU;
    public final StageClearMenu STAGE_CLEAR_MENU;
    public final SelectStoryMenu SELECT_STORY_MENU;

    public AllMenu(MainFrame frame, MainPanel panel, ClientData clientData){
        MAIN_GAME = new MainGame(frame, panel, clientData);
        PAUSE_MENU = new PauseMenu(frame, panel, clientData);
        RESULT_MENU = new ResultMenu(frame, panel, clientData);
        TITLE_MENU = new TitleMenu(frame, panel, clientData);
        LOADING_MENU = new LoadingMenu(frame, panel, clientData);
        STAGE_CLEAR_MENU = new StageClearMenu(frame, panel, clientData);
        SELECT_STORY_MENU = new SelectStoryMenu(frame, panel, clientData);
    }

    public AbstractMenu getFromID(int id){
        return switch (id) {
            case 0 -> MAIN_GAME;
            case 1 -> PAUSE_MENU;
            case 2 -> RESULT_MENU;
            case 3 -> TITLE_MENU;
            case 4 -> LOADING_MENU;
            case 5 -> STAGE_CLEAR_MENU;
            case 6 -> SELECT_STORY_MENU;
            default -> LOADING_MENU;
        };
    }
}
