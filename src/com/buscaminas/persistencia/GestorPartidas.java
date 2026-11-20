package com.buscaminas.persistencia;

import com.buscaminas.juego.Juego;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestorPartidas {
    /**
     * Metodo encargado de guardar el estado completo de la partida (tablero, jugadores y turno)
     * en un archivo usando serializacion.
     *
     * Convierte el objeto Tablero en bytes y lo almacena
     * en el archivo "partida.dat" para poder recuperarlo luego.
     */
    public void guardar(Juego juego){
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("partida.dat"));
            salida.writeObject(juego);
            salida.close();
            System.out.println("Partida GUARDADA....");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e);
        }
    }

    /**
     * Recupera una partida guardada desde el archivo partida.dat.
     *
     * @return juego recuperado o null si ocurre un error.
     */
    public Juego recuperar(){
        try {
            ObjectInputStream partidaGuardada = new ObjectInputStream(new FileInputStream("partida.dat"));
            Juego juegoGuardado = (Juego) partidaGuardada.readObject();
            partidaGuardada.close();
            System.out.println("Partida RECUPERADA...");
            return juegoGuardado;

        } catch (Exception e) {
            System.out.println("Error al recuperar partida."+
                    "\nError: " + e.toString());
            return null;
        }
    }
}
