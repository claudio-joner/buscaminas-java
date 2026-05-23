package com.buscaminas.juego;


import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Random;

public class Tablero implements Serializable {
    private Celda[][] tablero;
    private final int CANT_MINAS = 10;

    public Tablero(){
        tablero = new Celda[8][8];
        inicializar();
        colocarMina();
        calcularMinasCercanas();
    }

    private void calcularMinasCercanas() {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                int cantidad = contarMinasAlrededor(fila,columna);
                tablero[fila][columna].setMinasCercanas(cantidad);
            }
        }
    }

    private int contarMinasAlrededor(int fila, int columna) {
        int cantidad = 0;

        for (int i = fila-1; i <= fila + 1 ; i++) {
            for (int j = columna-1; j <= columna + 1 ; j++) {
                if(i>= 0 && i < 8 && j >= 0  && j < 8){
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
            int fila = random.nextInt(8);
            int columna = random.nextInt(8);

            if(!tablero[fila][columna].tieneMina()){
                tablero[fila][columna].colocarMina();
                minasColocadas++;
            }
        }

    }

    private void inicializar() {
        for (int fila = 0; fila < 8 ; fila++) {
            for (int columna = 0; columna < 8 ; columna++) {
                tablero[fila][columna] = new Celda();
            }

        }
    }

    public void mostrar(){

        System.out.println("\n     0  1  2  3  4  5  6  7");
        System.out.println("   -------------------------");

        for(int fila = 0; fila < 8; fila++){

            System.out.print(fila + " | ");

            for(int columna = 0; columna < 8; columna++){

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
        return fila >= 0 && fila < 8 && columna >= 0 && columna < 8;
    }

    public boolean ganoJugador(){
        int descubiertas = 0;
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Celda celda = tablero[fila][columna];
                        if(celda.estaDescubierta() && !celda.tieneMina()){
                            descubiertas++;
                        }
            }

        }
        return descubiertas ==(64-CANT_MINAS);
    }

    public void revelarMinas(){
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
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
