package main.java.com.right.github.client.core;

import main.java.com.right.github.shared.Logs;

import java.util.ArrayDeque;
import java.util.Queue;

public class ClientPartCore {
    protected Queue<Packet> receiveBox;
    private Queue<Packet> addressToSend;

    public ClientPartCore(){
        receiveBox = new ArrayDeque<>();
    }

    public Queue<Packet> getReceiveBox() {
        return receiveBox;
    }

    public static class Packet{}

    public void start(Queue<Packet> pAddressToSend){
        addressToSend = pAddressToSend;
    }
    public void update(){}
    public void end(){}

    public void sendPacket(Packet pPacketToSend){
        addressToSend.add(pPacketToSend);
    }

}
