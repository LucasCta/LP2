import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import figures.*;

class ObjectsList {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    ArrayList<Rect> rectList = new ArrayList<Rect>();
    ArrayList<Ellipse> ellipseList = new ArrayList<Ellipse>();
    ArrayList<Color> colorList = new ArrayList<Color>(){
      {
        add(Color.blue);
        add(Color.red);
        add(Color.yellow);
        add(Color.green);
        add(Color.white);
        add(Color.black);
      }
    };
    Random rand = new Random();
    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(350);
                    int y = rand.nextInt(350);
                    int w = rand.nextInt(100);
                    int h = rand.nextInt(100);
                    int c1 = rand.nextInt(6);
                    int c2 = rand.nextInt(6);
                    if (evt.getKeyChar() == 'r') {
                        rectList.add(new Rect(x,y,w,h, colorList.get(c1), colorList.get(c2)));
                        repaint(); //outer.repaint()
                    }
                    if (evt.getKeyChar() == 'e') {
                        ellipseList.add(new Ellipse(x,y,w,h, colorList.get(c1), colorList.get(c2)));
                        repaint(); //outer.repaint()
                    }
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
    }
    public void paint (Graphics g) {
        super.paint(g);
        for (int i = 0 ; i != ellipseList.size() + rectList.size() ; i++) {
            if (ellipseList.size() > i) {
              (ellipseList.get(i)).paint(g);
            }
            if (rectList.size() > i) {
              (rectList.get(i)).paint(g);
            }
        }
    }
}
