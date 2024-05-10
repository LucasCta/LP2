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
    public void sizeChange (int a, boolean b){
        if (b) {this.w += a;}
        else {this.h += a;}
        if (this.w < 10){this.w = 10;}
        if (this.h < 10){this.h = 10;}
    } 
    public boolean clicked (int x, int y) {
        double eCenter[] = {(this.x*2+this.w)/2,((this.y*2)+this.h)/2};
        double a = Math.pow(x - eCenter[0],2)/Math.pow(this.w/2,2);
        double b = Math.pow(y - eCenter[1],2)/Math.pow(this.h/2,2);
        return a+b <= 1;
    }
    public void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
        System.out.format("Background Color: %s, Line Color: %s",this.bgColor,this.lineColor);
    }
    public String save (){
        double eCenter[] = {(this.x*2+this.w)/2,((this.y*2)+this.h)/2};
        double a = this.w/2;
        double b = this.h/2;
        String c1 = String.format("#%06x", this.bgColor.getRGB() & 0xFFFFFF);
        String c2 = String.format("#%06x", this.lineColor.getRGB() & 0xFFFFFF);
        return String.format("<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" style=\"fill:%s;stroke:%s;stroke-width:1\"/>\n",eCenter[0],eCenter[1],a,b,c1,c2).replace(',','.');
    }
    public void paint (Graphics g, boolean focus) {
        Graphics2D g2d = (Graphics2D) g;
        if (this.bgColor != null){
            g2d.setColor(this.bgColor);
            g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        }
        g2d.setColor(this.lineColor);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        if (focus){
            g2d.setColor(Color.red);
            g2d.draw(new Ellipse2D.Double(this.x-3,this.y-3,this.w+6,this.h+6));
        }
    }
}
