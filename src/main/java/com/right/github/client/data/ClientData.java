package main.java.com.right.github.client.data;

import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.core.IntegerObject;
import main.java.com.right.github.core.SelectableItem;
import main.java.com.right.github.core.TextureManager;
import main.java.com.right.github.shared.packet.ClientModeSendDataPacket;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ClientData{
    // 各モードで使われるデータたち
    // region COMMON
    private boolean isRunning;
    private EnumUIModes currentUIMode = EnumUIModes.LOADING_MODE;
    private EnumUIModes requestedUIMode = EnumUIModes.LOADING_MODE;

    private int mouseX = 0;
    private int mouseY = 0;

    public final BufferedImage BACKGROUND_SELECT = TextureManager.getTexture("background/select.png");
    public final BufferedImage BACKGROUND_TYPE_TEXT = TextureManager.getTexture("background/type_text.png");
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
    private SelectableItem[] selectableItems;
    private IntegerObject responseAddress;
    // endregion
    // region CHOOSING PACKAGE
    // endregion
    // region INPUTTING TEXT
    // endregion
    // region SELECTING BLOCK POS
    private IntegerObject selectingBlockPosInput;
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
    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
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
    public SelectableItem[] getSelectableItems() {
        return selectableItems;
    }
    public void setSelectableItems(SelectableItem[] selectableItems) {
        this.selectableItems = selectableItems;
    }
    public IntegerObject getResponseAddress() {
        return responseAddress;
    }
    public void setResponseAddress(IntegerObject responseAddress) {
        this.responseAddress = responseAddress;
    }
    // endregion
    // region CHOOSING PACKAGE
    // endregion
    // region INPUTTING TEXT
    // endregion
    // region SELECTING BLOCK POS
    public void setSelectingBlockPosInput(int selectingBlockPosInput) {
        this.selectingBlockPosInput.setValue(selectingBlockPosInput);
    }
    public void setSelectingBlockPosInput(IntegerObject selectingBlockPosInput) {
        this.selectingBlockPosInput = selectingBlockPosInput;
    }
// endregion
    // region SELECTING POS
    // endregion
    // region SETTING
    // endregion
}
