package main.java.com.right.github.world.level.block;

import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.core.MathHelper;
import main.java.com.right.github.core.Matrix;
import main.java.com.right.github.world.entity.Bullet;
import main.java.com.right.github.world.level.Level;

public class MirrorTRBL extends Block{
    // TopLeft から BottomRightヘの鏡
    static float[][] matrixValue = {
            {0.0f, -1.0f, 0.0f},
            {-1.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 1.0f},
    };
    private static final Matrix MATRIX = new Matrix(matrixValue);

    public MirrorTRBL(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void bulletReaction(Level level, BlockPos blockPos, Bullet bullet) {
        if (MathHelper.commonPoint(bullet.getPos(), bullet.getPos().move(bullet.getVelocity()), blockPos.getTopRight(), blockPos.getBottomLeft()) != null){
            bullet.setVelocity(Matrix.product(MATRIX, bullet.getVelocity().getMatrix()));
        }
    }
}
