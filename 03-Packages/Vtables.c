#include <stdio.h>
#include <stdlib.h>
#include <math.h>

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    int r,g,b;
} Color;

Color* color_new (int x, int y, int z) {
    Color* this = malloc(sizeof(Color));
    this->r = x;
    this->g = y;
    this->b = z;
}

///////////////////////////////////////////////////////////////////////////////

struct Figure;
typedef void (* Figure_Print) (struct Figure*);
typedef int (* Figure_Area) (struct Figure*);
typedef int (* Figure_Perimeter) (struct Figure*);

typedef struct {
    void (* print) (struct Figure*);
    int (* area) (struct Figure*);
    int (* perimeter) (struct Figure*);
} Figure_vtable;

typedef struct Figure {
    int x, y;
    Color fg, bg;
    Figure_vtable* vtable;
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
    printf("Cores: (%d,%d,%d), (%d,%d,%d)\n\n", sup->fg.r, sup->fg.g, sup->fg.b, sup->bg.r, sup->bg.g, sup->bg.b); 
}

int Ellipse_area (Ellipse* this) {
    Figure* sup = (Figure*) this;
    return (int)(this->w * this->h * M_PI);
}

int Ellipse_perimeter (Ellipse* this){
    Figure* sup = (Figure*) this;
    //Approximation as there is no simple formula for an ellipse perimeter
    double h = pow(this->w - this->h,2)/pow(this->w + this->h,2);
    return (int)(M_PI*(this->w + this->h)*(1+((3*h)/(10+sqrt(4-3*h)))));
}


Figure_vtable ellipse_vtable = {
    (Figure_Print) Ellipse_print,
    (Figure_Area) Ellipse_area,
    (Figure_Perimeter) Ellipse_perimeter
};

Ellipse* ellipse_new (int x, int y, int w, int h, Color lineColor, Color bgColor) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->vtable = &ellipse_vtable;
    sup->x = x;
    sup->y = y;
    sup->fg = lineColor;
    sup->bg = bgColor;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int r, a, vx[6], vy[6];
} Rhexagon;

void Rhexagon_print (Rhexagon* this) {
    Figure* sup = (Figure*) this;
    printf("Hexagono Regular na posicao (%d,%d), de raio %d, e rotacao de %d graus.\nVertices: ", 
        sup->x, sup->y, this->r, this->a);
    for (int i = 0; i < 6; i++){
        printf("(%d,%d)", this->vx[i], this-> vy[i]);
    }
    printf("\nCores: (%d,%d,%d), (%d,%d,%d)\n\n", sup->fg.r, sup->fg.g, sup->fg.b, sup->bg.r, sup->bg.g, sup->bg.b); 
}


int Rhexagon_area (Rhexagon* this) {
    Figure* sup = (Figure*) this;
    return (int)(3*sqrt(3)*pow(this->r,2)/2);
}

int Rhexagon_perimeter (Rhexagon* this) {
    Figure* sup = (Figure*) this;
    return (int)(6*this->r);
}

Figure_vtable rhexagon_vtable = {
    (Figure_Print) Rhexagon_print,
    (Figure_Area) Rhexagon_area,
    (Figure_Perimeter) Rhexagon_perimeter
};

Rhexagon* rhexagon_new (int x, int y, int r, int a, Color lineColor, Color bgColor) {
    Rhexagon* this = malloc(sizeof(Rhexagon));
    Figure* sup = (Figure*) this;
    sup->vtable = &rhexagon_vtable;
    sup->x = x;
    sup->y = y;
    sup->fg = lineColor;
    sup->bg = bgColor;
    this->r = r;
    this->a = a;
    for (int i = 0; i < 6; i++){
        this->vx[i] = (int)(r*cos((a+i*60)*180/M_PI)) + x;
        this->vy[i] = (int)(r*sin((a+i*60)*180/M_PI)) + y;
    }
}

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Color* colors[3] = {
        color_new(25,25,25),
        color_new(255,255,255),
        color_new(100,0,100)
    };
    Figure* figs[4] = {
        (Figure*) ellipse_new(40,10,140,300,*colors[1],*colors[0]),
        (Figure*) ellipse_new(210,110,305,130,*colors[2],*colors[1]),
        (Figure*) rhexagon_new(120,80,100,15,*colors[1],*colors[2]),
        (Figure*) rhexagon_new(170,40,60,0,*colors[0],*colors[2])
    };
    for (int i=0; i<4; i++) {
        figs[i]->vtable->print(figs[i]);
        printf("Area: %d",figs[i]->vtable->area(figs[i]));
        printf("\nPerimeter: %d\n\n",figs[i]->vtable->perimeter(figs[i]));
        free(figs[i]);
    }
}