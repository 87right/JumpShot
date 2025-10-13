package main.java.com.right.github.client.ui.framemanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;
import main.java.com.right.github.core.Configs;

public class SelectingBlockPosFrameManager extends AbstractFrameManager{
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
            int blockPosX = clientData.getMouseX() / Configs.BLOCK_SIZE;
            int blockPosY = clientData.getMouseY() / Configs.BLOCK_SIZE;
            clientData.setSelectingBlockPosInput((frame.getUiInput()& 0b111111) | ((blockPosX + blockPosY * Configs.STAGE_WIDTH) << 6));
            clientNet.response();
        }
    }

    @Override
    public void exit(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        frame.setInputConsumedByUI(false);
    }
}
