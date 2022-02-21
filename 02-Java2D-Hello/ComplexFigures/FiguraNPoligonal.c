#include <stdio.h>

typedef struct VerticePair{
	int x, y; //v1 pos
  int w, z; //v2 pos
	int h, j; //posicao do vertice de controle, para a curvatura
	struct VerticePair *prox;
} VerticePair;

//Variaveis escolhidas para desenhar curvas de acordo com o metodo abaixo:
//http://devmag.org.za/2011/04/05/bzier-curves-a-tutorial/

VerticePair defVertices(int qntV){
	 //Aqui, gerariamos os vertices, posicionados como que dividindo igualmente um circulo de raio 1.
	 //Os vertices seriam armazenados em uma lista de structs, com pares de vertices adjacentes, contendo os vertices de controle para a geracao das curvas.
	 //Cada struct contem um ponteiro que aponta para o par ajacente seguinte, ex: v1,v2 com ponteiro para v2,v3. O ultimo vertice, vn, faz par com o primeiro.
	 //Deixo claro que nao implemento o codigo por conta da complexidade e do tempo disponivel para a entrega.
};

typedef struct{
  int x, y, qntV;
	char *bgColor, *lineColor;
	VerticePair start;
} FiguraNPoligonal;

FiguraNPoligonal defFNP(int x, int y, char bc[], char lc[], int qntV){
    FiguraNPoligonal f;
    f.x = x;
    f.y = y;
    f.bgColor = bc;
		f.lineColor = lc;
		f.qntV = qntV;
		//f.start = defVertices(qntV);
    return f;
};

void printFNP(FiguraNPoligonal f){
  printf("Pos: (%d,%d), Color de contorno: %s, Cor de fundo: %s, Quantidade de Vertices: %d", f.x,f.y,f.lineColor,f.bgColor,f.qntV);
}

int main(){
    FiguraNPoligonal figura;
    figura = defFNP(10,10,"red","blue",30);
    printFNP(figura);
   return 0;
}
