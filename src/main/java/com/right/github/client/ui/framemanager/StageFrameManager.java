package main.java.com.right.github.client.ui.framemanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;

public class StageFrameManager extends AbstractFrameManager{
    @Override
    public void enter(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {

    }

    @Override
    public void input(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {
        if (mainFrame.getFlagAction() < 0){
            mainFrame.checked();
            clientNet.keyInputChanged(mainFrame.getFlagAction(), mainFrame.getMouseX(), mainFrame.getMouseY());
        }
    }

    @Override
    public void exit(MainFrame mainFrame, ClientNet clientNet, ClientData clientData) {

    }
}
