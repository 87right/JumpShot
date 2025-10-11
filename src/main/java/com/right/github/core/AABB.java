package main.java.com.right.github.core;


import main.java.com.right.github.shared.Logs;

public class AABB {
    private float width = 0.0f;
    private float height = 0.0f;

    public AABB(float pWidth, float pHeight){
        if (pWidth <= 0 | pHeight <= 0){
            Logs.Warn("Invalid AABB");
        }else{
            width = pWidth;
            height = pHeight;
        }
    }

    public static boolean overlapped(Pos pObject1Pos, AABB pObject1, Pos pObject2Pos, AABB pObject2, Vec2D mtv){

        float halfWidth1 = pObject1.getWidth() / 2.0f;
        float halfWidth2 = pObject2.getWidth() / 2.0f;
        float sumWidth = halfWidth1 + halfWidth2;
        if (sumWidth <= 0){return false;}

        float halfHeight1 = pObject1.getHeight() / 2.0f;
        float halfHeight2 = pObject2.getHeight() / 2.0f;
        float sumHeight = halfHeight1 + halfHeight2;
        if (sumHeight <= 0){ return false;}


        float distanceX = pObject1Pos.getX() + halfWidth1 - pObject2Pos.getX() - halfWidth2;
        float distanceY = pObject1Pos.getY() + halfHeight1 - pObject2Pos.getY() - halfHeight2;

        float positiveDistanceX = Math.abs(distanceX);
        float positiveDistanceY = Math.abs(distanceY);

        boolean checkX = positiveDistanceX < sumWidth;
        boolean checkY = positiveDistanceY < sumHeight;


        if (checkX && checkY){
            if (sumWidth - positiveDistanceX >= sumHeight - positiveDistanceY){
                mtv.setY(Math.signum(distanceY) * (sumHeight - positiveDistanceY));
            }
            else{
                mtv.setX(Math.signum(distanceX) * (sumWidth - positiveDistanceX));
            }
        }

        return checkX && checkY;
    }

    public void setHeight(float pHeight) {
        this.height = pHeight;
    }

    public void setWidth(float pWidth) {
        this.width = pWidth;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
