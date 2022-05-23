import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private ArrayList<Figure> figuresList = new ArrayList<Figure>();
    private ArrayList<Color> colorList = new ArrayList<Color>(){{
        add(Color.blue);
        add(Color.red);
        add(Color.yellow);
        add(Color.green);
        add(Color.white);
        add(Color.black);
    }};
    private Button focus_butFig;
    private ArrayList<Button> menu = new ArrayList<Button>(){{
        add(new Button(0, new Rect(0,0,120,170, Color.darkGray, Color.LIGHT_GRAY)));
        add(new Button(0, new Rect(30,170,60,20,Color.darkGray,Color.lightGray)));
        add(new Button(1, new Rect(30,40,30,30, Color.darkGray, Color.darkGray)));
        add(new Button(1, new Ellipse(70,40,30,30, Color.darkGray, Color.darkGray)));
        add(new Button(1, new RHexagon(45,100,15,0, Color.darkGray, Color.darkGray)));
        add(new Button(1, new Triangle(85,105,15,30, Color.darkGray, Color.darkGray)));
        for(int i = 0; i < 6; i++){
            add(new Button(2, new Rect(20+i*15,150,10,10, colorList.get(i), Color.darkGray)));
        }
        for(int i = 0; i < 6; i++){
            add(new Button(3, new Rect(20+i*15,130,10,10, Color.darkGray, colorList.get(i))));
        }
        add(new Button(4, new Ellipse(40,175,10,10,Color.darkGray,Color.orange)));
        add(new Button(5, new Ellipse(55,175,10,10,Color.darkGray,Color.red)));
        add(new Button(6, new Ellipse(70,175,10,10,Color.darkGray,Color.green)));
    }};
    private Button focus_butLn = this.menu.get(6);
    private Button focus_butBg = this.menu.get(12);
    private Random rand = new Random();
    private void addFigure(int type, int c1, int c2){
        int c = rand.nextInt(30)+20;
        int d = rand.nextInt(30)+20;
        switch (type) {
            case 0: figuresList.add(new Rect(mouse[0]-2,mouse[1]-2,c+10,d+10, 
                        colorList.get(c1), colorList.get(c2))); break;
            case 1: figuresList.add(new Ellipse(mouse[0]-10,mouse[1]-10,c+10,d+10, 
                        colorList.get(c1), colorList.get(c2))); break;
            case 2: figuresList.add(new RHexagon(mouse[0],mouse[1],c,d, 
                        colorList.get(c1), colorList.get(c2))); break;
            case 3: figuresList.add(new Triangle(mouse[0],mouse[1],c,d, 
                        colorList.get(c1), colorList.get(c2))); break;
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
                if (menu.get(0).clicked(e.getX(),e.getY()) || menu.get(1).clicked(e.getX(),e.getY())){
                    for (Button but: menu) {
                        if (but.clicked(e.getX(),e.getY())){
                            switch (but.indice) {
                                case 1: focus_butFig = but; break;
                                case 2: 
                                    focus_butLn= but; 
                                    if (focus != null){
                                        focus.lineColor = colorList.get(menu.indexOf(focus_butLn)-6);
                                    }
                                    break;
                                case 3: 
                                    focus_butBg = but; 
                                    if (focus != null){
                                        focus.bgColor = colorList.get(menu.indexOf(focus_butBg)-12);
                                    }
                                    break;
                                case 4: 
                                    if (focus != null){
                                        figuresList.remove(focus); 
                                        focus = null;
                                    } break;
                                case 5:
                                    figuresList.clear();
                                    focus = null;
                                    break; 
                                case 6:
                                    try {
                                        String output = "<svg width=\"500\" height=\"500\">\n";
                                        for (Figure fig: figuresList){
                                            output += fig.save();
                                        } output += "</svg>";
                                        Path path = Paths.get("saved.svg");
                                        Files.writeString(path, output, StandardCharsets.UTF_8);
                                    } catch (IOException x) {
                                        System.out.print("Invalid Path");
                                    }
                                    break;
                            }
                        }
                    }
                } else if (focus_butFig != null) {
                    addFigure(menu.indexOf(focus_butFig)-2,menu.indexOf(focus_butLn)-6,
                            menu.indexOf(focus_butBg)-12);
                    focus_butFig = null;
                }
                focus = null;
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
                    int c1 = rand.nextInt(6);
                    int c2 = rand.nextInt(6);
                    if (evt.getKeyChar() == 'r') addFigure(0,c1,c2);
                    if (evt.getKeyChar() == 'e') addFigure(1,c1,c2);
                    if (evt.getKeyChar() == 'h') addFigure(2,c1,c2);
                    if (evt.getKeyChar() == 't') addFigure(3,c1,c2);
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
        for (Button but: this.menu) {
            but.paint(g,focus_butFig == but || 
                        focus_butLn == but || 
                        focus_butBg == but);
        }
    }
}