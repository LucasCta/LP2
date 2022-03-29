package figures;
import java.awt.*;

public class Line extends Figure{
    private int v1[] = {1,1};
    private int v2[] = {1,1};
    public Line (int v1x, int v1y, int v2x, int v2y, Color lineColor) {
        super(v1x+v2x/2, v1y+v2y/2, lineColor, lineColor);
        this.v1[0] = v1x;
        this.v1[1] = v1y;
        this.v2[0] = v2x;
        this.v2[1] = v2y;
        if (v1x < v2x){this.container[0] = v1x; this.container[1] = v2x;}
        else{this.container[1] = v1x; this.container[0] = v2x;}
        if (v1y < v2y){this.container[2] = v1y; this.container[3] = v2y;}
        else{this.container[3] = v1y; this.container[2] = v2y;}
    }
    public void print () {
        System.out.format("Linha com vertices em (%d,%d) e (%d,%d), de Cor %s.\n",
            this.v1[0], this.v1[1], this.v2[0], this.v2[1], this.lineColor);
    }
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.lineColor);
        g2d.drawLine(this.v1[0], this.v1[1], this.v2[0], this.v2[1]);
    }
}