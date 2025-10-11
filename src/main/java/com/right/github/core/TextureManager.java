package main.java.com.right.github.core;

import main.java.com.right.github.shared.Logs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class TextureManager {
    private static final String TEXTURE_DIR_PATH = "assets/jumpshot/textures/";

    private TextureManager(){}

    public static BufferedImage getTexture(String pPath){
        BufferedImage resultTexture;

        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(TEXTURE_DIR_PATH + pPath);

        if (stream == null){
            throw new RuntimeException("テクスチャが見つかりません: " + TEXTURE_DIR_PATH + pPath);
        }
        try {
            Logs.Info("Loading Texture \"" + TEXTURE_DIR_PATH + pPath + "\".");
            resultTexture = ImageIO.read(stream);
        } catch (IOException e) {
            Logs.Warn("Failed Loading Texture! unknown file: \"" + TEXTURE_DIR_PATH + pPath + "\".");
            throw new RuntimeException(e);
        }

        return resultTexture;
    }
}
