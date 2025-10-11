package main.java.com.right.github.core;

public class Pos {
    private Float x;
    private Float y;

    public Pos(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Pos copy(){return new Pos(x, y);}

    public Pos move(float dx, float dy){
        return new Pos(x + dx, y + dy);
    }
    public Pos move(Vec2D pVelocity){
        return new Pos(x + pVelocity.getX(), y + pVelocity.getY());
    }

    public Float getX() {
        return x;
    }
    public Float getY() {
        return y;
    }
}
