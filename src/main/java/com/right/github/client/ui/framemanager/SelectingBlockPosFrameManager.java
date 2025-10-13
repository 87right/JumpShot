package main.java.com.right.github.client.ui.framemanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;
import main.java.com.right.github.core.Configs;

public class SelectingBlockPosFrameManager extends AbstractFrameManager{
    @Override
    public void enter(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {
        mainFrame.setInputConsumedByUI(true);
    }

    @Override
    public void input(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {
        clientData.setMouseX(mainFrame.getMouseX());
        clientData.setMouseY(mainFrame.getMouseY());
        if (mainFrame.getUiInput() < 0){
            mainFrame.checkedUIInput();
            int blockPosX = clientData.getMouseX() / Configs.BLOCK_SIZE;
            int blockPosY = clientData.getMouseY() / Configs.BLOCK_SIZE;
            clientData.setSelectingBlockPosInput((mainFrame.getUiInput()& 0b111111) | ((blockPosX + blockPosY * Configs.STAGE_WIDTH) << 6));
            clientNet.response();
        }
    }

    @Override
    public void exit(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {
        mainFrame.setInputConsumedByUI(false);
    }
}
