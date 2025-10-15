package main.java.com.right.github.shared.packet;


import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.world.level.block.state.BlockState;

public class LevelPacket implements Packet{
    public static class BulletPacket extends LevelPacket implements Packet{
        public static class SendStatePacket extends BulletPacket implements Packet{

        }
    }
    public static class BlockStatesPacket extends LevelPacket{
        public static class SendAllStatsPacket extends BlockStatesPacket{
            private final BlockState[][] blockStates;

            public SendAllStatsPacket(BlockState[][] pBlockStates){
                blockStates = pBlockStates;
            }
            public BlockState[][] getBlockStates() {
                return blockStates;
            }
        }
    }
}
