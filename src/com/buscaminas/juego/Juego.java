package com.buscaminas.juego;


import com.buscaminas.jugadores.Jugador;
import com.buscaminas.persistencia.GestorPartidas;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Juego implements Serializable {
    private Tablero tablero;
    private boolean jugando ;
    private Scanner teclado = new Scanner(System.in);
    private List<Jugador> jugadores;
    private int indiceTurnoActual;
    private Jugador jugadorActual;



    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setIndiceTurnoActual(int indiceTurnoActual) {
        this.indiceTurnoActual = indiceTurnoActual;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        if(tablero == null){

            throw new RuntimeException(
                    "Tablero inexistente"
            );

        }

        return tablero;
    }

    public int getIndiceTurnoActual() {
        return indiceTurnoActual;
    }

    public boolean getJugando() {
        return jugando;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }


    public Juego(List<Jugador> jugadores){
        setTablero(new Tablero());
        setJugando(true);
        setJugadores(jugadores);
        setIndiceTurnoActual(0);
        iniciarTablero();
    }

    public Juego(Tablero tablero,List<Jugador> jugadores){
        setTablero(tablero);
        setJugando(true);
        setJugadores(jugadores);
        setIndiceTurnoActual(0);
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

        Jugador jugador = jugadorActual();

        int [] posicion = jugador.elegirCelda();
        int fila = posicion[0];
        int columna = posicion[1];


        //Se rememplaza por la linea anterior.
        /*System.out.print("Fila: ");
        int fila = teclado.nextInt();

        System.out.print("Columna: ");
        int columna = teclado.nextInt();*/

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

        cambiarTurno();
    }

    private Jugador jugadorActual(){
        setJugadorActual(getJugadores().get(getIndiceTurnoActual()));
        return getJugadorActual();
    }

    private void cambiarTurno(){
        setIndiceTurnoActual( (getIndiceTurnoActual() + 1) % getJugadores().size());
    }
}
