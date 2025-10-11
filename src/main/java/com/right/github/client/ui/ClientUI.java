package main.java.com.right.github.client.ui;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.framemanager.frame.MainFrame;
import main.java.com.right.github.client.ui.panelmanager.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.TextureManager;
import main.java.com.right.github.shared.packet.ExitPacket;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ClientUI {
    private MainFrame frame;
    private MainPanel panel;

    private ClientData clientData;
    private ClientNet clientNet;

    // Debug用背景
    private final BufferedImage BACKGROUND_LOADING = TextureManager.getTexture("background/loading.png");

    public ClientUI(ExitPacket exitPacket){
        frame = new MainFrame(exitPacket);
        panel =new MainPanel();

        frame.getContentPane().add(panel);

        frame.setBackground(Color.WHITE);	// windowの背景色設定
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setBounds(0, 0, Configs.SCREEN_WIDTH + 10, Configs.SCREEN_HEIGHT + 35);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Hoge");
        frame.setVisible(true);

        panel.drawBackground(BACKGROUND_LOADING);
    }

    public void start(ClientNet clientNet, ClientData clientData) {
        this.clientNet = clientNet;
        this.clientData = clientData;
    }

    public void update() {

        clientData.getCurrentUIMode().input(frame, clientNet, clientData);
        clientData.getCurrentUIMode().draw(panel, clientData);

        panel.rewrite();
    }

    public void end() {}

}
