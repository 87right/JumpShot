package main.java.com.right.github.client.data;

import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayList;

public class ClientData{
    // ゲーム全体に関するデータたち
    private boolean isRunning;
    private EnumUIModes currentUIMode = EnumUIModes.STAGE_MODE;

    // ステージに関するデータたち
    private BlockState[][] blockStates = null;
    private ArrayList<DisplayObjectData> displayObjectData = new ArrayList<>();
    private boolean isBlockChanged = false;

    public ClientData(){}

    public void start() {
        isRunning = true;
    }

    public void end() {
    }

    public EnumUIModes getCurrentUIMode() {
        return currentUIMode;
    }
    public BlockState[][] getBlockStates() {
        return blockStates;
    }
    public ArrayList<DisplayObjectData> getDisplayObjectData() {
        return displayObjectData;
    }
    public boolean isBlockChanged() {
        return isBlockChanged;
    }
    public void setBlockStates(BlockState[][] blockStates){
        this.blockStates = blockStates;
        isBlockChanged = true;
    }
    public void setDisplayObjectData(ArrayList<DisplayObjectData> displayObjectData) {
        this.displayObjectData = displayObjectData;
    }
    public void addDisplayObjectData(DisplayObjectData displayObjectData){
        this.displayObjectData.add(displayObjectData);
    }
    public void stop(){
        isRunning = false;
     }

    public boolean isRunning() {
        return isRunning;
    }
}
