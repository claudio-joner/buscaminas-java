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
| T-009 | Hecha | Reemplazar el tamaño 8 y el 64 fijos en `Tablero` por constantes (`FILAS`, `COLUMNAS`) |
| T-010 | Hecha | Eliminar imports sin usar en `Tablero` y el campo `encontrada` en `Celda` |
| T-011 | Pendiente | Generar las minas después del primer clic para que la primera jugada nunca sea una mina |
| T-012 | Pendiente | Implementar el modo Jugador vs CPU |
| T-013 | Hecha | Configurar JUnit 5 en el proyecto y crear la carpeta `test` con la estructura de paquetes espejo de `src` |
| T-014 | Hecha | Tests de `Celda`: `colocarMina`/`tieneMina`, `descubrir`/`estaDescubierta`, `setMinasCercanas`/`getMinasCercanas` y valores iniciales |
| T-015 | Hecha | Tests de `Tablero` (construcción): tamaño 8x8, exactamente 10 minas y `minasCercanas` coherente con las minas vecinas (esquinas y bordes incluidos) |
| T-016 | Hecha | Tests de `Tablero.posicionValida`: límites 0 y 7, valores negativos y fuera de rango |
| T-017 | Pendiente | Tests de `Tablero.descubrirCelda`: posición inválida, celda ya descubierta, celda con número, celda con mina (devuelve `true`) y celda con 0 (abre vecinas) |
| T-018 | Hecha | Tests de `Tablero.abrirCeros`: expansión por celdas con 0, parada en celdas con número y sin desbordar los bordes |
| T-019 | Hecha | Tests de `Tablero.ganoJugador` y `revelarMinas`: victoria al descubrir todas las celdas sin mina, no victoria con celdas pendientes y minas reveladas |
| T-020 | Hecha | Tests de `Jugador`/`JugadorHumano`: constructor, getters y setters, `sumarCeldaDescubiertas` y `elegirCelda` simulando `System.in` |
| T-021 | Pendiente | Tests de `Juego`: getters y setters, `getTablero` lanza excepción sin tablero, `cambiarTurno` rota entre jugadores y vuelve al primero, y flujo de derrota y victoria |
| T-022 | Hecha | Tests de `GestorPartidas`: guardar y recuperar devuelve un `Juego` equivalente (tablero, jugadores y turno) y `recuperar` devuelve `null` si no existe el archivo |
| T-023 | Pendiente | Tests de `MenuPrincipal`: opciones de los menús con entrada simulada, opción inválida y salida |
