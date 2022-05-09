import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import figures.*;

class Project1 {
    public static void main (String[] args) {
        ArrFigures frame = new ArrFigures();
        frame.loadFigures();
        frame.setVisible(true);
    }
}

class ArrFigures extends JFrame {
    private int mouse[] = {0,0};
    private Figure focus = null;
    private Random rand = new Random();
    private ArrayList<Figure> figuresList = new ArrayList<Figure>();
    public void loadFigures(){
        try {
            FileInputStream fis = new FileInputStream("proj.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Figure> temp = (ArrayList<Figure>) ois.readObject();
            if (temp != null) figuresList = temp;
            ois.close();
        } catch(FileNotFoundException exception) {
            exception.printStackTrace();
        } catch(IOException exception) {
            exception.printStackTrace();
        } catch(ClassNotFoundException exception){
            exception.printStackTrace(); 
        }
    };
    private ArrayList<Color> colorList = new ArrayList<Color>(){{
        add(Color.blue);
        add(Color.red);
        add(Color.yellow);
        add(Color.green);
        add(Color.white);
        add(Color.black);
    }};
    ArrFigures () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    try {
                        FileOutputStream fos = new FileOutputStream("proj.bin");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(figuresList);
                        oos.close();
                    } catch(FileNotFoundException exception) {
                        exception.printStackTrace();
                    } catch(IOException exception) {
                        exception.printStackTrace();
                    }
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
                    if (fig.clicked(e.getX(),e.getY())){
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
            public void mouseDragged (MouseEvent e) {
                if (focus != null){
                    if (e.isControlDown()){
                        focus.sizeChange(e.getX()-mouse[0],true);
                        focus.sizeChange(e.getY()-mouse[1],false);
                    }
                    else focus.drag(focus.x+e.getX()-mouse[0],focus.y+e.getY()-mouse[1]); 
                    mouse[0] = e.getX();
                    mouse[1] = e.getY();
                    repaint(); //outer.repaint()
                }
            }
            public void mouseMoved (MouseEvent e) {
                mouse[0] = e.getX();
                mouse[1] = e.getY();
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
                    if (evt.getKeyChar() ==  KeyEvent.VK_TAB){
                        if (figuresList.size() != 0){
                            if (focus != null) focus.highlight = false;
                            focus = figuresList.get(0); 
                            focus.highlight = true;
                            figuresList.add(focus);
                            figuresList.remove(focus);
                            focus = figuresList.get(figuresList.size()-1); 
                        }
                    }
                    if (evt.getKeyChar() == 'x' && focus != null){
                        figuresList.remove(focus);
                        focus = null;
                    }
                    if (evt.getKeyChar() == '+' && focus != null){
                        boolean b = false;
                        if (evt.isControlDown()) b = true;
                        focus.sizeChange(1,b); 
                    }
                    if (evt.getKeyChar() == '-' && focus != null){
                        boolean b = false;
                        if (evt.isControlDown()) b = true;
                        focus.sizeChange(-1,b); 
                    }
                    for (int i = 0; i < 6; i++){
                        char c[] = {'1','2','3','4','5','6'};
                        if (evt.getKeyChar() == c[i] && focus != null){
                            if (evt.isControlDown()){
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
        this.setFocusTraversalKeysEnabled(false);
    }
    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figuresList) {
            fig.paint(g);
        }
    }
}