package main.java.com.right.github.client.ui.component;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;

import java.awt.*;

public class TextBox extends AbstractComponent{
    private String text = "";
    public TextBox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(MainPanel panel, ClientData clientData) {
        Graphics2D gra = panel.renderManager.getGraUI();
        gra.setFont(new Font("Arial", Font.BOLD, height));
        gra.drawString(text, x, y);
    }

    @Override
    public int input(MainFrame frame, ClientData clientData) {
        System.out.println("TextBox.input" + text);
        if (frame.keyTyping.getEventEnter()){return 1;}

        if (frame.keyTyping.getEventBackSpace() && !text.isEmpty()){
            text = text.substring(0, text.length() - 1);
            return -1;
        }
        char input = frame.keyTyping.getInputChar();
        if (input != 0){
            text = text + frame.keyTyping.getInputChar();
        }

        return -1;
    }
}
