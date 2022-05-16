package figures;
import java.awt.*;
import ivisible.IVisible;
import java.io.Serializable;

public abstract class Figure implements IVisible, Serializable {
    protected int x, y;
    public Color lineColor, bgColor;
    public abstract void sizeChange (int a, boolean b);
    public void drag (int x, int y){
        this.x += x;
        this.y += y;
    }
    protected Figure (int x, int y, Color lineColor, Color bgColor){
        this.x = x;
        this.y = y;
        this.lineColor = lineColor;
        this.bgColor = bgColor;
    }
    protected double triangleArea (int vx[], int vy[]){
        double a = vx[0]*(vy[1] - vy[2]);
        double b = vx[1]*(vy[2] - vy[0]);
        double c = vx[2]*(vy[0] - vy[1]);
        double d = (a + b + c)/2;
        if (d > 0) return d;
        return -d;
    }
}