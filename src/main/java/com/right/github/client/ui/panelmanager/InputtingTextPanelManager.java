package main.java.com.right.github.client.ui.panelmanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;
import main.java.com.right.github.core.Configs;

public class InputtingTextPanelManager extends AbstractPanelManager{
    private final int fontSize = Configs.DEFAULT_FONT_SIZE;
    @Override
    public void enter(MainPanel panel, ClientData clientData) {
        panel.clearAll();
        panel.drawBackground(clientData.BACKGROUND_TYPE_TEXT);
    }

    @Override
    public void draw(MainPanel panel, ClientData clientData) {
        panel.clearUI();
        panel.drawTextBaseLine(80, 303, clientData.getInputText().getContent(), fontSize);
    }

    @Override
    public void exit(MainPanel panel, ClientData clientData) {

    }
}
