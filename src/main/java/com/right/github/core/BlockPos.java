package main.java.com.right.github.core;

public class BlockPos {
    private int x;
    private int y;

    public BlockPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public BlockPos(Pos pPos){
        this.x = (int) pPos.getX().floatValue() / Configs.BLOCK_SIZE;
        this.y = (int) pPos.getY().floatValue() / Configs.BLOCK_SIZE;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public BlockPos offset(int dx, int dy){
        return new BlockPos(x + dx, y + dy);
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public Pos getTopLeft(){
        return new Pos(x * Configs.BLOCK_SIZE, y * Configs.BLOCK_SIZE);
    }
    public Pos getTopRight(){
        return new Pos((x + 1) * Configs.BLOCK_SIZE, y * Configs.BLOCK_SIZE);
    }
    public Pos getBottomLeft(){
        return new Pos(x * Configs.BLOCK_SIZE, (y + 1) * Configs.BLOCK_SIZE);
    }
    public Pos getBottomRight(){
        return new Pos((x + 1) * Configs.BLOCK_SIZE, (y + 1) * Configs.BLOCK_SIZE);
    }

    public boolean isOutOfStage() {
        return x < 0 | x > Configs.STAGE_WIDTH - 1 | y < 0 | y > Configs.STAGE_HEIGHT - 1;
    }
}
