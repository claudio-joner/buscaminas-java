package com.buscaminas.jugadores;

import java.util.Scanner;

public class JugadorHumano extends Jugador {
    private transient Scanner teclado;

    public JugadorHumano(String nombre){
        super(nombre);
    }

    @Override
    public int[] elegirCelda() {
        if (teclado == null) {
            teclado = new Scanner(System.in);
        }
        System.out.println("Turno de: " + getNombre());
        System.out.println("Fila: ");
        int fila = teclado.nextInt();
        System.out.println("Columna: ");
        int columna = teclado.nextInt();

        return new int[]{fila,columna};
    }
}
