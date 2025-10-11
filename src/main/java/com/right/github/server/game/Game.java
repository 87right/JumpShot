package main.java.com.right.github.server.game;


import main.java.com.right.github.shared.packet.BasePacket;
import main.java.com.right.github.shared.packet.KeyInputsPacket;
import main.java.com.right.github.shared.packet.LevelPacket;
import main.java.com.right.github.world.World;
import main.java.com.right.github.world.level.Level;

import java.util.Queue;

public class Game {
    private World world;

    public Game(Queue<BasePacket> pPacketsToSend){
        world = new World();
        world.loadLevel(pPacketsToSend, "test");
    }

    public void update(Queue<BasePacket> pPacketsToSend){
        world.update(pPacketsToSend);
    }

    public void start(Queue<BasePacket> pPacketsToSend) {
    }

    public void keyInputs(KeyInputsPacket pKeyInputsPacket) {
        world.keyInputs(pKeyInputsPacket);
    }
}
