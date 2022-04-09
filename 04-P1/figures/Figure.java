package figures;
import java.awt.*;

public abstract class Figure{
    public int x, y;
    public boolean highlight = false;
    public Color lineColor, bgColor;
    public abstract void sizeChange (int a, boolean b);
    public abstract boolean isInside (int x, int y);
    public abstract void paint (Graphics g);    
    public void drag (int x, int y){
        this.x = x;
        this.y = y;
    }
    public Figure (int x, int y, Color lineColor, Color bgColor){
        this.x = x;
        this.y = y;
        this.lineColor = lineColor;
        this.bgColor = bgColor;
    }
    public double triangleArea (int vx[], int vy[]){
        double a = vx[0]*(vy[1] - vy[2]);
        double b = vx[1]*(vy[2] - vy[0]);
        double c = vx[2]*(vy[0] - vy[1]);
        double d = (a + b + c)/2;
        if (d > 0) return d;
        return -d;
    }
}