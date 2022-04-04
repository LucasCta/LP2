package figures;
import java.awt.*;

public class Triangle extends Figure{
    private int r, rot;
    private int vx[] = {1,1,1};
    private int vy[] = {1,1,1};
    private void setVertices(){
      for (int i = 0; i < 3; i++){
        this.vx[i] = (int)(this.r*Math.cos(Math.toRadians(this.rot+i*120))) + this.x;
        this.vy[i] = (int)(this.r*Math.sin(Math.toRadians(this.rot+i*120))) + this.y;
      }      
    }
    public Triangle (int x, int y, int r, int rot, Color lineColor, Color bgColor) {
      super(x,y,lineColor,bgColor);
      this.r = r;
      this.rot = rot;
      setVertices();
    }
    public Triangle copy () {
      return new Triangle(this.x, this.y, this.r, this.rot, this.lineColor, this.bgColor);
    }
    public void drag (int x, int y){
      this.x = x;
      this.y = y;
      setVertices();
  };
    public void sizeChange (int a, boolean b){
        this.r += a;
        if (this.r < 10){this.r = 10;}
        setVertices();
    } 
    public boolean isInside (int x, int y) {
        return false;
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
        if (this.bgColor != null){
          g2d.setColor(this.bgColor);
          g2d.fillPolygon(new Polygon(vx, vy, 3));
        }
        g2d.setColor(this.lineColor);
        g2d.drawPolygon(new Polygon(vx, vy, 3));
    }
}