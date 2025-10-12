package main.java.com.right.github.world.level;


import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.core.Pos;
import main.java.com.right.github.shared.packet.KeyInputsPacket;
import main.java.com.right.github.shared.packet.LevelPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.entity.Entity;
import main.java.com.right.github.world.entity.Player;
import main.java.com.right.github.world.level.block.Block;
import main.java.com.right.github.world.level.block.Blocks;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Queue;

public class Level {
    private BlockState[][] blockStates = new BlockState[15][20];
    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();

    public Level(Queue<Packet> toSendPacket, BlockState[][] pBlockStates){
        blockStates = pBlockStates;
        toSendPacket.add(new LevelPacket.BlockStatesPacket.SendAllStatsPacket(blockStates, getTypes(), getStates()));
        player = new Player(new Pos(40, 40), toSendPacket);
        entities.add(player);
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
        return Blocks.AIR;
    }

    public void keyInputs(KeyInputsPacket pKeyInputsPacket) {
        player.keyInputs(pKeyInputsPacket, this);
    }

    public void summonEntity(Entity pEntity){
        entities.add(pEntity);
    }

    public void setBlockState(BlockPos pBlockPos, BlockState pBlockState) {
        if (pBlockPos.isOutOfStage()){return;}
        blockStates[pBlockPos.getY()][pBlockPos.getX()] = pBlockState;
    }
}
