package com.buscaminas.juego;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CeldaTest {

    @Test
    void celdaNuevaNoTieneMinaNiEstaDescubierta() {
        Celda celda = new Celda();

        assertFalse(celda.tieneMina());
        assertFalse(celda.estaDescubierta());
    }
}
