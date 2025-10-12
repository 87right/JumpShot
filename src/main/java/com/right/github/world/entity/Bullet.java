package main.java.com.right.github.world.entity;


import main.java.com.right.github.core.Pos;
import main.java.com.right.github.shared.packet.EntityPacket;
import main.java.com.right.github.shared.packet.Packet;

import java.util.Queue;

public class Bullet extends Entity {
    public Bullet(Pos pSpawnPos, Queue<Packet> toSendPackets){
        toSendPackets.add(new EntityPacket(getDisplayObjectData()));
    }
}
