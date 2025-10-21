package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.world.level.block.Blocks;
import main.java.com.right.github.world.level.block.state.BlockState;

public class MainGame extends AbstractMenu{
    public MainGame(MainFrame frame, MainPanel panel, ClientData clientData) {
        super(frame, panel, clientData);
    }

    @Override
    public void draw() {
        super.draw();
        if (clientData.isBlockChanged()){
            BlockState[][] blockStates = clientData.getBlockStates();
            for (int y = 0; y < 15; y++) {
                for (int x = 0; x < 20; x++) {
                    panel.renderManager.getGraBlocks().drawImage(
                            Blocks.blocks.get(blockStates[y][x].getType()).getTEXTURE(blockStates[y][x].getType()),
                            x * Configs.BLOCK_SIZE,
                            y * Configs.BLOCK_SIZE,
                            Configs.BLOCK_SIZE,
                            Configs.BLOCK_SIZE,
                            panel
                    );
                }
            }
        }
    }

    @Override
    public int input() {
        return super.input();
    }
}
