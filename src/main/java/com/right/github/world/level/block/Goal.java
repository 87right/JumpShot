package main.java.com.right.github.world.level.block;

import main.java.com.right.github.core.AABB;
import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.world.entity.Bullet;
import main.java.com.right.github.world.level.Level;

public class Goal extends Block{
    private final AABB AABB = new AABB(40.0f, 40.0f);
    public Goal(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AABB getAABB() {
        return AABB;
    }

    @Override
    public void bulletReaction(Level level, BlockPos blockPos, Bullet bullet) {
        bullet.delete();
    }
}
