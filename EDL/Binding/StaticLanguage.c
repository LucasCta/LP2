#include <math.h>

#define euler 2.7182818284590452353602874713526624977572470936999
//nome = euler, propriedade = valor, tempo =  pré-processamento

double pitagoras(double, double);
//nome = pitagoras, propriedade = implementacao, tempo = ligacao
//nome = pitagoras, propriedade = tipo de retorno, tempo = compilacao

static double million = 1000000.0;
//nome = million, tipo = endereco, tempo = carregamento
//nome = double, tipo = tamanho, tempo = implementacao

int main (void) {

    double golden_ratio = 1.6180339887498948482045868343656381177203091798057;
    //nome = golden_ratio, tipo = endereco, tempo = execucao

    int val = (int)(cos(pitagoras(golden_ratio, euler)) * million);
    //nome = cos, tipo = implementacao, tempo = ligacao
    //nome = val, tipo = endereco, tempo = execucao
    //nome = *, tipo = semantica, tempo = compilacao
    //nome = int, tipo tamanho, tempo = implementacao

    return val; 

}
