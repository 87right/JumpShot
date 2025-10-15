package main.java.com.right.github.world.level.block;

import main.java.com.right.github.core.AABB;
import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.world.entity.Bullet;
import main.java.com.right.github.world.level.Level;
import main.java.com.right.github.world.level.block.state.BlockBehavior;

public class Block extends BlockBehavior {
    private BlockBehavior blockBehavior;
    private final AABB DEFAULT_AABB = new AABB((float) Configs.BLOCK_SIZE, (float) Configs.BLOCK_SIZE);

    public Block(BlockBehavior.Properties pProperties) {
        super(pProperties);
    }

    public AABB getAABB(){
        return this.DEFAULT_AABB;
    }

    public void bulletReaction(Level level, BlockPos blockPos, Bullet bullet){}
}
