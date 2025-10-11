package main.java.com.right.github.core;

import main.java.com.right.github.client.ui.ClientUI;

import java.awt.image.BufferedImage;

public class DisplayObjectData {
    private Float x;
    private Float y;
    private int width;
    private int height;
    private BufferedImage image;
    private boolean unChecked;

    public DisplayObjectData(Float pX, Float pY, Integer imageSizeX, Integer imageSizeY){
        x = pX;
        y = pY;
        width = imageSizeX;
        height = imageSizeY;
        image = new BufferedImage(imageSizeX, imageSizeY, BufferedImage.TYPE_INT_ARGB);
        unChecked = true;
    }
    public float getX() {
        unChecked = false;
        return x;
    }
    public float getY() {
        unChecked = false;
        return y;
    }
    public int getWidth() {
        unChecked = false;
        return width;
    }
    public int getHeight() {
        unChecked = false;
        return height;
    }
    public BufferedImage getImage() {
        unChecked = false;
        return image;
    }
    public void setX(float x) {
        unChecked = true;
        this.x = x;
    }
    public void setY(float y) {
        unChecked = true;
        this.y = y;
    }
    public void setWidth(int width) {
        unChecked = true;
        this.width = width;
    }
    public void setHeight(int height) {
        unChecked = true;
        this.height = height;
    }
    public void setImage(BufferedImage image) {
        unChecked = true;
        this.image = image;
    }
    public boolean isUnChecked() {
        return unChecked;
    }
}
