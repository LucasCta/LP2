package figures;
import java.awt.*;

public class RHexagon extends Figure{
    private int r, rot;
    private int vx[] = {1,1,1,1,1,1};
    private int vy[] = {1,1,1,1,1,1};
    private void setVertices(){
      for (int i = 0; i < 6; i++){
        this.vx[i] = (int)(this.r*Math.cos(Math.toRadians(this.rot+i*60))) + this.x;
        this.vy[i] = (int)(this.r*Math.sin(Math.toRadians(this.rot+i*60))) + this.y;
      }   
    }
    public RHexagon (int x, int y, int r, int rot, Color lineColor, Color bgColor) {
      super(x,y,lineColor,bgColor);
      this.r = r;
      this.rot = rot;
      setVertices();
    } 
    public void drag (int x, int y){
        this.x += x;
        this.y += y;
        setVertices();
    }
    public void sizeChange (int a, boolean b){
        this.r += a;
        if (this.r < 10){this.r = 10;}
        setVertices();
    } 
    public boolean clicked (int x, int y) {
        double pArea[] = {0,0};
        int xv[] = {x, 0, 0};
        int yv[] = {y, 0, 0};
        for (int j = 0; j < 2; j++){
          if (j == 1){
            xv[0] = this.x;
            yv[0] = this.y;
          }
          for (int i = 0; i < 5; i ++){
            xv[1] = vx[i];
            yv[1] = vy[i];
            xv[2] = vx[i+1];
            yv[2] = vy[i+1];
            pArea[j] += triangleArea(xv,yv);
          }  
          xv[1] = vx[5];
          xv[2] = vx[0];
          yv[1] = vy[5];
          yv[2] = vy[0];
          pArea[j] += triangleArea(xv,yv);
        }
        return pArea[0] == pArea[1];
    }
    public void print () {
      System.out.format("Hexagon at (%d,%d), Radius %d, Rotation %d, with Vertices: ",
        this.x, this.y, this.r, this.rot);
      for (int i = 0; i < 6; i++){
        System.out.format("(%d,%d)",this.vx[i],this.vy[i]);
      }
      System.out.format("\nBackground Color: %s, Line Color: %s",this.bgColor,this.lineColor);
    }
    public String save(){
      String c1 = String.format("#%06x", this.bgColor.getRGB() & 0xFFFFFF);
      String c2 = String.format("#%06x", this.lineColor.getRGB() & 0xFFFFFF);
      return String.format("<polygon points=\"%d,%d %d,%d %d,%d %d,%d %d,%d %d,%d\" style=\"fill:%s;stroke:%s;stroke-width:1\"/>\n",
                            this.vx[0],this.vy[0],this.vx[1],this.vy[1],this.vx[2],this.vy[2],
                            this.vx[3],this.vy[3],this.vx[4],this.vy[4],this.vx[5],this.vy[5],c1,c2);
    }
    public void paint (Graphics g, boolean focus) {
        Graphics2D g2d = (Graphics2D) g;
        if (this.bgColor != null){
          g2d.setColor(this.bgColor);
          g2d.fillPolygon(new Polygon(vx, vy, 6));
        }
        g2d.setColor(this.lineColor);
        g2d.drawPolygon(new Polygon(vx, vy, 6));
        if (focus){
          g2d.setColor(Color.red);
          this.r += 4;
          setVertices();
          g2d.drawPolygon(new Polygon(vx, vy, 6));
          this.r -= 4;
          setVertices();
        }
    }
}
