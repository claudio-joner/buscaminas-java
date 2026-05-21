package com.buscaminas.juego;

public class Celda {
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
