package main.java.com.right.github.core;

public class MathHelper {

    private MathHelper(){}

    public static Pos commonPoint(Pos pos1, Pos pos2, Pos pos3, Pos pos4){
        /*
        * 直線1 = pos1 : pos2
        * 直線2 = pos3 : pos4*/



        // 座標取得
        float x1 = pos1.getX();
        float y1 = pos1.getY();

        float x2 = pos2.getX();
        float y2 = pos2.getY();

        float x3 = pos3.getX();
        float y3 = pos3.getY();

        float x4 = pos4.getX();
        float y4 = pos4.getY();

        // ベクトル計算
        float dx1 = x2 - x1;
        float dy1 = y2 - y1;

        float dx2 = x4 - x3;
        float dy2 = y4 - y3;

        float den = dx2 * dy1 - dx1 * dy2;

        // 並行は除去
        float EPSILON = 0.00001f;
        if (Math.abs(den) < EPSILON){
            return null;
        }

        float t = ((dx2 * (y3 - y1)) - dy2 * (x3 - x1)) / den;
        float u = ((dx1 * (y3 - y1)) - dy1 * (x3 - x1)) / den;

        // 範囲内
        if (0.0f - EPSILON <= t && t <= 1.0f + EPSILON && 0.0f - EPSILON <= u && u <= 1.0f + EPSILON){
            return new Pos(x1 + dx1 * t, y1 + dy1 * u);
        }

        // 範囲外
        return null;
    }
}
