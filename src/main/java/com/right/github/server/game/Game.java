package main.java.com.right.github.server.game;


import main.java.com.right.github.shared.packet.KeyInputsPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.World;

import java.util.Queue;

public class Game {
    private World world;
    private Queue<Packet> packetsSC;

    public Game(Queue<Packet> packetsSC){
        this.packetsSC = packetsSC;
    }

    public void update(Queue<Packet> pPacketsToSend){
        if (world == null){
            return;
        }
        world.update(pPacketsToSend);
    }

    public void start(String[] stageNames) {
        world = new World(stageNames);
    }

    public void keyInputs(KeyInputsPacket pKeyInputsPacket) {
        world.keyInputs(pKeyInputsPacket);
    }
}
