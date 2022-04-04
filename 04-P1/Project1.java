import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import figures.*;

class Project1 {
    public static void main (String[] args) {
        ArrFigures frame = new ArrFigures();
        frame.setVisible(true);
    }
}

class ArrFigures extends JFrame {
    Figure focus = null;
    int mouse[] = {0,0};
    ArrayList<Figure> figuresList= new ArrayList<Figure>();
    ArrayList<Color> colorList = new ArrayList<Color>(){{
        add(Color.blue);
        add(Color.red);
        add(Color.yellow);
        add(Color.green);
        add(Color.white);
        add(Color.black);
    }};
    Random rand = new Random();
    ArrFigures () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );        
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent e) {
                if (focus != null){
                    focus.highlight = false;
                    focus = null;
                }
                for (Figure fig: figuresList) {
                    if (fig.isInside(e.getX(),e.getY())){
                        if (focus != null){
                            focus.highlight = false;
                        }
                        focus = fig;
                        focus.highlight = true;
                    }
                }
                if (focus != null) {
                    figuresList.add(focus);
                    figuresList.remove(focus);
                    focus = figuresList.get(figuresList.size()-1); 
                }
                repaint(); //outer.repaint()
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved (MouseEvent e) {
                mouse[0] = e.getX();
                mouse[1] = e.getY();
            }
            public void mouseDragged (MouseEvent e) {
               if (focus != null){
                   focus.drag(e.getX(),e.getY());
                   repaint(); //outer.repaint()
                }
            }
        });
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = mouse[0];
                    int y = mouse[1];
                    int w = rand.nextInt(40) + 10;
                    int h = rand.nextInt(40) + 10;
                    int c1 = rand.nextInt(6);
                    int c2 = rand.nextInt(6);
                    if (evt.getKeyChar() == 'r') {
                        figuresList.add(new Rect(x,y,w,h, colorList.get(c1), colorList.get(c2)));
                    }
                    if (evt.getKeyChar() == 'e') {
                        figuresList.add(new Ellipse(x,y,w,h, colorList.get(c1), colorList.get(c2)));
                    }
                    if (evt.getKeyChar() == 'h'){
                        figuresList.add(new RHexagon(x,y,w,h, colorList.get(c1), colorList.get(c2)));
                    }                    
                    if (evt.getKeyChar() == 't'){
                        figuresList.add(new Triangle(x,y,w,h, colorList.get(c1), colorList.get(c2)));
                    }
                    if (evt.getKeyChar() == 'x' && focus != null){
                        figuresList.remove(focus);
                        focus = null;
                    }
                    if (evt.getKeyChar() == '+' && focus != null){
                        boolean b = false;
                        if(evt.isControlDown()){b = true;}
                        focus.sizeChange(1,b); 
                    }
                    if (evt.getKeyChar() == '-' && focus != null){
                        boolean b = false;
                        if(evt.isControlDown()){b = true;}
                        focus.sizeChange(-1,b); 
                    }
                    for (int i = 0; i < 6; i++){
                        char c[] = {'1','2','3','4','5','6'};
                        if (evt.getKeyChar() == c[i] && focus != null){
                            if(evt.isControlDown()){
                                focus.lineColor = colorList.get(i);
                            }
                            else{
                                focus.bgColor = colorList.get(i);
                            }
                        }
                    }
                    repaint(); //outer.repaint()
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
    }
    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figuresList) {
            fig.paint(g);
        }
    }
}