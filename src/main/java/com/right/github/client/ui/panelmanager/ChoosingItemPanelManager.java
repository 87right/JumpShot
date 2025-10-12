package main.java.com.right.github.client.ui.panelmanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.SelectableItem;

public class ChoosingItemPanelManager extends AbstractPanelManager {
    private Integer mouseX = 0;
    private Integer mouseY = 0;

    @Override
    public void enter(MainPanel panel, ClientData clientData) {
        panel.clearBackground();
        panel.clearUI();
        panel.clearParticles();
        panel.clearBlocks();
        panel.clearEntities();

        panel.drawBackground(clientData.BACKGROUND_SELECT);
    }

    @Override
    public void draw(MainPanel panel, ClientData clientData) {
        for (int i = 0; i < clientData.getSelectableItems().length; i++) {
            int fontSize = (int) (Configs.SCREEN_HEIGHT * 0.05f);
            SelectableItem selectableItem = clientData.getSelectableItems()[i];
            panel.drawText(40, (fontSize + 10) * (i + 4), "- " + selectableItem.getName(), fontSize);
        }
    }

    @Override
    public void exit(MainPanel panel, ClientData clientData) {
    }
}
