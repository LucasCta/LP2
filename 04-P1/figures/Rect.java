package figures;
import java.awt.*;

public class Rect extends Figure{
    private int w, h;
    public Rect (int x, int y, int w, int h, Color lineColor, Color bgColor) {
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
        double pArea = 0;
        double rArea = w * h;
        int vx[] = {x, this.x, this.x + this.w};
        int vy[] = {y, this.y, this.y};
        pArea += triangleArea(vx,vy);
        vx[1] = this.x + this.w;
        vy[2] = this.y + this.h;
        pArea += triangleArea(vx,vy);
        vx[2] = this.x;
        vy[1] = this.y + this.h;
        pArea += triangleArea(vx,vy);
        vx[1] = this.x;
        vy[2] = this.y;
        pArea += triangleArea(vx,vy);
        return rArea == pArea;
    }
    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
        System.out.format("Background Color: %s, Line Color: %s",this.bgColor,this.lineColor);
    }
    public String save (){
        String c1 = String.format("#%06x", this.bgColor.getRGB() & 0xFFFFFF);
        String c2 = String.format("#%06x", this.lineColor.getRGB() & 0xFFFFFF);
        return String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"fill:%s;stroke:%s;stroke-width:1\"/>\n",this.x,this.y,this.w,this.h,c1,c2);
    }
    public void paint (Graphics g, boolean focus) {
        Graphics2D g2d = (Graphics2D) g;
        if (this.bgColor != null){
            g2d.setColor(this.bgColor);
            g2d.fillRect(this.x,this.y, this.w,this.h);
        }
        g2d.setColor(this.lineColor);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        if (focus){
            g2d.setColor(Color.red);
            g2d.drawRect(this.x-3,this.y-3,this.w+6,this.h+6);
        }
    }
}