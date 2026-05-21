package com.buscaminas.juego;

import com.sun.source.util.TaskListener;

import java.util.Scanner;

public class Juego {
    private Tablero tablero;
    public Juego(){

        tablero = new Tablero();

        Scanner teclado = new Scanner(System.in);

        while (true){

            tablero.mostrar();

            System.out.print("Fila: ");
            int fila = teclado.nextInt();

            System.out.print("Columna: ");
            int columna = teclado.nextInt();


            if (!tablero.posicionValida(fila,columna)){
                System.out.println("Fila o columna invalida, Use valores de 0 a 7.");
                continue;
            }

            boolean perdio = tablero.descubrirCelda(fila,columna);

            if (perdio){
                tablero.revelarMinas();
                tablero.mostrar();
                System.out.println("\nMensaje: BOOM... Perdiste.");
                break;
            }
            if(tablero.ganoJugador()){
                tablero.mostrar();
                System.out.println("\nMensaje: GANASTE");
                break;
            }

        }
        //System.out.println("Tablero Creado.");

        tablero.mostrar();
    }
}
