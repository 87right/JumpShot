package main.java.com.right.github.world.level.block;

import main.java.com.right.github.core.AABB;

public class Goal extends Block{
    private final AABB AABB = new AABB(40.0f, 40.0f);
    public Goal(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AABB getAABB() {
        return AABB;
    }
}
