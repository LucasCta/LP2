import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HexagonApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Hexagon hex1;
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
    }
    public void paint (Graphics g) {
        super.paint(g);
    }
}

class Hexagon {
    int x, y;
    Hexagon (int x, int y) {
      this.x = x;
      this.y = y;
    }
    void print () {
        System.out.format("");
    }
    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }
}
