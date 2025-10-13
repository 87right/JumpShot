package main.java.com.right.github.client.ui.framemanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;

public class StageFrameManager extends AbstractFrameManager{
    @Override
    public void enter(MainFrame frame, ClientNet clientNet, ClientData clientData) {

    }

    @Override
    public void input(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        if (frame.getFlagAction() < 0){
            frame.checked();
            clientNet.keyInputChanged(frame.getFlagAction(), frame.getMouseX(), frame.getMouseY());
        }
    }

    @Override
    public void exit(MainFrame frame, ClientNet clientNet, ClientData clientData) {

    }
}
