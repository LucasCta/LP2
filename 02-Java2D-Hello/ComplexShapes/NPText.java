import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NPText {
  public static void main (String[] args) {
    Texto r1 = new Texto(1,1,1,"font","fontType",Color.red,Color.blue);
    r1.print();
  }
}

class Texto{
   int x, y, fontSize;
   String font, typeFamily;
   Color fontColor, bgColor;
   Texto(int x, int y, int fontSize, String font, String typeFamily, Color fontColor, Color bgColor){
       this.x = x;
       this.y = y;
       this.fontSize = fontSize;
       this.font = font;
       this.typeFamily = typeFamily;
       this.fontColor = fontColor;
       this.bgColor = bgColor;
   }
   void print () {
     System.out.format("Texto na posicao (%d,%d), com fonte (%s), de tamanho (%d), em formatacao (%s),",
        this.x, this.y, this.font, this.fontSize, this.typeFamily);
     System.out.format("com fonte de cor (%s) e cor de fundo de cor (%s).\n",
        this.fontColor, this.bgColor);

   }
}
