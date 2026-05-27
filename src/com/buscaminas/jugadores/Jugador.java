package com.buscaminas.jugadores;

import java.io.Serializable;

public abstract class Jugador implements Serializable {
    private String nombre;
    private int celdasDescubiertas;

    public String getNombre() {
        return nombre;
    }

    public int getCeldasDescubiertas() {
        return celdasDescubiertas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCeldasDescubiertas(int celdasDescubiertas) {
        this.celdasDescubiertas = celdasDescubiertas;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.celdasDescubiertas = 0;
    }

    public void sumarCeldaDescubiertas(){
        setCeldasDescubiertas(getCeldasDescubiertas() + 1);
    }

    public abstract int[] elegirCelda();
}
