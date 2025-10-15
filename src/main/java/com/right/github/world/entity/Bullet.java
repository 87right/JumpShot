package main.java.com.right.github.world.entity;


import main.java.com.right.github.core.Pos;
import main.java.com.right.github.core.TextureManager;
import main.java.com.right.github.core.Vec2D;
import main.java.com.right.github.shared.packet.EntityPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.level.Level;

import java.awt.image.BufferedImage;
import java.util.Queue;

public class Bullet extends Entity {
    private boolean exist;
    private static final BufferedImage NULL = TextureManager.getTexture("entity/bullet/null.png");
    private static final BufferedImage BLUE = TextureManager.getTexture("entity/bullet/blue.png");
    public Bullet(Pos spawnPos, Queue<Packet> toSendPackets){
        setPos(new Pos(0, 0));
        setVelocity(new Vec2D(0, 0));
        setAcceleration(new Vec2D(0, 0));
        setState(getPos(), getVelocity(), getAcceleration(), 100, 100);
        getDisplayObjectData().setImage(BLUE);
        toSendPackets.add(new EntityPacket(getDisplayObjectData()));
    }

    @Override
    public void update(Queue<Packet> toSendPackets, Level pLevel) {
        setPos(getPos().move(getVelocity()));
        getDisplayObjectData().setX(getPos().getX());
        getDisplayObjectData().setY(getPos().getY());
    }

    public boolean exist(){return exist;}
    public void create(Pos pos, Vec2D vec){
        setPos(pos);
        setVelocity(vec);
        getDisplayObjectData().setX(getPos().getX());
        getDisplayObjectData().setY(getPos().getY());
        exist = true;
        getDisplayObjectData().setImage(BLUE);
    }
    public void delete(){
        exist = false;
        getDisplayObjectData().setImage(NULL);
    }
}
