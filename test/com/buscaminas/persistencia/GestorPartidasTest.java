package com.buscaminas.persistencia;

import com.buscaminas.juego.Celda;
import com.buscaminas.juego.Juego;
import com.buscaminas.juego.Tablero;
import com.buscaminas.jugadores.Jugador;
import com.buscaminas.jugadores.JugadorHumano;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** GestorPartidas usa "partida.dat" en el directorio actual: se respalda y restaura para no pisar una partida real. */
class GestorPartidasTest {

    private static final Path ARCHIVO = Path.of("partida.dat");
    private static final Path RESPALDO = Path.of("partida.dat.test-bak");

    private final GestorPartidas gestor = new GestorPartidas();

    @BeforeEach
    void respaldar() throws Exception {
        if (Files.exists(ARCHIVO)) {
            Files.move(ARCHIVO, RESPALDO, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @AfterEach
    void restaurar() throws Exception {
        Files.deleteIfExists(ARCHIVO);
        if (Files.exists(RESPALDO)) {
            Files.move(RESPALDO, ARCHIVO);
        }
    }

    @Test
    void recuperarDevuelveNullSiNoExisteElArchivo() {
        assertNull(gestor.recuperar());
    }

    @Test
    void guardarYRecuperarDevuelveUnJuegoEquivalente() throws Exception {
        Jugador ana = new JugadorHumano("Ana");
        ana.setCeldasDescubiertas(3);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(ana);
        jugadores.add(new JugadorHumano("Luis"));
        Juego original = new Juego(jugadores);
        original.setIndiceTurnoActual(1);
        original.getTablero().descubrirCelda(0, 0);

        gestor.guardar(original);
        Juego recuperado = gestor.recuperar();

        assertNotNull(recuperado);
        assertEquals(1, recuperado.getIndiceTurnoActual());
        assertEquals(2, recuperado.getJugadores().size());
        assertEquals("Ana", recuperado.getJugadores().get(0).getNombre());
        assertEquals(3, recuperado.getJugadores().get(0).getCeldasDescubiertas());
        assertEquals("Luis", recuperado.getJugadores().get(1).getNombre());
        assertTablerosIguales(original.getTablero(), recuperado.getTablero());
    }

    private static void assertTablerosIguales(Tablero esperado, Tablero actual) throws Exception {
        Celda[][] a = celdas(esperado);
        Celda[][] b = celdas(actual);
        for (int f = 0; f < a.length; f++) {
            for (int c = 0; c < a[f].length; c++) {
                String pos = "(" + f + "," + c + ")";
                assertEquals(a[f][c].tieneMina(), b[f][c].tieneMina(), "mina " + pos);
                assertEquals(a[f][c].estaDescubierta(), b[f][c].estaDescubierta(), "descubierta " + pos);
                assertEquals(a[f][c].getMinasCercanas(), b[f][c].getMinasCercanas(), "cercanas " + pos);
            }
        }
    }

    private static Celda[][] celdas(Tablero tablero) throws Exception {
        Field campo = Tablero.class.getDeclaredField("tablero");
        campo.setAccessible(true);
        return (Celda[][]) campo.get(tablero);
    }
}
