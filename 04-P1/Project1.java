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
    Figure highlight = null;
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
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved (MouseEvent e) {
                mouse[0] = e.getX();
                mouse[1] = e.getY();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent e) {
                if (focus != null){
                    focus = null;
                    highlight= null;
                }
                for (Figure fig: figuresList) {
                    if (e.getX() > fig.container[0] && e.getX() < fig.container[1] 
                        &&  e.getY() > fig.container[2] && e.getY() < fig.container[3]){
                        highlight = new Rect(fig.container[0],fig.container[2],
                            fig.container[1]-fig.container[0],fig.container[3]-fig.container[2],
                            Color.pink,null); 
                        focus = fig;
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
                    if (evt.getKeyChar() == 'l'){
                        int x2 = x + rand.nextInt(100) - 50;
                        int y2 = y + rand.nextInt(100) - 50;
                        figuresList.add(new Line(x,y,x2,y2, colorList.get(c1)));
                    }
                    if (evt.getKeyChar() == 'x' && focus != null){
                        figuresList.remove(focus);
                        focus = null;
                        highlight = null;
                    }
                    for (int i = 0; i < 6; i++){
                        char c[] = {'1','2','3','4','5','6'};
                        if (evt.getKeyChar() == c[i] && focus != null){
                            for (Figure fig: figuresList){
                                if (fig == focus){
                                    if(evt.isControlDown()){
                                        fig.lineColor = colorList.get(i);
                                    }
                                    else{
                                        fig.bgColor = colorList.get(i);
                                    }
                                } 
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
            if (highlight != null){
                highlight.paint(g);
            }
        }
    }
}