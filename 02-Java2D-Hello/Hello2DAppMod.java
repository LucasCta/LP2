import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DAppMod {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(350, 350);
        this.setVisible(true);
    }

    public void paint (Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        getContentPane().setBackground(new Color(176,132,93));

        g2d.setPaint(Color.yellow);
        int w = getWidth();
        int h = getHeight();
        g2d.drawLine(0,0, w,h);
        g2d.drawLine(0,h, w,0);

        g2d.setPaint(Color.red);
        Rectangle rectangle = new Rectangle(w/2, h/2, w, h);
        g2d.draw(rectangle);
        Rectangle rectangle2 = new Rectangle(0, 0, w/2, h/2);
        g2d.draw(rectangle2);

        g2d.setPaint(Color.green);
        g2d.drawOval(w/4, h/4, w/2, h/2);
        g2d.drawOval(w/3, (h/30)*13, w/32, h/32);
        g2d.drawOval((w/30)*17, h/3, w/32, h/32);
        g2d.drawOval((w/30)*13, 2*h/3, w/32, h/32);
        g2d.drawOval(2*w/3, (h/30)*17, w/32, h/32);

    }
}
