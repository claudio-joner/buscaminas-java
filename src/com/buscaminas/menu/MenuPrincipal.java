package com.buscaminas.menu;

import com.buscaminas.juego.Juego;

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
                    System.out.println("Recuperando partida....");
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
                "\n1. Persona vs Persona" +
                "\n2. Persona vs CPU" +
                "\n3. Volver" +
                "\nSelecione una opción";
        int opcion;

        do {
            System.out.println(mensajeNuevaPartida);

            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    Juego juego = new Juego();
                    break;

                case 2:
                    System.out.println("Recuperando...");
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Opción inválida");
            };

        }while (opcion != 3);



    }
}
