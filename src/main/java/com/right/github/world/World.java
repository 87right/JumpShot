package main.java.com.right.github.world;

import main.java.com.right.github.core.JSSFManager;
import main.java.com.right.github.core.Pos;
import main.java.com.right.github.shared.packet.KeyInputsPacket;
import main.java.com.right.github.shared.packet.Packet;
import main.java.com.right.github.world.entity.Bullet;
import main.java.com.right.github.world.entity.Entity;
import main.java.com.right.github.world.entity.Player;
import main.java.com.right.github.world.level.Level;

import java.util.Queue;

public class World {
    protected Level level;

    public World(){}

    public void update(Queue<Packet> toSendPacket){
        if (level == null){return;}
        level.update(toSendPacket);
    }
    public void keyInputs(KeyInputsPacket pKeyInputsPacket){
        if (level == null){return;}
        level.keyInputs(pKeyInputsPacket);
    }

    public void loadLevel(Queue<Packet> toSendPacket, String pName){
        JSSFManager.RawStageData rawStageData = JSSFManager.read(pName);
        level = new Level(toSendPacket, rawStageData.blockStates());

        Queue<Entity.RawEntityData> rawEntityData = rawStageData.rawEntityData();
        while (!rawEntityData.isEmpty()){
            level.summonEntity(Entity.readeRawEntityData(toSendPacket, rawEntityData.poll()));
        }
    }
}
