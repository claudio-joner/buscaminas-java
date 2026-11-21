package com.buscaminas.juego;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CeldaTest {

    @Test
    void celdaNuevaNoTieneMinaNiEstaDescubierta() {
        Celda celda = new Celda();

        assertFalse(celda.tieneMina());
        assertFalse(celda.estaDescubierta());
    }

    @Test
    void celdaNuevaTieneCeroMinasCercanas() {
        assertEquals(0, new Celda().getMinasCercanas());
    }

    @Test
    void colocarMinaHaceQueTieneMinaSeaTrue() {
        Celda celda = new Celda();

        celda.colocarMina();

        assertTrue(celda.tieneMina());
    }

    @Test
    void descubrirHaceQueEstaDescubiertaSeaTrue() {
        Celda celda = new Celda();

        celda.descubrir();

        assertTrue(celda.estaDescubierta());
    }

    @Test
    void descubrirNoColocaMina() {
        Celda celda = new Celda();

        celda.descubrir();

        assertFalse(celda.tieneMina());
    }

    @Test
    void setMinasCercanasActualizaElValor() {
        Celda celda = new Celda();

        celda.setMinasCercanas(5);

        assertEquals(5, celda.getMinasCercanas());
    }
}
