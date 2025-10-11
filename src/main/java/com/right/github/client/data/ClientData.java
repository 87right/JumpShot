package main.java.com.right.github.client.data;

import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Queue;

public class ClientData{
    // ゲーム全体に関するデータたち
    private boolean isRunning;
    private EnumUIModes currentUIMode = EnumUIModes.STAGE_MODE;

    // ステージに関するデータたち
    private BlockState[][] blockStates = null;
    private ArrayList<DisplayObjectData> displayObjectData = new ArrayList<>();
    private boolean isDisplayObjectDataChanged;

    public ClientData(){}

    public void start() {
        isRunning = true;
    }

    public void update() {
        for (DisplayObjectData entity: displayObjectData){
            if (entity.isUnChecked()){isDisplayObjectDataChanged = true;}
        }
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
     public boolean isDisplayObjectDataChanged(){
        if (isDisplayObjectDataChanged){
            isDisplayObjectDataChanged = false;
            return true;
        }
        return false;
    }
    public void setBlockStates(BlockState[][] blockStates){
        this.blockStates = blockStates;
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
