package com.buscaminas.juego;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** Utilidades de test para armar tableros deterministas por reflexión (sin tocar el código de producción). */
final class TableroTestUtil {

    private TableroTestUtil() {
    }

    static Celda[][] celdas(Tablero tablero) {
        try {
            Field campo = Tablero.class.getDeclaredField("tablero");
            campo.setAccessible(true);
            return (Celda[][]) campo.get(tablero);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    /** Crea un tablero con minas solo en las posiciones indicadas ({fila, columna}) y recalcula los números. */
    static Tablero conMinas(int[]... posiciones) {
        Tablero tablero = new Tablero();
        Celda[][] celdas = new Celda[Tablero.FILAS][Tablero.COLUMNAS];
        for (int f = 0; f < Tablero.FILAS; f++) {
            for (int c = 0; c < Tablero.COLUMNAS; c++) {
                celdas[f][c] = new Celda();
            }
        }
        for (int[] p : posiciones) {
            celdas[p[0]][p[1]].colocarMina();
        }
        try {
            Field campo = Tablero.class.getDeclaredField("tablero");
            campo.setAccessible(true);
            campo.set(tablero, celdas);
            Method calcular = Tablero.class.getDeclaredMethod("calcularMinasCercanas");
            calcular.setAccessible(true);
            calcular.invoke(tablero);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
        return tablero;
    }

    static int contarMinas(Tablero tablero) {
        int total = 0;
        for (Celda[] fila : celdas(tablero)) {
            for (Celda celda : fila) {
                if (celda.tieneMina()) {
                    total++;
                }
            }
        }
        return total;
    }
}
