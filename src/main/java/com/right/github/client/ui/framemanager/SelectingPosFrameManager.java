package main.java.com.right.github.client.ui.framemanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;
import main.java.com.right.github.core.Configs;

public class SelectingPosFrameManager extends AbstractFrameManager{
    @Override
    public void enter(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        frame.setInputConsumedByUI(true);
    }

    @Override
    public void input(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        clientData.setMouseX(frame.getMouseX());
        clientData.setMouseY(frame.getMouseY());
        if (frame.getUiInput() < 0){
            frame.checkedUIInput();
            clientData.setSelectingPosXInput(frame.getMouseX());
            clientData.setSelectingPosYInput(frame.getMouseY());
            clientNet.response();
        }

    }

    @Override
    public void exit(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        frame.setInputConsumedByUI(false);
    }
}
