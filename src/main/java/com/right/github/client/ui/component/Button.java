package main.java.com.right.github.client.ui.component;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.TextureManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends AbstractComponent{
    private final int ID;
    private final String TEXT;
    private boolean isHovered;

    private static final BufferedImage BUTTON_IMG = TextureManager.getTexture("component/button.png");

    public Button(int x, int y, int width, int height, int ID, String text) {
        super(x, y, width, height);
        this.ID = ID;
        this.TEXT = text;
    }

    @Override
    public boolean draw(MainPanel panel, ClientData clientData) {
        Graphics2D gra = panel.renderManager.getGraUI();
        gra.setFont(new Font("Arial", Font.BOLD, height));
        if (!isHovered){
            gra.drawImage(BUTTON_IMG, x, y, width, height, panel);
            gra.drawString(TEXT, x + width * 0.125f, y + height - 4); // 下に隙間を作る分
        }else{
            gra.drawImage(BUTTON_IMG, x - (int) (width * 0.1f), y - (int) (height * 0.1f), (int) (width * 1.2f), (int) (height * 1.2f), panel);
            gra.drawString(TEXT, x + width * 0.125f, y + height - 4);
        }
        return false;
    }

    @Override
    public int input(MainFrame frame, ClientData clientData) {
        isHovered = frame.mouseMovement.getMouseX() >= x
                && frame.mouseMovement.getMouseX() <= x + width
                && frame.mouseMovement.getMouseY() >= y
                && frame.mouseMovement.getMouseY() <= y + height;
        if (frame.mouseClick.consumeLMBReleased() && isHovered){
            return ID;
        }
        return -1;
    }
}
