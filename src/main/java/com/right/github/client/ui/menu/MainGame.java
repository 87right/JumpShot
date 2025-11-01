package main.java.com.right.github.client.ui.menu;

import main.java.com.right.github.client.data.ClientData;
import main.java.com.right.github.client.net.ClientNet;
import main.java.com.right.github.client.ui.menu.frame.MainFrame;
import main.java.com.right.github.client.ui.menu.panel.MainPanel;
import main.java.com.right.github.core.Configs;
import main.java.com.right.github.core.DisplayObjectData;
import main.java.com.right.github.core.TextureManager;
import main.java.com.right.github.world.level.block.Blocks;
import main.java.com.right.github.world.level.block.state.BlockState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainGame extends AbstractMenu{
    private static final BufferedImage TEST_BG = TextureManager.getTexture("background/test.png");
    public MainGame(MainFrame frame, MainPanel panel, ClientData clientData) {
        super(frame, panel, clientData);
    }

    @Override
    public void draw(ClientNet clientNet) {
        super.draw(clientNet);
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

        Graphics2D gra = panel.renderManager.getGraEntities();
        for (DisplayObjectData displayObjectData : clientData.getDisplayObjectData()){
            gra.drawImage(displayObjectData.getImage(), (int) displayObjectData.getX(), (int) displayObjectData.getY(), displayObjectData.getWidth(), displayObjectData.getHeight(), panel);
        }
    }

    @Override
    public int input(ClientNet clientNet) {
        Graphics2D gra = panel.renderManager.getGraEntities();
        gra.setComposite(AlphaComposite.Clear);
        gra.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);

        if (frame.keyCommand.getState() < 0 || frame.mouseClick.getLMBState()){
            frame.keyCommand.checkedState();
            int state = frame.keyCommand.getState();
            if ((state & 1 << 3) > 0){
                clientData.requestedMenu = 1;
            }
            if (frame.mouseClick.getLMBState()){state = state | 1 << 3;}
            clientNet.keyInputChanged(state, frame.mouseMovement.getMouseX(), frame.mouseMovement.getMouseY());
        }
        return 0;
    }

    @Override
    public void enter(ClientNet clientNet) {
        super.enter(clientNet);
        clientData.startTime = System.currentTimeMillis();
        clientData.clearComponent();
        panel.renderManager.getGraBackground().drawImage(TEST_BG, 0, 0, panel);
        frame.keyCommand.active = true;
        frame.mouseClick.active = true;
    }
}
