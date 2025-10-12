package main.java.com.right.github.client.data;

import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayList;

public class ClientData{
    // 各モードで使われるデータたち
    // region COMMON
    private boolean isRunning;
    private EnumUIModes currentUIMode = EnumUIModes.LOADING_MODE;
    private EnumUIModes requestedUIMode = EnumUIModes.LOADING_MODE;
    // endregion
    // region STAGE
    private BlockState[][] blockStates = null;
    private ArrayList<DisplayObjectData> displayObjectData = new ArrayList<>();
    private boolean isBlockChanged = false;
    // endregion
    // region LOADING
    // endregion
    // region EMPTY
    // endregion
    // region CHOOSING ITEM
    // endregion
    // region CHOOSING PACKAGE
    // endregion
    // region INPUTTING TEXT
    // endregion
    // region SELECTING BLOCK POS
    // endregion
    // region SELECTING POS
    // endregion
    // region SETTING
    // endregion

    public ClientData(){}

    public void start() {
        isRunning = true;
    }
    public void end() {
    }

    // region COMMON
    public boolean isRunning() {
        return isRunning;
    }
    public void stop(){
        isRunning = false;
    }
    public EnumUIModes getCurrentUIMode() {
        return currentUIMode;
    }
    public EnumUIModes getRequestedUIMode() {
        return requestedUIMode;
    }
    public void setCurrentUIMode(EnumUIModes currentUIMode) {
        this.currentUIMode = currentUIMode;
    }
    public void requestUIMode(EnumUIModes requestedUIMode) {
        this.requestedUIMode = requestedUIMode;
    }
    // endregion
    // region STAGE
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
    // endregion
    // region LOADING
    // endregion
    // region EMPTY
    // endregion
    // region CHOOSING ITEM
    // endregion
    // region CHOOSING PACKAGE
    // endregion
    // region INPUTTING TEXT
    // endregion
    // region SELECTING BLOCK POS
    // endregion
    // region SELECTING POS
    // endregion
    // region SETTING
    // endregion
}
