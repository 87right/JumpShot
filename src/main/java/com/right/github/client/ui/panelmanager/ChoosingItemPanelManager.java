package main.java.com.right.github.client.ui.panelmanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.SelectableItem;

public class ChoosingItemPanelManager extends AbstractPanelManager {
    private Integer mouseX = 0;
    private Integer mouseY = 0;

    private final int fontSize = Configs.DEFAULT_FONT_SIZE;
    private final int margin = (int) (Configs.MARGIN_ON_STRING * 0.5f);
    private final int indent = fontSize + Configs.MARGIN_ON_STRING;
    private final int topY = Configs.TITLE_HEIGHT;

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
        boolean someIsHovered = false;
        panel.clearUI();
        panel.clearParticles();
        for (int i = 0; i < clientData.getSelectableItems().length; i++) {
            int currentY = indent * i + topY;
            SelectableItem selectableItem = clientData.getSelectableItems()[i];
            panel.drawText(40, currentY, "- " + selectableItem.getName(), fontSize);
            if (clientData.getMouseY() >= currentY - margin && clientData.getMouseY() <= currentY + indent - margin){
                panel.fillRect(30, currentY + margin, Configs.SCREEN_WIDTH, fontSize, 0, 0, 0, 128);
                someIsHovered = true;
            }
        }
        if (!someIsHovered){
            panel.clearParticles();
        }
    }

    @Override
    public void exit(MainPanel panel, ClientData clientData) {
    }
}
