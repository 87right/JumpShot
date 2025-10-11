package main.java.com.right.github.client.ui.panelmanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;
import main.java.com.right.github.core.DisplayObjectData;

public class StagePanelManager extends AbstractPanelManager{
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


}
