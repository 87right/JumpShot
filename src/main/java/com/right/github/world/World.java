package main.java.com.right.github.world;

import main.java.com.right.github.core.JSSFManager;
import main.java.com.right.github.shared.packet.KeyInputsPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.shared.packet.StageClearPacket;
import main.java.com.right.github.world.entity.Entity;
import main.java.com.right.github.world.level.Level;

import java.util.ArrayList;
import java.util.Queue;

public class World {
    protected Level level;
    private final String[] STAGES;
    private int currentStage = 0;
    public boolean isFinished = false;

    public World(String[] stageNames){
        STAGES = stageNames;
    }

    public void update(Queue<Packet> toSendPacket){
        if (!isFinished){
            if (level == null) {
                loadLevel(toSendPacket, STAGES[currentStage]);
            }
            level.update(toSendPacket);

            if (level.clearFlag) {
                if (currentStage == STAGES.length - 1) {
                    toSendPacket.add(new StageClearPacket(true));
                    isFinished = true;
                } else {
                    toSendPacket.add(new StageClearPacket(false));
                    currentStage++;
                    loadLevel(toSendPacket, STAGES[currentStage]);
                }

            }
        }
    }
    public void keyInputs(KeyInputsPacket pKeyInputsPacket){
        if (level == null){return;}
        level.keyInputs(pKeyInputsPacket);
    }

    public void loadLevel(Queue<Packet> toSendPacket, String pName){
        JSSFManager.RawStageData rawStageData = JSSFManager.read(pName);
        level = new Level(toSendPacket, rawStageData.blockStates());

        Queue<Entity.RawEntityData> rawEntityData = rawStageData.rawEntityData();
        rawEntityData.add(new Entity.RawEntityData(2, 0, 0, 0));
        while (!rawEntityData.isEmpty()){
            level.summonEntity(Entity.readeRawEntityData(toSendPacket, rawEntityData.poll()));
        }
    }
}
