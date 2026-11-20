package com.buscaminas.menu;

import com.buscaminas.juego.Juego;
import com.buscaminas.jugadores.Jugador;
import com.buscaminas.jugadores.JugadorHumano;
import com.buscaminas.persistencia.GestorPartidas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner teclado = new Scanner(System.in);

    public void iniciar(){
        int opcion;

        String mensajeMenuPrincipal = "======BUSCAMINAS======" +
                "\n1. Nueva Partida" +
                "\n2. Recuperar Partida" +
                "\n3. Salir" +
                "\nSelecione una opción";

        do {


            System.out.println(mensajeMenuPrincipal);

            opcion = teclado.nextInt();
            switch (opcion){
                case 1:
                    menuNuevaPartida();
                    break;
                case 2:
                    GestorPartidas gestorPartidas = new GestorPartidas();
                    Juego guardado = gestorPartidas.recuperar();
                    if (guardado != null) {
                        guardado.iniciar();
                    } else {
                        System.out.println("No se pudo recuperar la partida.");
                    }
                    break;
                case 3:
                    System.out.println("Adios...");
                    break;
                default:
                    System.out.println("Opción invalida.");
            }
        }while (opcion != 3);

    }

    private void menuNuevaPartida() {
        String mensajeNuevaPartida = "\n--- NUEVA PARTIDA ---" +
                "\n1. SinglePlayer" +
                "\n2. Multijugador" +
                "\n3. Volver" +
                "\nSelecione una opción";
        int opcion;

        do {
            System.out.println(mensajeNuevaPartida);

            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    iniciarSinglePlayer();
                    break;
                case 2:
                    menuMultijugador();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Opción inválida");
            };

        }while (opcion != 3);

    }

    private void menuMultijugador() {
        String mensajeNuevaPartida = "\n--- Multijugador ---" +
                "\n1. Jugador vs Jugador" +
                "\n2. Jugador vs CPU" +
                "\n3. Volver" +
                "\nSelecione una opción";
        int opcion;

        do {
            System.out.println(mensajeNuevaPartida);

            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    iniciarPersonaVsPersona();
                    break;
                case 2:
                    System.out.println("Opcion Jugador vs PC");
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Opción inválida");
            };

        }while (opcion != 3);

    }

    private void iniciarSinglePlayer() {
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new JugadorHumano("Jugador 1"));
        new Juego(jugadores).iniciar();
    }

    private void iniciarPersonaVsPersona() {
        System.out.println("Cantidad de jugadores: ");
        int cantidad = teclado.nextInt();

        if(cantidad < 2 ){
            System.out.println("Debe haber al menos 2 jugadores.");
            return;
        }

        List<Jugador> jugadores = new ArrayList<>();

        for (int i = 1 ; i <= cantidad ; i++) {
            jugadores.add(
                    new JugadorHumano("Jugador" + i)
            );
        }
        new Juego(jugadores).iniciar();
    }
}
