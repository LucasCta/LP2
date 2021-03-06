package figures;
import java.awt.*;

public class Rect extends Figure{
    private int w, h;
    public int area () {return w*h;}
    public void drag (int dx, int dy) {this.x += dx;this.y += dy;}
    public Rect (int x, int y, int w, int h, Color lineColor, Color bgColor) {
        super(x,y,lineColor,bgColor);
        this.w = w;
        this.h = h;
    }
    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
        System.out.format("Background Color: %s, Line Color: %s",this.bgColor,this.lineColor);
    }
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.bgColor);
        g2d.fillRect(this.x,this.y, this.w,this.h);
        g2d.setColor(this.lineColor);
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}