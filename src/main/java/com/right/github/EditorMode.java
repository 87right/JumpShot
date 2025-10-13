package main.java.com.right.github;

import main.java.com.right.github.client.Client;
import main.java.com.right.github.client.ui.EnumUIModes;
import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.core.IntegerObject;
import main.java.com.right.github.core.StringObject;
import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.shared.packet.ClientModeRequestPacket;
import main.java.com.right.github.shared.packet.ClientModeSendDataPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.World;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.SequencedCollection;

public class EditorMode {
    public static void main(String[] args){
        Logs.Info("これは本体ではなく、エディターモードです！");
        int phase = 0;
        boolean isWaiting = false;
        boolean isFine = true;

        World world = new World();

        StringObject responseString = new StringObject("");
        final IntegerObject responseInteger = new IntegerObject(-1);

        Queue<Packet> packetsClientToServer = new ArrayDeque<>();
        Queue<Packet> packetsServerToClient = new ArrayDeque<>();

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
                            packetsServerToClient.add(new ClientModeRequestPacket(EnumUIModes.INPUTTING_TEXT_MODE));
                            break;
                        }
                        try{
                            world.loadLevel(packetsServerToClient, responseString.getContent());
                        } catch (Exception _) {

                        }

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
        private WorldEditorMode(){}

        private void setBlockState(BlockPos pBlockPos, BlockState pBlockState){
            level.setBlockState(pBlockPos, pBlockState);
        }
    }
}
