package main.java.com.right.github.world.level.block.state;

public class BlockState {
    private int type;
    private long state;

    public BlockState(int pType, long pState){
        this.type = pType;
        this.state = pState;
    }

    public void setType(int pType) {
        this.type = pType;
    }

    public void setState(long pState) {
        this.state = pState;
    }

    public int getType() {
        return type;
    }

    public long getState() {
        return state;
    }
}
