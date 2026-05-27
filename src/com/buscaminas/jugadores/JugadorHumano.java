package com.buscaminas.jugadores;

import java.util.Scanner;

public class JugadorHumano extends Jugador {
    private Scanner teclado = new Scanner(System.in);

    public JugadorHumano(String nombre){
        super(nombre);
    }

    @Override
    public int[] elegirCelda() {
        System.out.println("Turno de: " + getNombre());
        System.out.println("Fila: ");
        int fila = teclado.nextInt();
        System.out.println("Columna: ");
        int columna = teclado.nextInt();

        return new int[]{fila,columna};
    }
}
