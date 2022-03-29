package figures;
import java.awt.*;

public class Triangle extends Figure{
    private int r, rot;
    private int vx[] = {1,1,1};
    private int vy[] = {1,1,1};
    public Triangle (int x, int y, int r, int rot, Color lineColor, Color bgColor) {
      super(x,y,lineColor,bgColor);
      this.r = r;
      this.rot = rot;
      for (int i = 0; i < 3; i++){
        this.vx[i] = (int)(vx[i]*r*Math.cos(Math.toRadians(rot+i*120))) + x;
        this.vy[i] = (int)(vy[i]*r*Math.sin(Math.toRadians(rot+i*120))) + y;
      }      
      this.container[0] = vx[0];
      this.container[1] = vx[0];
      this.container[2] = vy[0];
      this.container[3] = vy[0];    
      for (int i = 1; i < 3; i++){
        if (this.vx[i] < this.container[0]){this.container[0]=this.vx[i];}
        if (this.vx[i] > this.container[1]){this.container[1]=this.vx[i];}
        if (this.vy[i] < this.container[2]){this.container[2]=this.vy[i];}
        if (this.vy[i] > this.container[3]){this.container[3]=this.vy[i];}
      }
    }
    public void print () {
      System.out.format("Triangle at (%d,%d), Radius %d, Rotation %d, with Vertices: ",
        this.x, this.y, this.r, this.rot);
      for (int i = 0; i < 3; i++){
        System.out.format("(%d,%d)",this.vx[i],this.vy[i]);
      }
      System.out.format("\nBackground Color: %s, Line Color: %s",this.bgColor,this.lineColor);
    }
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.bgColor);
        g2d.fillPolygon(new Polygon(vx, vy, 3));
        g2d.setColor(this.lineColor);
        g2d.drawPolygon(new Polygon(vx, vy, 3));
    }
}