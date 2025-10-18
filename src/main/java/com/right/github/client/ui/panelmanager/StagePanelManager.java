package main.java.com.right.github.client.ui.panelmanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.core.TextureManager;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class StagePanelManager extends AbstractPanelManager{
    private final BufferedImage BACKGROUND_TEST = TextureManager.getTexture("background/test.png");
    @Override
    public void enter(MainPanel panel, ClientData clientData) {
        panel.clearAll();
        panel.drawBackground(BACKGROUND_TEST);
    }

    @Override
    public void draw(MainPanel panel, ClientData clientData) {
        boolean isDisplayObjectDataChanged = false;
        for (DisplayObjectData entity: clientData.getDisplayObjectData()){
            if (entity.isUnChecked()){
                isDisplayObjectDataChanged = true;
                break;
            }
        }
        if (isDisplayObjectDataChanged){
            panel.drawEntities(clientData.getDisplayObjectData());
        }

        if (clientData.isBlockChanged()){
            panel.drawBlocks(clientData.getBlockStates());
        }
    }

    @Override
    public void exit(MainPanel panel, ClientData clientData) {

    }


}
