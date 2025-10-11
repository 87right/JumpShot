package main.java.com.right.github.client.data;

import main.java.com.right.github.client.core.ClientPartCore;
import main.java.com.right.github.client.ui.ClientUI;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Queue;

public class ClientData extends ClientPartCore {
    // ゲーム全体に関するデータたち
    private boolean isRunning;
    // ステージに関するデータたち
    private BlockState[][] blockStates = null;
    private ArrayList<DisplayObjectData> entities = new ArrayList<>();

    public ClientData(){}

    @Override
    public void start(Queue<Packet> pAddressToSend) {
        super.start(pAddressToSend);

        isRunning = true;
    }

    @Override
    public void update() {
        super.update();

        while (!getReceiveBox().isEmpty()){
            Packet packet = getReceiveBox().poll();
            if (packet instanceof Stop){
                isRunning = false;
            }else if (packet instanceof UpdateAllBlockStates updateAllBlockStates){
                blockStates = updateAllBlockStates.getBlockStates();
                sendPacket(new ClientUI.reRenderBlocks(blockStates));
            } else if (packet instanceof AddEntity addEntity) {
                entities.add(addEntity.getDisplayObjectData());
            } else if (packet instanceof ClearEntities) {
                entities.clear();
            }
        }

        boolean unCheckFlag = false;
        for (DisplayObjectData entity: entities){
            if (entity.isUnChecked()){unCheckFlag = true;}
        }
        if (unCheckFlag){
            sendPacket(new ClientUI.reRenderEntities(entities));
        }
    }

    @Override
    public void end() {
        super.end();
    }

    public static class UpdateAllBlockStates extends Packet{
        private final BlockState[][] blockStates;
        public UpdateAllBlockStates(BlockState[][] pBlockStates){
            if (pBlockStates.length != 15 || pBlockStates[0].length != 20){
                throw new IllegalArgumentException("Invalid Size of BlockStates.");
            }
            blockStates = pBlockStates;
        }
        public BlockState[][] getBlockStates() {return blockStates;}
    }
    public static class AddEntity extends Packet{
        private final DisplayObjectData displayObjectData;
        public AddEntity(DisplayObjectData pDisplayObjectData){
            displayObjectData = pDisplayObjectData;
        }
        public DisplayObjectData getDisplayObjectData() {return displayObjectData;}
    }
    public static class ClearEntities extends Packet{
        public ClearEntities(){}
    }
    public static class Stop extends Packet{
        public Stop(){}
    }


    public boolean isRunning() {
        return isRunning;
    }
}
