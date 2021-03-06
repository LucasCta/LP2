package figures;
import java.awt.*;

public class RHexagon extends Figure{
    private int r, a;
    private int vx[] = {1,1,1,1,1,1};
    private int vy[] = {1,1,1,1,1,1};
    public RHexagon (int x, int y, int r, int a, Color lineColor, Color bgColor) {
      super(x,y,lineColor,bgColor);
      this.r = r;
      this.a = a;
      for (int i = 0; i < 6; i++){
        this.vx[i] = (int)(vx[i]*r*Math.cos(Math.toRadians(a+i*60))) + x;
        this.vy[i] = (int)(vy[i]*r*Math.sin(Math.toRadians(a+i*60))) + y;
      }
    }
    public void print () {
      System.out.format("Hexagon at (%d,%d), Radius %d, Rotation %d, with Vertices: ",
        this.x, this.y, this.r, this.a);
      for (int i = 0; i < 6; i++){
        System.out.format("(%d,%d)",this.vx[i],this.vy[i]);
      }
      System.out.format("\nBackground Color: %s, Line Color: %s",this.bgColor,this.lineColor);
    }
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.bgColor);
        g2d.fillPolygon(new Polygon(vx, vy, 6));
        g2d.setColor(this.lineColor);
        g2d.drawPolygon(new Polygon(vx, vy, 6));
    }
}
