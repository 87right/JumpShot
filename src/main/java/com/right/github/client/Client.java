package main.java.com.right.github.client;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.ClientUI;
import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.shared.packet.*;

import java.util.Queue;

public class Client {
    private ExitPacket exitPacket;

    private final ClientNet clientNet;
    private final ClientData clientData;
    private final ClientUI clientUI;

    public Client(Queue<Packet> pPacketsClientToServer, Queue<Packet> pPacketsServerToClient){
        exitPacket = new ExitPacket();

        clientNet = new ClientNet(pPacketsClientToServer, pPacketsServerToClient, exitPacket);
        clientData = new ClientData();
        clientUI = new ClientUI(exitPacket);
    }

    public void start() {
        clientData.start();
        clientNet.start(clientData);
        clientUI.start(clientNet, clientData);
    }
    public void update(){
        clientNet.update();
        clientUI.update();
    }
    public void cleanUP(){
        clientNet.end();
        clientData.end();
        clientUI.end();
        Logs.Successful("Client is Properly Stopped.");
    }
    public boolean isRunning(){return clientData.isRunning();}

}
