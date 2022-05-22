import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import figures.*;

class Project1 {
    public static void main (String[] args) {
        ArrFigures frame = new ArrFigures();
        frame.setVisible(true);
    }
}

class ArrFigures extends JFrame {
    private int mouse[] = {0,0};
    private Figure focus = null;
    private Random rand = new Random();
    private ArrayList<Figure> figuresList = new ArrayList<Figure>();
    private ArrayList<Color> colorList = new ArrayList<Color>(){{
        add(Color.blue);
        add(Color.red);
        add(Color.yellow);
        add(Color.green);
        add(Color.white);
        add(Color.black);
    }};
    private ArrayList<Button> butFigs = new ArrayList<Button>(){{
        add(new Button(0, new Rect(30,40,30,30, Color.darkGray, Color.darkGray)));
        add(new Button(1, new Ellipse(70,40,30,30, Color.darkGray, Color.darkGray)));
        add(new Button(2, new RHexagon(45,100,15,0, Color.darkGray, Color.darkGray)));
        add(new Button(3, new Triangle(85,105,15,30, Color.darkGray, Color.darkGray)));   
    }};
    private ArrayList<Button> butBgColors = new ArrayList<Button>(){{ for(int i = 0; i < 6; i++){
        add(new Button(i, new Rect(20+i*15,130,10,10, Color.darkGray, colorList.get(i))));
    }}};
    private ArrayList<Button> butLnColors = new ArrayList<Button>(){{ for(int i = 0; i < 6; i++){
            add(new Button(i, new Rect(20+i*15,150,10,10, colorList.get(i), Color.darkGray)));
    }}};
    private Figure menu_canvas = new Rect(0,0,120,170, Color.darkGray, Color.LIGHT_GRAY);
    private Button focus_butLn = this.butLnColors.get(0);
    private Button focus_butBg = this.butBgColors.get(0);
    private Button focus_butFig = this.butFigs.get(0);
    private void addFigure(int type, int a, int b, int c, int d, int e, int f){
        switch (type) {
            case 0: figuresList.add(new Rect(a-2,b-2,c+10,d+10, colorList.get(e), colorList.get(f))); break;
            case 1: figuresList.add(new Ellipse(a-10,b-10,c+10,d+10, colorList.get(e), colorList.get(f))); break;
            case 2: figuresList.add(new RHexagon(a,b,c,d, colorList.get(e), colorList.get(f))); break;
            case 3: figuresList.add(new Triangle(a,b,c,d, colorList.get(e), colorList.get(f))); break;
        } return;
    }
    ArrFigures () {
        try {
            FileInputStream fis = new FileInputStream("proj.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.figuresList = (ArrayList<Figure>) ois.readObject();
            ois.close();
        } catch(Exception x) {
            System.out.println("Erro ao carregar figuras.");
        } 
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    try {
                        FileOutputStream fos = new FileOutputStream("proj.bin");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(figuresList);
                        oos.flush();
                        oos.close();
                    } catch(Exception x) {
                        System.out.println("Erro ao salvar figuras.");
                    }
                    System.exit(0);
                }
            }
        );        
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent e) {
                for (Button but: butFigs) {
                    if (but.clicked(e.getX(),e.getY())){
                        focus_butFig = but;
                    }
                }
                for (Button but: butLnColors) {
                    if (but.clicked(e.getX(),e.getY())){
                        focus_butLn= but;
                    }
                }
                for (Button but: butBgColors) {
                    if (but.clicked(e.getX(),e.getY())){
                        focus_butBg = but;
                    }
                }
                if (menu_canvas.clicked(e.getX(),e.getY()) == false && focus_butFig != null){
                    addFigure(focus_butFig.indice, mouse[0], mouse[1],
                                rand.nextInt(30)+20,rand.nextInt(30)+20,
                                focus_butLn.indice, focus_butBg.indice);
                    focus_butFig = null;
                }
                if (focus != null) focus = null;
                for (Figure fig: figuresList) {
                    if (fig.clicked(e.getX(),e.getY())){
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
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged (MouseEvent e) {
                if (focus != null){
                    if (e.isControlDown()){
                        focus.sizeChange(e.getX()-mouse[0],true);
                        focus.sizeChange(e.getY()-mouse[1],false);
                    }
                    else focus.drag(e.getX()-mouse[0],e.getY()-mouse[1]); 
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
                    int w = rand.nextInt(30) + 20;
                    int h = rand.nextInt(30) + 20;
                    int c1 = rand.nextInt(6);
                    int c2 = rand.nextInt(6);
                    if (evt.getKeyChar() == 'r') {
                        addFigure(0,x,y,w,h,c1,c2);
                    }
                    if (evt.getKeyChar() == 'e') {
                        addFigure(1,x,y,w,h,c1,c2);
                    }
                    if (evt.getKeyChar() == 'h'){
                        addFigure(2,x,y,w,h,c1,c2);
                    }                    
                    if (evt.getKeyChar() == 't'){
                        addFigure(3,x,y,w,h,c1,c2);
                    }
                    if (evt.getKeyChar() ==  KeyEvent.VK_TAB){
                        if (figuresList.size() != 0){
                            focus = figuresList.get(0); 
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
                            } else{
                                focus.bgColor = colorList.get(i);
                            }
                        }
                    }
                    repaint(); //outer.repaint()
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(500, 500);
        this.setFocusTraversalKeysEnabled(false);
    }
    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figuresList) {
            fig.paint(g, focus == fig);
        }
        menu_canvas.paint(g, false);
        for (Button but: this.butFigs) {
            but.paint(g, focus_butFig == but);
        }
        for (Button but: this.butLnColors) {
            but.paint(g, focus_butLn == but);
        }
        for (Button but: this.butBgColors) {
            but.paint(g, focus_butBg == but);
        }
    }
}