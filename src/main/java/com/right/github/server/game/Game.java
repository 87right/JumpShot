package main.java.com.right.github.server.game;


import main.java.com.right.github.shared.packet.KeyInputsPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.World;

import java.util.Queue;

public class Game {
    private World world;

    public Game(Queue<Packet> pPacketsToSend){
        world = new World();
        world.loadLevel(pPacketsToSend, "test");
    }

    public void update(Queue<Packet> pPacketsToSend){
        world.update(pPacketsToSend);
    }

    public void start(Queue<Packet> pPacketsToSend) {
    }

    public void keyInputs(KeyInputsPacket pKeyInputsPacket) {
        world.keyInputs(pKeyInputsPacket);
    }
}
