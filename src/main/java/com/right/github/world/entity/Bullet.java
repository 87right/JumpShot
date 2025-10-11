package main.java.com.right.github.world.entity;


import main.java.com.right.github.core.Pos;
import main.java.com.right.github.shared.packet.BasePacket;
import main.java.com.right.github.shared.packet.EntityPacket;

import java.util.Queue;

public class Bullet extends Entity {
    public Bullet(Pos pSpawnPos, Queue<BasePacket> toSendPackets){
        toSendPackets.add(new EntityPacket(getDisplayObjectData()));
    }
}
