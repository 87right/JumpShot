package main.java.com.right.github.client.ui.framemanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;

abstract public class AbstractFrameManager {
    abstract public void enter(MainFrame mainFrame, ClientNet clientNet, ClientData clientData);
    abstract public void input(MainFrame mainFrame, ClientNet clientNet, ClientData clientData);
    abstract public void exit(MainFrame mainFrame, ClientNet clientNet, ClientData clientData);

}
