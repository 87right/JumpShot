package main.java.com.right.github.client.ui.framemanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;

public class InputtingTextFrameManager extends AbstractFrameManager{
    @Override
    public void enter(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        frame.setInputConsumedByUI(true);
        frame.setTextingText("");
    }

    @Override
    public void input(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        if (frame.getTextingText().length() > 28){
            frame.setTextingText(frame.getTextingText().substring(0, frame.getTextingText().length() - 1));
        }
        clientData.setInputText(frame.getTextingText());

        if (frame.enter()){
            clientNet.response();
        }
    }

    @Override
    public void exit(MainFrame frame, ClientNet clientNet, ClientData clientData) {
        frame.setInputConsumedByUI(false);
    }
}
