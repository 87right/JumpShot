package main.java.com.right.github;

import main.java.com.right.github.client.Client;
import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.*;
import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.shared.packet.ClientModeRequestPacket;
import main.java.com.right.github.shared.packet.ClientModeSendDataPacket;
import main.java.com.right.github.shared.packet.LevelPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.World;
import main.java.com.right.github.world.level.Level;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class EditorMode {
    public static void main(String[] args){
        Logs.Info("これは本体ではなく、エディターモードです！");
        int phase = 0;
        boolean isWaiting = false;
        boolean isFine = true;

        WorldEditorMode world = new WorldEditorMode();

        final StringObject responseString = new StringObject("");
        final IntegerObject responseInteger = new IntegerObject(-1);


        final SelectableItem[] menuRMB = {
                new SelectableItem("Edit Block State", 1),
                new SelectableItem("Select Block Type", 2),
                new SelectableItem("Back", 3),
                new SelectableItem("Exit", 4),
        };
        final SelectableItem[] blocks = {
                new SelectableItem("Air", 1),
                new SelectableItem("Wall", 2),
                new SelectableItem("Goal", 3),
        };


        BlockPos blockPos = new BlockPos(0, 0);
        BlockState blockState = new BlockState(1, 0);

        final Queue<Packet> packetsClientToServer = new ArrayDeque<>();
        final Queue<Packet> packetsServerToClient = new ArrayDeque<>();

        Client client = new Client(packetsClientToServer, packetsServerToClient);

        client.start();
        client.update();

        while (client.isRunning() && isFine){
            client.update();

            if (isWaiting){
                while(!packetsClientToServer.isEmpty()){
                    Packet currentPacket = packetsClientToServer.poll();
                    if (currentPacket instanceof ClientModeSendDataPacket.Response){
                        isWaiting = false;
                    }
                }
            }else {
                switch(phase){
                    case 0:{
                        // 初期段階
                        phase = 1;
                        break;
                    }
                    case 1:{
                        // ステージ選択
                        if (Objects.equals(responseString.getContent(), "")){
                            packetsServerToClient.add(new ClientModeSendDataPacket.InputtingText(responseString));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.LOADING_MODE));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.INPUTTING_TEXT_MODE));
                            isWaiting = true;
                            break;
                        }
                        try{
                            world = new WorldEditorMode();
                            world.loadLevel(packetsServerToClient, responseString.getContent());
                        } catch (Exception _) {break;}
                        responseString.setContent("");
                        phase = 3;
                        break;
                    }
                    case 3:{
                        // BlockPos選択
                        if (responseInteger.getValue() == -1){
                            packetsServerToClient.add(new ClientModeSendDataPacket.SelectingBlockPos(responseInteger));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.SELECTING_BLOCK_POS_MODE));
                            break;
                        }
                        int value = responseInteger.getValue();
                        responseInteger.setValue(-1);
                        int mb;
                        if ((value & 1) > 0){
                            mb = 1;
                        } else if ((value & 2) > 0) {
                            mb = 2;
                        } else if ((value & 4) > 0) {
                            mb = 3;
                        } else {
                            break;
                        }
                        value = value >> 6;
                        blockPos.setX(value % Configs.STAGE_WIDTH);
                        blockPos.setY(value / Configs.STAGE_WIDTH);
                        switch (mb){
                            case 1:{
                                // 編集
                                System.out.println(blockState.getType());
                                world.setBlockState(blockPos, blockState);
                                packetsServerToClient.add(new LevelPacket.BlockStatesPacket.SendAllStatsPacket(world.getBlockStates()));
                                break;
                            }
                            case 2:{
                                blockState = world.copyBlockState(blockPos);
                                break;
                            }
                            case 3:{
                                phase = 4;
                                break;
                            }
                        }
                        break;
                    }
                    case 4:{
                        // メニュー
                        if (responseInteger.getValue() == -1){
                            packetsServerToClient.add(new ClientModeSendDataPacket.ChoosingItem(menuRMB, responseInteger));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.CHOOSING_ITEM_MODE));
                        }
                        int value = responseInteger.getValue();
                        responseInteger.setValue(-1);
                        switch (value){
                            case 1:{
                                // BlockStateの編集
                                System.out.println("Coming Soon...");
                                break;
                            }
                            case 2:{
                                // Blockの選択
                                phase = 5;
                                break;
                            }
                            case 3:{
                                // Blockの選択
                                phase = 3;
                                break;
                            }
                            case 4:{
                                // 終了
                                phase = 1;
                                responseString.setContent("");
                                world.saveLevel();
                                break;
                            }
                        }
                        break;
                    }
                    case 5:{
                        // メニュー
                        if (responseInteger.getValue() == -1){
                            packetsServerToClient.add(new ClientModeSendDataPacket.ChoosingItem(blocks, responseInteger));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.CHOOSING_ITEM_MODE));
                            break;
                        }
                        int value = responseInteger.getValue();
                        responseInteger.setValue(-1);
                        switch (value){
                            case 1:{
                                // 空気
                                blockState.setType(0);
                                break;
                            }
                            case 2:{
                                // 壁
                                blockState.setType(1);
                                break;
                            }
                            case 3:{
                                // ゴール
                                blockState.setType(2);
                                break;
                            }
                        }
                        phase = 3;
                        break;
                    }
                    default:{
                        Logs.Warn("Unexpected Status.");
                        isFine = false;
                    }
                }
            }

            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        client.cleanUP();

        System.exit(0);
    }

    private static class WorldEditorMode extends World {
        String name = "";

        private WorldEditorMode(){}

        @Override
        public void loadLevel(Queue<Packet> toSendPacket, String pName) {
            level = new Level(toSendPacket, JSSFManager.readWithFullPath(pName));
            name = pName;
        }

        private void setBlockState(BlockPos pBlockPos, BlockState pBlockState){
            level.setBlockState(pBlockPos, pBlockState.copy());
        }
        private BlockState copyBlockState(BlockPos blockPos){
            return level.copyBlockStates(blockPos);
        }
        private void saveLevel(){
            JSSFManager.write(name, level.getBlockStates());
            level = null;
        }

        public BlockState[][] getBlockStates() {
            return level.getBlockStates();
        }
    }
}
