package main.java.com.right.github.world.entity;


import main.java.com.right.github.core.*;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.level.Level;

import java.util.Queue;

public class Entity {
    private DisplayObjectData displayObjectData;
    private AABB aabb;

    protected Pos pos;
    protected Vec2D velocity;
    protected Vec2D acceleration;

    private boolean hasGravity = true;

    private static final float EPSILON = 0.001f;


    public void setState(Pos pPos, Vec2D pVelocity, Vec2D pAcceleration, int width, int height){
        displayObjectData = new DisplayObjectData(pPos.getX(), pPos.getY(),  width, height);
        pos = pPos;
        velocity = pVelocity;
        acceleration = pAcceleration;
    }

    public AABB getAABB() {
        return aabb;
    }

    public void setAABB(AABB aabb) {
        this.aabb = aabb;
    }

    public Pos getPos(){
        return pos.copy();
    }

    public Vec2D getVelocity() {
        return velocity.copy();
    }

    public Vec2D getAcceleration() {
        return acceleration.copy();
    }

    public DisplayObjectData getDisplayObjectData() {
        return displayObjectData;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public void setVelocity(Vec2D velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vec2D acceleration) {
        this.acceleration = acceleration;
    }

    public boolean hasGravity() {
        return hasGravity;
    }

    public void setHasGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
    }

    public void update(Queue<Packet> toSendPackets, Level pLevel){
        pos = pos.move(velocity);
        velocity = velocity.add(acceleration);

        if (velocity.getX() > Configs.BLOCK_SIZE - 1){velocity.setX(Configs.BLOCK_SIZE - 1);}
        if (velocity.getY() > Configs.BLOCK_SIZE - 1){velocity.setY(Configs.BLOCK_SIZE - 1);}

        displayObjectData.setX(pos.getX());
        displayObjectData.setY(pos.getY());
    }

    public boolean onGround(Level pLevel){
        return pLevel.getBlock(new BlockPos(pos.move(Configs.PLAYER_WIDTH - EPSILON, Configs.PLAYER_HEIGHT + 0.01f))).hasCollision()|
                pLevel.getBlock(new BlockPos(pos.move((float) Configs.PLAYER_WIDTH / 2, Configs.PLAYER_HEIGHT + 0.01f))).hasCollision()|
                pLevel.getBlock(new BlockPos(pos.move(EPSILON, Configs.PLAYER_HEIGHT + 0.01f))).hasCollision();
    }

    public record RawEntityData(
            int id, // 32
            float posX, // 32
            float posY, // 32
            long state // 64
    ){}

    public static Entity readeRawEntityData(Queue<Packet> packetsServerToClient, RawEntityData rawEntityData){
        switch (rawEntityData.id){
            case 1: return new Player(new Pos(rawEntityData.posX, rawEntityData.posY), packetsServerToClient);
            default: throw new RuntimeException();
        }
    }
}
