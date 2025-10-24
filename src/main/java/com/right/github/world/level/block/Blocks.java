package main.java.com.right.github.world.level.block;


import main.java.com.right.github.world.level.block.state.BlockBehavior;

import java.util.HashMap;
import java.util.Map;

public class Blocks {
    private static int id = 0;
    public static Map<Integer, RegisteredBlock> blocks = new HashMap<>();
    public static final Block AIR = register("air", new Block(new BlockBehavior.Properties()));
    public static final Block WALL = register("wall", new Wall(new BlockBehavior.Properties().setHasCollision(true)));
    public static final Block GOAL = register("goal", new Goal(new BlockBehavior.Properties().setHasCollision(true)));
    public static final Block GROUND = register("ground", new Ground(new BlockBehavior.Properties().setHasCollision(true)));
    public static final Block MIRROR_TLBR = register("mirror_tlbr", new MirrorTLBR(new BlockBehavior.Properties().setHasCollision(true)));
    public static final Block MIRROR_TRBL = register("mirror_trbl", new MirrorTRBL(new BlockBehavior.Properties().setHasCollision(true)));

    private static Block register(String name, Block pBlock){
        blocks.put(id, new RegisteredBlock(pBlock, name, id));
        id ++;
        return pBlock;
    }
}
