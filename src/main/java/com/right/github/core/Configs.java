package main.java.com.right.github.core;

public class Configs {
    public static final int BLOCK_SIZE = 40;
    public static final int STAGE_WIDTH = 20;
    public static final int STAGE_HEIGHT = 15;
    public static final int SCREEN_WIDTH = BLOCK_SIZE * STAGE_WIDTH;
    public static final int SCREEN_HEIGHT = BLOCK_SIZE * STAGE_HEIGHT;
    public static final int PLAYER_WIDTH = 30;
    public static final int PLAYER_HEIGHT = 30;
    public static final int DEFAULT_FONT_SIZE = (int) (Configs.SCREEN_HEIGHT * 0.07f);
    public static final int MARGIN_ON_STRING = 10;
    public static final int TITLE_HEIGHT = (DEFAULT_FONT_SIZE + MARGIN_ON_STRING) * 2;
    public static final float GRAVITY = 9.8f / 20.875f;


    private Configs(){}
}
