package figures;
import java.awt.*;

public abstract class Figure {
    public int x, y;
    public Color lineColor, bgColor;
    public abstract void paint (Graphics g);
    public Figure (int x, int y, Color lineColor, Color bgColor){
        this.x = x;
        this.y = y;
        this.lineColor = lineColor;
        this.bgColor = bgColor;
    }
}