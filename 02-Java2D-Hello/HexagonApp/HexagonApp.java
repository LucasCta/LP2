import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class HexagonApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Hexagon hex1;
    Hexagon hex2;
    Hexagon hex3;
    Hexagon hex4;
    Hexagon hex5;
    Hexagon hex6;
    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("HexagonApp");
        this.setSize(350, 350);
        this.hex1 = new Hexagon(125,275,50,0,Color.blue,Color.red);
        this.hex2 = new Hexagon(133,205,20,30,Color.yellow,Color.orange);
        this.hex3 = new Hexagon(75,155,45,45,new Color(0,255,0),new Color(0,255,0));
        this.hex4 = new Hexagon(43,223,15,15,new Color(0,122,0),new Color(122,0,122));
        this.hex5 = new Hexagon(232,142,100,-10,Color.white,Color.black);
        this.hex6 = new Hexagon(200,220,70,30,new Color(122,0,122),new Color(0,122,0));
    }
    public void paint (Graphics g) {
        super.paint(g);
        this.hex1.paint(g);
        this.hex2.paint(g);
        this.hex3.paint(g);
        this.hex4.paint(g);
        this.hex5.paint(g);
        this.hex6.paint(g);
    }
}

class Hexagon {
    int x, y, r, a;
    int vx[] = {1,1,1,1,1,1};
    int vy[] = {1,1,1,1,1,1};
    Color bgColor, lineColor;
    Hexagon (int x, int y, int r, int a, Color bg, Color ln) {
      this.x = x;
      this.y = y;
      this.r = r;
      this.a = a;
      for (int i = 0; i < 6; i++){
        this.vx[i] = (int)(vx[i]*r*Math.cos(Math.toRadians(a+i*60))) + x;
        this.vy[i] = (int)(vy[i]*r*Math.sin(Math.toRadians(a+i*60))) + y;
      }
      this.bgColor = bg;
      this.lineColor = ln;
    }
    void print () {
      System.out.format("Hexagon at (%d,%d), Radius %d, Rotation %d, with Vertices: ",
        this.x, this.y, this.r, this.a);
      for (int i = 0; i < 6; i++){
        System.out.format("(%d,%d)",this.vx[i],this.vy[i]);
      }
      System.out.format("\nBackground Color: %s, Line Color: %s",this.bgColor,this.lineColor);
    }
    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.bgColor);
        g2d.fillPolygon(new Polygon(vx, vy, 6));
        g2d.setColor(this.lineColor);
        g2d.drawPolygon(new Polygon(vx, vy, 6));
    }
}
