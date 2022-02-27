import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1;
    Rect r2;
    Rect r3;
    Rect r4;
    Rect r5;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30, Color.red, Color.blue);
        this.r2 = new Rect(100,200, 30,40, new Color(0,255,0), new Color(100,100,100));
        this.r3 = new Rect(170,121, 150,100, new Color(0,0,0), new Color(0,0,0));
        this.r4 = new Rect(150,150, 123,123, new Color(255,255,255), new Color(255,255,255));
        this.r5 = new Rect(250, 50, 40,50, new Color(29,113,196), new Color(135,44,171));
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.r4.paint(g);
        this.r5.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;
    Color lineColor, bgColor;

    Rect (int x, int y, int w, int h, Color lineColor, Color bgColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.lineColor = lineColor;
        this.bgColor = bgColor;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.bgColor);
        g2d.fillRect(this.x,this.y, this.w,this.h);
        g2d.setColor(this.lineColor);
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }

}
