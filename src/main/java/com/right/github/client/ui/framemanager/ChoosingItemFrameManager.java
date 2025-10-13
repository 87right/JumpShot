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
    public void enter(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        frame.setInputConsumedByUI(true);
    }

    @Override
    public void input(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        clientData.setMouseX(frame.getMouseX());
        clientData.setMouseY(frame.getMouseY());

        if (frame.getUiInput() < 0 && (frame.getUiInput() & 1) > 0){
            frame.checkedUIInput();
            if (frame.getMouseY() > topY && frame.getMouseY() < topY + indent * clientData.getSelectableItems().length){
                IntegerObject response = clientData.getResponseAddress();
                response.setValue(clientData.getSelectableItems()[(frame.getMouseY() - topY) / indent].getId());
                clientNet.response();
            }
        }

    }

    @Override
    public void exit(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        frame.setInputConsumedByUI(false);
    }
}
