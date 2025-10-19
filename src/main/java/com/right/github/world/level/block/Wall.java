package main.java.com.right.github.world.level.block;

import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.world.entity.Bullet;
import main.java.com.right.github.world.level.Level;
import main.java.com.right.github.world.level.block.state.BlockState;

public class Wall extends Block{
    public Wall(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void initialize(Level level, BlockPos blockPos, BlockState blockState) {
        if (level.getBlock(blockPos.offset(0, -1)).equals(Blocks.WALL) || level.getBlock(blockPos.offset(0, -1)).equals(Blocks.GROUND)){
            blockState.setType(3);
        }
    }

    @Override
    public void bulletReaction(Level level, BlockPos blockPos, Bullet bullet) {
        bullet.delete();
    }
}
