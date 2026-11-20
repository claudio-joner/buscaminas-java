# Tareas

Formato: `T-NroTarea | Estado | Descripción`

Estados: `Pendiente`, `En curso`, `Hecha`

| Tarea | Estado | Descripción |
|-------|--------|-------------|
| T-001 | Hecha | Implementar recuperación de partida guardada |
| T-002 | Hecha | Agregar un README con descripción, cómo compilar/ejecutar y reglas del juego |
| T-003 | Pendiente | Distinguir jugada inválida de jugada válida en `descubrirCelda` para no consumir turno ni cambiar de jugador por una celda ya abierta |
| T-004 | Pendiente | Usar `sumarCeldaDescubiertas()` desde `Juego` para llevar el conteo por jugador, o eliminar el contador |
| T-005 | Pendiente | Validar la entrada por consola (`nextInt()` falla con texto) en menús y en `JugadorHumano` |
| T-006 | Pendiente | Compartir un único `Scanner` en lugar de varios sobre `System.in` |
| T-007 | Pendiente | Preguntar si guardar al salir de una partida |
| T-008 | Pendiente | Que `GestorPartidas.guardar` informe al llamador si falló |
| T-009 | Pendiente | Reemplazar el tamaño 8 y el 64 fijos en `Tablero` por constantes (`FILAS`, `COLUMNAS`) |
| T-010 | Pendiente | Eliminar imports sin usar en `Tablero` y el campo `encontrada` en `Celda` |
| T-011 | Pendiente | Generar las minas después del primer clic para que la primera jugada nunca sea una mina |
| T-012 | Pendiente | Implementar el modo Jugador vs CPU |
