package main.java.com.right.github.world.level.block;


import main.java.com.right.github.core.TextureManager;
import main.java.com.right.github.shared.Logs;

import java.awt.image.BufferedImage;

public class RegisteredBlock {
    private final Block BLOCK;
    private final String NAME;
    private final int ID;
    private final BufferedImage TEXTURE;

    public RegisteredBlock(Block pBlock, String pName, int pID){
        Logs.Info("Loading Block \"" + pName + "\".");
        this.BLOCK =pBlock;
        this.NAME = pName;
        this.ID = pID;

        TEXTURE = TextureManager.getTexture("block/" + NAME + ".png");
    }

    public BufferedImage getTEXTURE(long state){
        return TEXTURE;
    }

    public Block getBLOCK() {
        return BLOCK;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }
}
