package main.java.com.right.github.client.ui.panelmanager;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;

abstract public class AbstractPanelManager {
    abstract public void draw(MainPanel panel, ClientData clientData);
}
