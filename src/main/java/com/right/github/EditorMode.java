package main.java.com.right.github;

import main.java.com.right.github.core.BlockPos;
import main.java.com.right.github.shared.Logs;
import main.java.com.right.github.world.World;
import main.java.com.right.github.world.level.block.state.BlockState;

public class EditorMode {
    public static void main(String[] args){
        Logs.Info("これは本体ではなく、エディターモードです！");

    }

    private static class WorldEditorMode extends World {
        private WorldEditorMode(){}

        private void setBlockState(BlockPos pBlockPos, BlockState pBlockState){
            level.setBlockState(pBlockPos, pBlockState);
        }
    }
}
