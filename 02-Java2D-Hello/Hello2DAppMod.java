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

        g2d.setPaint(Color.blue);
        int w = getWidth();
        int h = getHeight();
        g2d.drawLine(0,0, w,h);
        g2d.drawLine(0,h, w,0);

        g2d.setPaint(Color.red);
        Rectangle rectangle = new Rectangle(w/2, h/2, w, h);
        g2d.draw(rectangle);

    }
}
