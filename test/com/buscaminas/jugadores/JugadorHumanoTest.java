package com.buscaminas.jugadores;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class JugadorHumanoTest {

    private final InputStream stdinOriginal = System.in;

    @AfterEach
    void restaurarStdin() {
        System.setIn(stdinOriginal);
    }

    @Test
    void constructorAsignaNombreYCeldasEnCero() {
        JugadorHumano jugador = new JugadorHumano("Ana");

        assertEquals("Ana", jugador.getNombre());
        assertEquals(0, jugador.getCeldasDescubiertas());
    }

    @Test
    void settersActualizanLosValores() {
        JugadorHumano jugador = new JugadorHumano("Ana");

        jugador.setNombre("Luis");
        jugador.setCeldasDescubiertas(7);

        assertEquals("Luis", jugador.getNombre());
        assertEquals(7, jugador.getCeldasDescubiertas());
    }

    @Test
    void sumarCeldaDescubiertasIncrementaDeAUno() {
        JugadorHumano jugador = new JugadorHumano("Ana");

        jugador.sumarCeldaDescubiertas();
        jugador.sumarCeldaDescubiertas();

        assertEquals(2, jugador.getCeldasDescubiertas());
    }

    @Test
    void elegirCeldaLeeFilaYColumnaDeLaEntrada() {
        System.setIn(new ByteArrayInputStream("3\n5\n".getBytes()));
        JugadorHumano jugador = new JugadorHumano("Ana");

        assertArrayEquals(new int[]{3, 5}, jugador.elegirCelda());
    }

    @Test
    void elegirCeldaReutilizaElScannerEnLlamadasSucesivas() {
        System.setIn(new ByteArrayInputStream("1\n2\n6\n7\n".getBytes()));
        JugadorHumano jugador = new JugadorHumano("Ana");

        assertArrayEquals(new int[]{1, 2}, jugador.elegirCelda());
        assertArrayEquals(new int[]{6, 7}, jugador.elegirCelda());
    }
}
