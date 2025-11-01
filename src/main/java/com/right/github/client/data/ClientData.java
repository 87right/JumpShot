package main.java.com.right.github.client.data;

import main.java.com.right.github.client.ui.component.AbstractComponent;
import main.java.com.right.github.core.*;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayList;

public class ClientData{
    // 各モードで使われるデータたち
    // region COMMON
    private boolean isRunning;

    private final ArrayList<AbstractComponent> components = new ArrayList<>();

    public int screenWidth, screenHeight;
    public int requestedMenu = -1;
    public boolean isFinished;
    // endregion
    // region STAGE
    private BlockState[][] blockStates = null;
    private final ArrayList<DisplayObjectData> displayObjectData = new ArrayList<>();
    private boolean isBlockChanged = false;

    public long startTime;
    public long endTime;
    public boolean isRTA = false;
    // endregion
    // region LOADING
    // endregion
    // region EMPTY
    // endregion
    // region CHOOSING  endregion
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

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public ArrayList<AbstractComponent> getComponents() {
        return components;
    }
    public void addComponent(AbstractComponent component){
        components.add(component);
    }
    public void clearComponent(){components.clear();}

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
    public void addDisplayObjectData(DisplayObjectData displayObjectData){
        this.displayObjectData.add(displayObjectData);
    }
    public void clearDisplayObjectData(){displayObjectData.clear();}
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
