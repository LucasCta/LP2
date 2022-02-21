import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RectApp {
    public static void main (String[] args) {
        Rect r1 = new Rect(1,1, 10,10);
        r1.print();
        //testes 2.1.3:
        System.out.format("Area R1: (%d)\n",r1.area());
        r1.drag(20,-20);
        r1.print();
        Rect r2 = new Rect(100,100, 30,45);
        r2.print();
        r2.drag(-20,20);
        System.out.format("Area R2: (%d)\n",r2.area());
        r2.drag(5,-5);
        r2.print();
        //fim dos testes 2.1.3
    }
}

class Rect {
    int x, y;
    int w, h;
    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    int area (){
        return w*h;
    }
    void drag (int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
}
