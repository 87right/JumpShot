package main.java.com.right.github.core;


import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.world.entity.Entity;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class JSSFManager {
    private static final String STAGE_FILE_DIR = "assets/jumpshot/stages/";
    private static final String FULL_STAGE_FILE_DIR = "src/main/resources/assets/jumpshot/stages/";

    private JSSFManager(){}

    public static RawStageData read(String pName){
        BlockState[][] blockStates = new BlockState[15][20];
        Queue<Entity.RawEntityData> rawEntityData = new ArrayDeque<>();

        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(STAGE_FILE_DIR + pName + ".jssf");
            BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(is));
            DataInputStream dis = new DataInputStream(bis)){

            for (int y = 0; y < 15; y++) {
                for (int x = 0; x < 20; x++) {
                    int type = dis.readInt();
                    long state = dis.readLong();
                    blockStates[y][x] = new BlockState(type, state);
                }
            }

            while (true) {
                try {
                    int type = dis.readInt();
                    float posX = dis.readFloat();
                    float posY = dis.readFloat();
                    long state = dis.readLong();
                    rawEntityData.add(new Entity.RawEntityData(type, posX, posY, state));
                } catch (Exception e) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new RawStageData(blockStates, rawEntityData);
    }

    public static BlockState[][] readWithFullPath(String pName){
        BlockState[][] resultBlockState = new BlockState[15][20];

        try(FileInputStream is = new FileInputStream(FULL_STAGE_FILE_DIR + pName + ".jssf");
            BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(is));
            DataInputStream dis = new DataInputStream(bis)){

            for (int y = 0; y < 15; y++) {
                for (int x = 0; x < 20; x++) {
                    int type = dis.readInt();
                    long state = dis.readLong();
                    resultBlockState[y][x] = new BlockState(type, state);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultBlockState;
    }

    public static void write(String pName, BlockState[][] pBlockStates, float x, float y){
        try(FileOutputStream fos = new FileOutputStream(FULL_STAGE_FILE_DIR + pName + ".jssf");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos)){

            Logs.Info(pName + ".jssf へ保存中");
            for (BlockState[] blockStatesY : pBlockStates){
                for (BlockState blockState : blockStatesY){
                    int type = blockState.getType();
                    long state = blockState.getState();

                    dos.writeInt(type);
                    dos.writeLong(state);
                }
            }

            // とりあえずPlayer(予定)の値をentity欄に書く
            // TODO: 実践的な機構を書く
            dos.writeInt(1);
            dos.writeFloat(x);
            dos.writeFloat(y);
            dos.writeLong(0L);
            Logs.Successful(pName + ".jssf への保存が完了しました");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void create(String pName){

        try(FileOutputStream fos = new FileOutputStream(FULL_STAGE_FILE_DIR + pName + ".jssf");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos)){

            Logs.Info(pName + ".jssf を作成中");
            for (int y = 0; y < Configs.STAGE_HEIGHT; y++) {
                for (int x = 0; x < Configs.STAGE_WIDTH; x++) {
                    dos.writeInt(y == Configs.STAGE_HEIGHT - 1? 1 : 0);
                    dos.writeLong(0);
                }
            }
            Logs.Successful(pName + ".jssf の作成が完了しました");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static record RawStageData(
            BlockState[][] blockStates,
            Queue<Entity.RawEntityData> rawEntityData
    ){};

}
