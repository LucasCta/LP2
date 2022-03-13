package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure{
    private int w, h;
    public Ellipse (int x, int y, int w, int h, Color lineColor, Color bgColor) {
        super(x,y,lineColor,bgColor);
        this.w = w;
        this.h = h;
    }
    public void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
        System.out.format("Background Color: %s, Line Color: %s",this.bgColor,this.lineColor);
    }
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.bgColor);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(this.lineColor);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}