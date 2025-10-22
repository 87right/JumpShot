package main.java.com.right.github.client.data;

import main.java.com.right.github.client.ui.component.AbstractComponent;
import main.java.com.right.github.client.ui.menu.AbstractMenu;
import main.java.com.right.github.core.*;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ClientData{
    // 各モードで使われるデータたち
    // region COMMON
    private boolean isRunning;

    private int mouseX = 0;
    private int mouseY = 0;

    public final BufferedImage BACKGROUND_SELECT = TextureManager.getTexture("background/select.png");
    public final BufferedImage BACKGROUND_TYPE_TEXT = TextureManager.getTexture("background/type_text.png");

    private final ArrayList<AbstractComponent> components = new ArrayList<>();

    public int screenWidth, screenHeight;
    public int requestedMenu = -1;
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
    private StringObject inputText;
    // endregion
    // region SELECTING BLOCK POS
    private IntegerObject selectingBlockPosInput;
    // endregion
    // region SELECTING POS
    private FloatObject selectingPosXInput;
    private FloatObject selectingPosYInput;
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
    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
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
    public StringObject getInputText() {
        return inputText;
    }
    public void setInputText(String inputText) {
        this.inputText.setContent(inputText);
    }
    public void setInputText(StringObject inputText) {
        this.inputText = inputText;
    }

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
    public void setSelectingPosXInput(float selectingPosXInput){
        this.selectingPosXInput.setValue(selectingPosXInput);
    }
    public void setSelectingPosXInput(FloatObject selectingPosXInput){
        this.selectingPosXInput = selectingPosXInput;
    }
    public void setSelectingPosYInput(float selectingPosYInput){
        this.selectingPosYInput.setValue(selectingPosYInput);
    }
    public void setSelectingPosYInput(FloatObject selectingPosYInput){
        this.selectingPosYInput = selectingPosYInput;
    }
    // endregion
    // region SETTING
    // endregion
}
