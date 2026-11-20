# Buscaminas

Juego de Buscaminas por consola, escrito en Java.

## Características

- Tablero de 8x8 con 10 minas colocadas al azar.
- Modo un jugador y modo multijugador por turnos (Jugador vs Jugador).
- Apertura automática de celdas vacías (cuando una celda no tiene minas cerca, se abren las vecinas).
- Guardar y recuperar partida (tablero, jugadores y turno) en el archivo `partida.dat`.

> Jugador vs CPU aparece en el menú pero todavía no está implementado.

## Requisitos

- JDK 15 o superior (el código usa bloques de texto `"""`).

## Compilar y ejecutar

Desde la raíz del proyecto:

```bash
javac -d out/production/Buscaminas $(find src -name '*.java')
java -cp out/production/Buscaminas Main
```

En PowerShell:

```powershell
javac -d out/production/Buscaminas (Get-ChildItem src -Recurse -Filter *.java).FullName
java -cp out/production/Buscaminas Main
```

También se puede abrir como proyecto de IntelliJ IDEA y ejecutar `Main`.

## Cómo se juega

1. En el menú principal elige **Nueva Partida** o **Recuperar Partida**.
2. En cada turno elige **Realizar jugada** e ingresa fila y columna (de 0 a 7).
3. El tablero muestra:
   - `-` celda sin descubrir
   - un número: cantidad de minas en las 8 celdas vecinas
   - `*` mina
4. Ganas al descubrir todas las celdas sin mina. Pierdes si descubres una mina.
5. Desde el menú de partida puedes **Guardar partida** o **Salir**.

## Estructura del proyecto

```
src/
├── Main.java
└── com/buscaminas/
    ├── menu/          MenuPrincipal
    ├── juego/         Juego, Tablero, Celda
    ├── jugadores/     Jugador, JugadorHumano
    └── persistencia/  GestorPartidas
```

## Tareas

El listado de tareas está en [TAREAS.md](TAREAS.md).
