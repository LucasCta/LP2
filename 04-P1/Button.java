import ivisible.IVisible;
import figures.Figure;
import java.awt.*;

public class Button implements IVisible{
    public int indice;
    private Figure fig;
    public Button (int indice, Figure fig){
        this.indice = indice;
        this.fig = fig;
    }
    public boolean clicked (int x, int y){
        return this.fig.clicked(x,y);
    }
    public void paint (Graphics g, boolean focused){
        if (focused) {
            this.fig.paint(g, true);
        } else {
            this.fig.paint(g, false);
        }
    }
}
