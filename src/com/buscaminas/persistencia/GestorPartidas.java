package com.buscaminas.persistencia;

import com.buscaminas.juego.Tablero;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestorPartidas {
    /**
     * Metodo encargado de guardar el estado actual del tablero
     * en un archivo usando serializacion.
     *
     * Convierte el objeto Tablero en bytes y lo almacena
     * en el archivo "partida.dat" para poder recuperarlo luego.
     */
    public void guardar(Tablero tablero){
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("partida.dat"));
            salida.writeObject(tablero);
            salida.close();
            System.out.println("Partida guardada");
        } catch (Exception e) {
            System.out.println("Error al guardar");
        }
    }

    public Tablero recuperar(){
        try{
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("partida.dat"));
            Tablero tablero = (Tablero) entrada.readObject();
            entrada.close();
            System.out.println("Partida recuperada.");
            return  tablero;
        } catch (Exception e) {
            System.out.println("Error al recuperar partida.");

            return null;
        }
    }
}
