import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class ElipseApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Ellipse e1;
    Ellipse e2;
    Ellipse e3;
    Ellipse e4;
    Ellipse e5;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("ElipseApp");
        this.setSize(350, 350);
        this.e1 = new Ellipse(50,100, 100,30, Color.blue, Color.red);
        this.e2 = new Ellipse(30,50, 30,30, new Color(70,140,210), new Color(210,140,70));
        this.e3 = new Ellipse(140,200, 40,100, new Color(0,255,0), new Color(0,255,0));
        this.e4 = new Ellipse(200,50, 50, 150, Color.black, Color.white);
        this.e5 = new Ellipse(150,50, 150,50, Color.white, Color.black);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
        this.e4.paint(g);
        this.e5.paint(g);
    }
}

class Ellipse {
    int x, y;
    int w, h;
    Color lineColor, bgColor;

    Ellipse (int x, int y, int w, int h, Color lineColor, Color bgColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.lineColor = lineColor;
        this.bgColor = bgColor;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.bgColor);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(this.lineColor);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }

}
