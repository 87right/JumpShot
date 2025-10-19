package main.java.com.right.github.core;

public class Vec2D {
    private float x;
    private float y;

    public Vec2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public static Vec2D sum(Vec2D v1, Vec2D v2){
        return new Vec2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }
    public Vec2D add(Vec2D vec){
        return sum(this, vec);
    }
    public Vec2D reverse(){
        return new Vec2D(-1 * x, -1 * y);
    }
    public Vec2D times(float pTimes){
        return new Vec2D(x * pTimes, y * pTimes);
    }
    public Vec2D copy(){return new Vec2D(x, y);}

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addX(float pX) {
        x += pX;
    }
    public void addY(float pY){
        y += pY;
    }

    public Vec2D normalize(){
        return this.times((float) (1.0f / Math.sqrt(x*x + y*y)));
    }
}
