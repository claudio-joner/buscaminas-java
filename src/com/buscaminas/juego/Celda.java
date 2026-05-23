package com.buscaminas.juego;

import java.io.Serializable;

public class Celda implements Serializable {
    //Atributos de la clase
    private boolean mina;
    private boolean encontrada;
    private int minasCercanas;
    private boolean descubierta;


    public boolean tieneMina(){
        return mina;
    }

    public void colocarMina(){
        this.mina = true;
    }

    public boolean estaDescubierta(){
        return descubierta;
    }

    public void descubrir(){
        descubierta = true;
    }

    public int getMinasCercanas(){
        return minasCercanas;
    }

    public void setMinasCercanas(int minasCercanas){
        this.minasCercanas = minasCercanas;
    }


}
