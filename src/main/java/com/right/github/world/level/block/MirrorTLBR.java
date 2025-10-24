package main.java.com.right.github.world.level.block;

import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.core.Matrix;
import main.java.com.right.github.world.entity.Bullet;
import main.java.com.right.github.world.level.Level;

public class MirrorTLBR extends Block{
    // TopLeft から BottomRightヘの鏡
    static float[][] matrixValue = {
            {0.0f, -1.0f},
            {-1.0f, 0.0f},
    };
    private static final Matrix MATRIX = new Matrix(matrixValue);

    public MirrorTLBR(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void bulletReaction(Level level, BlockPos blockPos, Bullet bullet) {
        bullet.setVelocity(Matrix.product(MATRIX, bullet.getVelocity().getMatrix()));
    }
}
