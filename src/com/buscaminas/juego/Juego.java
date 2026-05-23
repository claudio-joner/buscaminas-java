package com.buscaminas.juego;


import com.buscaminas.persistencia.GestorPartidas;

import java.io.Serializable;
import java.util.Scanner;

public class Juego implements Serializable {
    private Tablero tablero;
    private boolean jugando ;
    private Scanner teclado = new Scanner(System.in);

    public Tablero getTablero() {
        if(tablero == null){

            throw new RuntimeException(
                    "Tablero inexistente"
            );

        }

        return tablero;
    }

    public boolean getJugando() {
        return jugando;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    public Juego(){
        setTablero(new Tablero());
        setJugando(true);
        iniciarTablero();
    }

    public Juego(Tablero tablero){
        setTablero(tablero);
        setJugando(true);
        iniciarTablero();
    }

    private void iniciarTablero() {

        String menu = """

        ===== MENU PARTIDA =====

        1. Realizar jugada
        2. Guardar partida
        3. Salir

        Seleccione:
        """;

        while(getJugando()){

            getTablero().mostrar();

            System.out.print(menu);

            int opcion = teclado.nextInt();

            switch(opcion){

                case 1:
                    realizarJugada();
                    break;

                case 2:
                    GestorPartidas gestor = new GestorPartidas();
                    gestor.guardar(getTablero());
                    System.out.println("Partida guardada...");
                    break;

                case 3:
                    setJugando(false);
                    System.out.println("Saliendo de partida...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void realizarJugada(){

        Tablero tablero = getTablero();

        System.out.print("Fila: ");
        int fila = teclado.nextInt();

        System.out.print("Columna: ");
        int columna = teclado.nextInt();

        if (!tablero.posicionValida(fila,columna)){
            System.out.println("Fila o columna invalida, use valores de 0 a 7.");
            return;
        }

        boolean perdio = tablero.descubrirCelda(fila,columna);

        if (perdio){
            tablero.revelarMinas();
            tablero.mostrar();
            System.out.println("\nMensaje: BOOM... PERDISTE.");
            setJugando(false);
            return;
        }

        if(tablero.ganoJugador()){
            tablero.mostrar();
            System.out.println("\nMensaje: GANASTE");
            setJugando(false);
        }
    }
}
