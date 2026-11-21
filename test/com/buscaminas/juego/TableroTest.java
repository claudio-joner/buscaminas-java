package com.buscaminas.juego;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

    // ---- T-015: construcción ----

    @Test
    void tableroTiene8x8Celdas() {
        Celda[][] celdas = TableroTestUtil.celdas(new Tablero());

        assertEquals(8, celdas.length);
        for (Celda[] fila : celdas) {
            assertEquals(8, fila.length);
        }
    }

    @RepeatedTest(20)
    void tableroTieneExactamente10Minas() {
        assertEquals(10, TableroTestUtil.contarMinas(new Tablero()));
    }

    @RepeatedTest(20)
    void minasCercanasCoincideConLasMinasVecinas() {
        Celda[][] celdas = TableroTestUtil.celdas(new Tablero());

        for (int f = 0; f < Tablero.FILAS; f++) {
            for (int c = 0; c < Tablero.COLUMNAS; c++) {
                assertEquals(vecinasConMina(celdas, f, c), celdas[f][c].getMinasCercanas(),
                        "celda (" + f + "," + c + ")");
            }
        }
    }

    @Test
    void minasCercanasEnEsquinasYBordes() {
        // Minas en (0,1), (1,0) y (7,6)
        Tablero tablero = TableroTestUtil.conMinas(new int[]{0, 1}, new int[]{1, 0}, new int[]{7, 6});
        Celda[][] celdas = TableroTestUtil.celdas(tablero);

        assertEquals(2, celdas[0][0].getMinasCercanas()); // esquina superior izquierda
        assertEquals(1, celdas[7][7].getMinasCercanas()); // esquina inferior derecha
        assertEquals(1, celdas[6][7].getMinasCercanas()); // borde derecho
        assertEquals(1, celdas[7][5].getMinasCercanas()); // borde inferior
        assertEquals(0, celdas[0][7].getMinasCercanas()); // esquina superior derecha
    }

    private static int vecinasConMina(Celda[][] celdas, int fila, int columna) {
        int cantidad = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                boolean dentro = i >= 0 && i < celdas.length && j >= 0 && j < celdas[0].length;
                if (dentro && !(i == fila && j == columna) && celdas[i][j].tieneMina()) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    // ---- T-016: posicionValida ----

    @Test
    void posicionValidaAceptaLosLimites0Y7() {
        Tablero tablero = new Tablero();

        assertTrue(tablero.posicionValida(0, 0));
        assertTrue(tablero.posicionValida(0, 7));
        assertTrue(tablero.posicionValida(7, 0));
        assertTrue(tablero.posicionValida(7, 7));
    }

    @Test
    void posicionValidaRechazaNegativos() {
        Tablero tablero = new Tablero();

        assertFalse(tablero.posicionValida(-1, 0));
        assertFalse(tablero.posicionValida(0, -1));
        assertFalse(tablero.posicionValida(-1, -1));
    }

    @Test
    void posicionValidaRechazaFueraDeRango() {
        Tablero tablero = new Tablero();

        assertFalse(tablero.posicionValida(8, 0));
        assertFalse(tablero.posicionValida(0, 8));
        assertFalse(tablero.posicionValida(8, 8));
        assertFalse(tablero.posicionValida(100, 100));
    }

    // ---- T-018: abrirCeros ----

    @Test
    void abrirCerosExpandePorCeldasConCeroYSeDetieneEnNumeros() {
        // Mina única en (0,0): todo es 0 salvo (0,1), (1,0) y (1,1), que valen 1
        Tablero tablero = TableroTestUtil.conMinas(new int[]{0, 0});
        Celda[][] celdas = TableroTestUtil.celdas(tablero);

        tablero.abrirCeros(7, 7);

        for (int f = 0; f < Tablero.FILAS; f++) {
            for (int c = 0; c < Tablero.COLUMNAS; c++) {
                boolean esLaMina = f == 0 && c == 0;
                assertEquals(!esLaMina, celdas[f][c].estaDescubierta(), "celda (" + f + "," + c + ")");
            }
        }
    }

    @Test
    void abrirCerosEnCeldaConNumeroSoloDescubreEsaCelda() {
        Tablero tablero = TableroTestUtil.conMinas(new int[]{0, 0});
        Celda[][] celdas = TableroTestUtil.celdas(tablero);

        tablero.abrirCeros(1, 1); // vale 1

        assertTrue(celdas[1][1].estaDescubierta());
        assertFalse(celdas[2][2].estaDescubierta());
        assertFalse(celdas[0][1].estaDescubierta());
    }

    @Test
    void abrirCerosNoDesbordaLosBordesDesdeUnaEsquina() {
        Tablero tablero = TableroTestUtil.conMinas(new int[]{7, 7});

        assertDoesNotThrow(() -> tablero.abrirCeros(0, 0));
        assertTrue(TableroTestUtil.celdas(tablero)[0][0].estaDescubierta());
    }

    @Test
    void abrirCerosConPosicionInvalidaNoHaceNada() {
        Tablero tablero = TableroTestUtil.conMinas(new int[]{0, 0});

        assertDoesNotThrow(() -> tablero.abrirCeros(-1, 3));
        assertDoesNotThrow(() -> tablero.abrirCeros(8, 8));
        for (Celda[] fila : TableroTestUtil.celdas(tablero)) {
            for (Celda celda : fila) {
                assertFalse(celda.estaDescubierta());
            }
        }
    }

    // ---- T-019: ganoJugador y revelarMinas ----

    @Test
    void ganoJugadorEsFalsoAlInicio() {
        assertFalse(new Tablero().ganoJugador());
    }

    @Test
    void ganoJugadorEsFalsoConUnaCeldaSinMinaPendiente() {
        Tablero tablero = TableroTestUtil.conMinas(new int[]{0, 0});
        Celda[][] celdas = TableroTestUtil.celdas(tablero);

        descubrirSinMina(celdas);
        celdas[7][7] = new Celda(); // celda sin descubrir

        assertFalse(tablero.ganoJugador());
    }

    @Test
    void ganoJugadorEsVerdaderoAlDescubrirTodasLasCeldasSinMina() {
        Tablero tablero = new Tablero();

        descubrirSinMina(TableroTestUtil.celdas(tablero));

        assertTrue(tablero.ganoJugador());
    }

    @Test
    void descubrirSoloMinasNoCuentaParaLaVictoria() {
        Tablero tablero = new Tablero();

        tablero.revelarMinas();

        assertFalse(tablero.ganoJugador());
    }

    @Test
    void revelarMinasDescubreTodasLasMinasYSoloEsas() {
        Tablero tablero = new Tablero();
        Celda[][] celdas = TableroTestUtil.celdas(tablero);

        tablero.revelarMinas();

        for (Celda[] fila : celdas) {
            for (Celda celda : fila) {
                assertEquals(celda.tieneMina(), celda.estaDescubierta());
            }
        }
    }

    private static void descubrirSinMina(Celda[][] celdas) {
        for (Celda[] fila : celdas) {
            for (Celda celda : fila) {
                if (!celda.tieneMina()) {
                    celda.descubrir();
                }
            }
        }
    }
}
