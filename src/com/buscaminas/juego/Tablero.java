package com.buscaminas.juego;


import java.io.Serializable;
import java.util.Random;

public class Tablero implements Serializable {
    private Celda[][] tablero;
    public static final int FILAS = 8;
    public static final int COLUMNAS = 8;
    private final int CANT_MINAS = 10;

    public Tablero(){
        tablero = new Celda[FILAS][COLUMNAS];
        inicializar();
        colocarMina();
        calcularMinasCercanas();
    }

    private void calcularMinasCercanas() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                int cantidad = contarMinasAlrededor(fila,columna);
                tablero[fila][columna].setMinasCercanas(cantidad);
            }
        }
    }

    private int contarMinasAlrededor(int fila, int columna) {
        int cantidad = 0;

        for (int i = fila-1; i <= fila + 1 ; i++) {
            for (int j = columna-1; j <= columna + 1 ; j++) {
                if(i>= 0 && i < FILAS && j >= 0  && j < COLUMNAS){
                    if (!(i == fila && j == columna)) {

                        if(tablero[i][j].tieneMina()){
                            cantidad ++;
                        }
                    }
                }
            }
        }
        return  cantidad;
    }

    private void colocarMina() {
        Random random = new Random();
        int minasColocadas = 0;

        while(minasColocadas < CANT_MINAS){
            int fila = random.nextInt(FILAS);
            int columna = random.nextInt(COLUMNAS);

            if(!tablero[fila][columna].tieneMina()){
                tablero[fila][columna].colocarMina();
                minasColocadas++;
            }
        }

    }

    private void inicializar() {
        for (int fila = 0; fila < FILAS ; fila++) {
            for (int columna = 0; columna < COLUMNAS ; columna++) {
                tablero[fila][columna] = new Celda();
            }

        }
    }

    public void mostrar(){

        System.out.println("\n     0  1  2  3  4  5  6  7");
        System.out.println("   -------------------------");

        for(int fila = 0; fila < FILAS; fila++){

            System.out.print(fila + " | ");

            for(int columna = 0; columna < COLUMNAS; columna++){

                Celda celda = tablero[fila][columna];

                if(celda.estaDescubierta()){
                    if(celda.tieneMina()){
                        System.out.print(" * ");
                    }
                    else {
                        System.out.print(" " + celda.getMinasCercanas() + " ");
                    }
                }else{
                    System.out.print(" - ");
                }

            }

            System.out.println();

        }

    }

    public boolean descubrirCelda(int fila,int columna){
        if(!posicionValida(fila,columna)){
            System.out.println("Posicion invalida");
            return false;
        }

        Celda celda = tablero[fila][columna];

        if(celda.estaDescubierta()){
            System.out.println("Esta celda ya fue descubierta.");
            return false;
        }

        if(!celda.tieneMina() && celda.getMinasCercanas()==0){

            abrirCeros(fila,columna);

        }else{

            celda.descubrir();


        }

        return celda.tieneMina();
    }

    public boolean posicionValida(int fila, int columna){
        return fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS;
    }

    public boolean ganoJugador(){
        int descubiertas = 0;
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                Celda celda = tablero[fila][columna];
                        if(celda.estaDescubierta() && !celda.tieneMina()){
                            descubiertas++;
                        }
            }

        }
        return descubiertas ==(FILAS * COLUMNAS - CANT_MINAS);
    }

    public void revelarMinas(){
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if(tablero[fila][columna].tieneMina()){
                    tablero[fila][columna].descubrir();
                }
            }
        }
    }

    public void abrirCeros(int fila,int columna){
        if(!posicionValida(fila,columna)){
            return;
        }

        Celda celda = tablero[fila][columna];

        if (celda.estaDescubierta()){
            return;
        }

        celda.descubrir();

        if (celda.getMinasCercanas()!= 0){
            return;
        }

        for (int i = fila - 1; i <= fila + 1 ; i++) {
            for (int j = columna - 1 ; j <= columna + 1; j++) {
                if(!(i == fila && j == columna)){
                    abrirCeros(i,j);
                }
            }

        }

    }


}
