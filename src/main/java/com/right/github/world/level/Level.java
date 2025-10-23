package main.java.com.right.github.world.level;


import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.Pos;
import main.java.com.right.github.core.Vec2D;
import main.java.com.right.github.shared.packet.KeyInputsPacket;
import main.java.com.right.github.shared.packet.LevelPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.entity.Bullet;
import main.java.com.right.github.world.entity.Entity;
import main.java.com.right.github.world.entity.Player;
import main.java.com.right.github.world.level.block.Block;
import main.java.com.right.github.world.level.block.Blocks;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Queue;

public class Level {
    private final BlockState[][] blockStates;
    private Player player;
    private final ArrayList<Entity> entities = new ArrayList<>();
    public boolean clearFlag = false;

    public Level(Queue<Packet> toSendPacket, BlockState[][] pBlockStates){
        blockStates = pBlockStates;

        for (int y = 0; y < Configs.STAGE_HEIGHT; y++) {
            for (int x = 0; x < Configs.STAGE_WIDTH; x++) {
                Blocks.blocks.get(blockStates[y][x].getType()).getBLOCK().initialize(this, new BlockPos(x, y), blockStates[y][x]);
            }
        }

        toSendPacket.add(new LevelPacket.BlockStatesPacket.SendAllStatsPacket(blockStates));
    }

    public void update(Queue<Packet> toSendPackets){
        // entityの更新
        for (Entity entity: entities) {
            entity.update(toSendPackets, this);
        }
    }

    public int[][] getTypes(){
        int[][] types = new int[15][20];
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 20; x ++){
                types[y][x] = blockStates[y][x].getType();
            }
        }
        return types;
    }

    public long[][] getStates(){
        long[][] types = new long[15][20];
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 20; x ++){
                types[y][x] = blockStates[y][x].getState();
            }
        }
        return types;
    }

    public Block getBlock(BlockPos pBlockPos){
        if(!pBlockPos.isOutOfStage()){
            return Blocks.blocks.get(blockStates[pBlockPos.getY()][pBlockPos.getX()].getType()).getBLOCK();
        }
        return Blocks.WALL;
    }

    public void keyInputs(KeyInputsPacket pKeyInputsPacket) {
        if (player == null) {
            return;
        }

        player.keyInputs(pKeyInputsPacket, this);
    }

    public void summonEntity(Entity pEntity){
        entities.add(pEntity);
        if (pEntity instanceof Player player){
            this.player = player;
        }
    }

    public void setBlockState(BlockPos pBlockPos, BlockState pBlockState) {
        if (pBlockPos.isOutOfStage()){return;}
        blockStates[pBlockPos.getY()][pBlockPos.getX()] = pBlockState;
    }

    public void createBullet(String color, Pos pos, Vec2D vec){
        for (Entity entity : entities){
            if (entity instanceof Bullet bullet){
                bullet.create(pos, vec);
                return;
            }
        }
    }
    public void deleteBullet(String color){
        for (Entity entity : entities){
            if (entity instanceof Bullet bullet){
                bullet.delete();
                return;
            }
        }
    }

    public BlockState copyBlockStates(BlockPos blockPos) {
        if (blockPos.isOutOfStage()){
            return new BlockState(1, 0);
        }
        return blockStates[blockPos.getY()][blockPos.getX()].copy();
    }

    public BlockState[][] getBlockStates() {
        return blockStates;
    }
}
