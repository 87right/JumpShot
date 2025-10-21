package main.java.com.right.github.client.ui.menu.frame.dispatch;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.core.Configs;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseMovement {
    private final Insets insets;
    private final ClientData clientData;
    public boolean active = false;

    private boolean moved = false;
    private boolean isDragging = false;
    private int mouseX = 0;
    private int mouseY = 0;

    public MouseMovement(ClientData clientData, Insets insets){this.clientData = clientData; this.insets = insets;}

    public void mouseMoved(MouseEvent e, int menuBar){
        if (active){
            isDragging = false;
            moved = true;
            if (clientData != null){
                mouseX = ((e.getX() - insets.left) * Configs.SCREEN_WIDTH) / clientData.screenWidth;
                mouseY = ((e.getY() - insets.top - menuBar) * Configs.SCREEN_HEIGHT) / clientData.screenHeight;
            }
        }
    }
    public void mouseDragged(MouseEvent e, int menuBar){
        if (active){
            isDragging = true;
            moved = true;
            if (clientData != null){
                mouseX = ((e.getX() - insets.left) * Configs.SCREEN_WIDTH) / clientData.screenWidth;
                mouseY = ((e.getY() - insets.top - menuBar) * Configs.SCREEN_HEIGHT) / clientData.screenHeight;
            }
        }
    }

    public boolean isMoved() {if (moved){moved = false; return true;} return false;}
    public boolean isDragging() {return isDragging;}
    public int getMouseY() {return mouseY;}
    public int getMouseX() {return mouseX;}
}
