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
                new SelectableItem("Ruler", 5),
                new SelectableItem("Go Back", 3),
                new SelectableItem("Exit", 4),
        };
        final SelectableItem[] blocks = {
                new SelectableItem("Air", 1),
                new SelectableItem("Wall", 2),
                new SelectableItem("Goal", 3),
        };
        final SelectableItem[] createOrEdit = {
                new SelectableItem("Create New Stage", 1),
                new SelectableItem("Edit Existing Stage", 2),
        };
        final SelectableItem[] errorStageNotFound = {
                new SelectableItem("Stage Not Found", -1),
                new SelectableItem("Create New Stage", 1),
                new SelectableItem("Go Back", 2),
        };
        final SelectableItem[] errorStageAlreadyExist = {
                new SelectableItem("Stage Already Exists", -1),
                new SelectableItem("Edit Stage", 1),
                new SelectableItem("Go Back", 2),
        };
        final SelectableItem[] saveOrNot = {
                new SelectableItem("Save Stage?", -1),
                new SelectableItem("Yes", 1),
                new SelectableItem("No", 2),
        };


        BlockPos blockPos = new BlockPos(0, 0);
        BlockState blockState = new BlockState(1, 0);

        String stageName = "";

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
                    // 初期段階
                    case 0:{
                        phase = 6;
                        break;
                    }
                    // ステージ選択
                    case 1:{
                        if (Objects.equals(responseString.getContent(), "")){
                            packetsServerToClient.add(new ClientModeSendDataPacket.InputtingText(responseString));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.LOADING_MODE));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.INPUTTING_TEXT_MODE));
                            isWaiting = true;
                            break;
                        }
                        stageName = responseString.getContent();
                        responseString.setContent("");
                        try{
                            world = new WorldEditorMode();
                            world.loadLevel(packetsServerToClient, stageName);
                        } catch (Exception _) {
                            phase = 2;
                            break;
                        }
                        phase = 3;
                        break;
                    }
                    // 選択されたステージが見つかりません!
                    case 2:{
                        if (responseInteger.getValue() == -1){
                            packetsServerToClient.add(new ClientModeSendDataPacket.ChoosingItem(errorStageNotFound, responseInteger));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.CHOOSING_ITEM_MODE));
                            break;
                        }
                        int value = responseInteger.getValue();
                        responseInteger.setValue(-1);
                        switch (value){
                            case 1:{
                                phase = 8;
                                break;
                            }
                            case 2:{
                                // 戻る
                                phase = 6;
                                break;
                            }
                        }
                        break;
                    }
                    // BlockPos選択
                    case 3:{
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
                    // メニュー
                    case 4:{
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
                                phase = 10;
                                break;
                            }
                            case 5:{
                                // 定規
                                phase = 11;
                                break;
                            }
                        }
                        break;
                    }
                    // Block選択
                    case 5:{
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
                    // 新規作成 or 編集
                    case 6:{
                        if (responseInteger.getValue() == -1){
                            packetsServerToClient.add(new ClientModeSendDataPacket.ChoosingItem(createOrEdit, responseInteger));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.CHOOSING_ITEM_MODE));
                            break;
                        }
                        int value = responseInteger.getValue();
                        responseInteger.setValue(-1);
                        responseString.setContent("");
                        switch (value){
                            case 1:{
                                // 新規作成
                                phase = 7;
                                break;
                            }
                            case 2:{
                                // 編集
                                phase = 1;
                                break;
                            }
                        }
                        break;
                    }
                    // 新規作成のテキスト入力
                    case 7:{
                        if (Objects.equals(responseString.getContent(), "")){
                            packetsServerToClient.add(new ClientModeSendDataPacket.InputtingText(responseString));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.LOADING_MODE));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.INPUTTING_TEXT_MODE));
                            isWaiting = true;
                            break;
                        }
                        stageName = responseString.getContent();
                        responseString.setContent("");
                        try{
                            // こっちがエラー
                            WorldEditorMode tempWorld = new WorldEditorMode();
                            tempWorld.loadLevel(packetsServerToClient, stageName);
                            phase = 9;
                            break;
                        } catch (Exception _) {
                            // こっちが正常
                            phase = 8;
                            break;
                        }
                    }
                    // 新規作成と編集へ移行
                    case 8:{
                        JSSFManager.create(stageName);
                        try{
                            world = new WorldEditorMode();
                            world.loadLevel(packetsServerToClient, stageName);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        phase = 3;
                        break;
                    }
                    // そのステージは既に存在します
                    case 9:{
                        if (responseInteger.getValue() == -1){
                            packetsServerToClient.add(new ClientModeSendDataPacket.ChoosingItem(errorStageAlreadyExist, responseInteger));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.CHOOSING_ITEM_MODE));
                            break;
                        }
                        int value = responseInteger.getValue();
                        responseInteger.setValue(-1);
                        switch (value){
                            case 1:{
                                // 編集
                                try{
                                    world = new WorldEditorMode();
                                    world.loadLevel(packetsServerToClient, stageName);
                                    phase = 3;
                                    break;
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            case 2:{
                                // 戻る
                                phase = 6;
                                break;
                            }
                        }
                        break;
                    }
                    // 保存しますか
                    case 10:{
                        if (responseInteger.getValue() == -1){
                            packetsServerToClient.add(new ClientModeSendDataPacket.ChoosingItem(saveOrNot, responseInteger));
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.CHOOSING_ITEM_MODE));
                            break;
                        }
                        int value = responseInteger.getValue();
                        responseInteger.setValue(-1);
                        switch (value){
                            case 1:{
                                phase = 6;
                                responseString.setContent("");
                                world.saveLevel();
                                break;
                            }
                            case 2:{
                                phase = 6;
                                responseString.setContent("");
                                break;
                            }
                        }
                        break;
                    }
                    // 定規
                    case 11:{

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