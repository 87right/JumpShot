package main.java.com.right.github.world.level.block.state;


import main.java.com.right.github.shared.Logs;

public class BlockBehavior {
    private final boolean hasCollision;
    private final float speedFactor;
    private final float jumpFactor;
    private final Properties properties;

    public BlockBehavior(Properties pProperties){
        this.hasCollision = pProperties.hasCollision;
        this.speedFactor = pProperties.speedFactor;
        this.jumpFactor = pProperties.jumpFactor;
        this.properties = pProperties;
    }

    public boolean hasCollision(){return hasCollision;}
    public float getSpeedFactor() {return speedFactor;}
    public float getJumpFactor() {
        return jumpFactor;
    }

    public static class Properties{
        private boolean hasCollision = false;
        private float speedFactor = 0.0f;
        private float jumpFactor = 0.0f;

        public Properties setHasCollision(boolean pHasCollision){this.hasCollision = pHasCollision;return this;}
        public void setJumpFactor(float pJumpFactor) {this.jumpFactor = pJumpFactor;}
        public void setSpeedFactor(float speedFactor) {this.speedFactor = speedFactor;}
    }

    public static class StateValue{
        private final int offset; //使用する最初のbit(>= 0)
        private final int length; //使用する長さ(>= 1)

        public StateValue(int pOffset, int pLength){
            offset = pOffset;
            length = pLength;
        }

        public int getValue(BlockState pBlockState){
            return (int) (pBlockState.getState() >> offset) & (1 << length) - 1;
        }

        public void setValue(BlockState pBlockState, int pValue){
            if(pValue > (1 << length) - 1){
                Logs.Warn("This Value is out of Range !");
                return;
            }
            pBlockState.setState((pBlockState.getState()& ~(((1L << length) - 1L) << offset)) | (long) pValue << offset);
        }
    }

    public static class StateFlag{
        private final int offset;

        public StateFlag(int pOffset){
            offset = pOffset;
        }

        public boolean getValue(BlockState pBlockState){
            return (pBlockState.getState() & 1L << offset) > 0;
        }

        public void reverse(BlockState pBlockState){
            pBlockState.setState(pBlockState.getState() ^ 1L << offset);
        }

        public void setTrue(BlockState pBlockState){
            pBlockState.setState(pBlockState.getState() | 1L << offset);
        }

        public  void setFalse(BlockState pBlockState){
            pBlockState.setState(pBlockState.getState() & ~(1L << offset));
        }

        public void setValue(BlockState pBlockState, boolean pValue){
            if (pValue){
                setTrue(pBlockState);
            }else{
                setFalse(pBlockState);
            }
        }

    }
}
