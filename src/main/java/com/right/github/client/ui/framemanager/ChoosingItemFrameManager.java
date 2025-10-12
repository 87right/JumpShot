package main.java.com.right.github.client.ui.framemanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.IntegerObject;

public class ChoosingItemFrameManager extends AbstractFrameManager{

    private final int fontSize = Configs.DEFAULT_FONT_SIZE;
    private final int margin = (int) (Configs.MARGIN_ON_STRING * 0.5f);
    private final int indent = fontSize + Configs.MARGIN_ON_STRING;
    private final int topY = Configs.TITLE_HEIGHT;

    @Override
    public void enter(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {
        mainFrame.setInputConsumedByUI(true);
    }

    @Override
    public void input(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {
        clientData.setMouseX(mainFrame.getMouseX());
        clientData.setMouseY(mainFrame.getMouseY());

        if (mainFrame.getUiInput() < 0 && (mainFrame.getUiInput() & 1) > 0){
            mainFrame.checkedUIInput();
            if (mainFrame.getMouseY() > topY && mainFrame.getMouseY() < topY + indent * clientData.getSelectableItems().length){
                IntegerObject response = clientData.getResponseAddress();
                response.setValue(clientData.getSelectableItems()[(mainFrame.getMouseY() - topY) / indent].getId());
                clientNet.response();
            }
        }

    }

    @Override
    public void exit(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {
        mainFrame.setInputConsumedByUI(false);
    }
}
