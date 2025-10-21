package main.java.com.right.github.client.ui;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.menu.AbstractMenu;
import main.java.com.right.github.client.ui.menu.AllMenu;
import main.java.com.right.github.client.ui.menu.MainGame;
import main.java.com.right.github.client.ui.menu.TItleMenu;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.shared.packet.ExitPacket;

import javax.swing.*;
import java.awt.*;

public class ClientUI {
    private AbstractMenu currentMenu;
    private final AllMenu allMenu;

    private MainFrame frame;
    private MainPanel panel;

    private ClientData clientData;
    private ClientNet clientNet;

    private final Insets insets;

    public ClientUI(ExitPacket exitPacket, ClientData clientData){
        frame = new MainFrame(exitPacket, clientData);
        panel =new MainPanel(clientData);
        allMenu = new AllMenu(frame, panel, clientData);

        frame.getContentPane().add(panel);

        frame.setBackground(Color.WHITE);	// windowの背景色設定
        frame.setResizable(true); // TODO: ReSizeの有効化&システム構築
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setTitle("Hoge");
        frame.setVisible(true);
        insets = frame.getInsets();
        frame.setBounds(0, 0, Configs.SCREEN_WIDTH + insets.left + insets.right, Configs.SCREEN_HEIGHT + insets.top + insets.bottom);
        frame.setLocationRelativeTo(null);

        changeMode(allMenu.LOADING_MENU);
    }

    public void start(ClientNet clientNet, ClientData clientData) {
        this.clientNet = clientNet;
        this.clientData = clientData;
        changeMode(allMenu.TITLE_MENU);
    }

    public void update() {
        currentMenu.input();
        currentMenu.draw();
        panel.rewrite();
    }

    public void end() {}

    private void changeMode(AbstractMenu menu){
        if (currentMenu != null){currentMenu.exit();}
        currentMenu = menu;
        if (menu != null){currentMenu.enter();}
    }

}
