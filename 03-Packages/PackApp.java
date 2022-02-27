import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1, r2;
    Ellipse e1, e2;
    RHexagon h1, h2, h3;
    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
        this.r1 = new Rect(100,170,100,30, Color.black, Color.white);
        this.r2 = new Rect(125,70,50,50, Color.yellow, Color.yellow);
        this.e1 = new Ellipse(100,100,100,30, Color.yellow, Color.black);
        this.h1 = new RHexagon(150,150,20,30, Color.white, Color.black);
        this.e2 = new Ellipse(125,200,50,50, Color.yellow, Color.yellow);
        this.h2 = new RHexagon(100,120,20,0, Color.red, Color.yellow);
        this.h3 = new RHexagon(200,120,20,0, Color.red, Color.yellow);
    }
    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
        this.h1.paint(g);
        this.e2.paint(g);
        this.h2.paint(g);
        this.h3.paint(g);
        this.r2.paint(g);
        this.r1.paint(g);
    }
}
