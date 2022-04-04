/*package figures;
import java.awt.*;

public class Line extends Figure{
    private int v1[] = {1,1};
    private int v2[] = {1,1};
    public Line (int v1x, int v1y, int v2x, int v2y, Color lineColor) {
        super((v1x+v2x)/2, (v1y+v2y)/2, lineColor, lineColor);
        if (v1x < v2x){
            this.v1[0] = v1x;
            this.v2[0] = v2x;
        }
        else {
            this.v1[0] = v2x;
            this.v2[0] = v1x;
        } 
        if (v1y < v2y){
            this.v1[1] = v1y;
            this.v2[1] = v2y;
        }
        else {
            this.v1[1] = v2y;
            this.v2[1] = v1y;
        }
    }
    public void sizeChange (int a, boolean b){
        if (b) {
            this.v1[0] -= a;
            this.v2[0] += a;
        } 
        else {
            this.v1[1] -= a;
            this.v2[1] += a;
        }
        while (this.v1[0] > this.v2[0]){
            this.v1[0] -= 1;
        } 
        while (this.v1[1] > this.v2[1]){
            this.v1[1] -= 1;
        }
        this.x = (this.v1[0]+this.v1[1])/2;
        this.y = (this.v2[0]+this.v2[1])/2;
    } 
    public boolean isInside (int x, int y) {
        return false;
    }
    public void print () {
        System.out.format("Linha com vertices em (%d,%d) e (%d,%d), de Cor %s.\n",
            this.v1[0], this.v1[1], this.v2[0], this.v2[1], this.lineColor);
    }
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.lineColor);
        g2d.drawLine(this.v1[0], this.v1[1], this.v2[0], this.v2[1]);
    }
                    if (evt.getKeyChar() == 'l'){
                        int x2 = x + rand.nextInt(100) - 50;
                        int y2 = y + rand.nextInt(100) - 50;
                        figuresList.add(new Line(x,y,x2,y2, colorList.get(c1)));
                    }
    
}*/