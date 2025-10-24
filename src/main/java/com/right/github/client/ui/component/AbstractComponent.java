package main.java.com.right.github.client.ui.component;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;

public abstract class AbstractComponent {
    public int x, y, width, height;
    public AbstractComponent(int x, int y, int width, int height){
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    public abstract void draw(MainPanel panel, ClientData clientData);
    public abstract int input(MainFrame frame, ClientData clientData);
}
