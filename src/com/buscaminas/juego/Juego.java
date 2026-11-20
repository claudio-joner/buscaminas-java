package com.buscaminas.juego;


import com.buscaminas.jugadores.Jugador;
import com.buscaminas.persistencia.GestorPartidas;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Juego implements Serializable {
    private Tablero tablero;
    private boolean jugando ;
    private transient Scanner teclado;
    private List<Jugador> jugadores;
    private int indiceTurnoActual;


    //SETTERS and GETTERS

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



    //CONSTRUCTORES

    public Juego(List<Jugador> jugadores){
        setTablero(new Tablero());
        setJugando(true);
        setJugadores(jugadores);
        setIndiceTurnoActual(0);
    }

    public Juego(Tablero tablero,List<Jugador> jugadores){
        setTablero(tablero);
        setJugando(true);
        setJugadores(jugadores);
        setIndiceTurnoActual(0);
    }

    private Scanner teclado() {
        if (teclado == null) {
            teclado = new Scanner(System.in);
        }
        return teclado;
    }

    /** Ejecuta el bucle de la partida (nueva o recuperada). */
    public void iniciar() {
        setJugando(true);

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

            int opcion = teclado().nextInt();

            switch(opcion){

                case 1:
                    realizarJugada();
                    break;

                case 2:
                    GestorPartidas gestor = new GestorPartidas();
                    gestor.guardar(this);
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
        int fila = teclado().nextInt();

        System.out.print("Columna: ");
        int columna = teclado().nextInt();*/

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

        if(getJugando()){
            cambiarTurno();
        }
    }

    private Jugador jugadorActual(){
        return getJugadores().get(getIndiceTurnoActual());
    }

    private void cambiarTurno(){
        setIndiceTurnoActual( (getIndiceTurnoActual() + 1) % getJugadores().size());
    }
}
