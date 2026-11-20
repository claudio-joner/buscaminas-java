# Bitácora

Registro de lo hablado y realizado en el proyecto.

## Sesión 2026-11-19

### 1. Lectura del proyecto
- Se revisó todo el código (8 clases en `com.buscaminas`) y se hizo un resumen.
- Problemas detectados: recuperar partida incompleto, `break` faltante en `menuMultijugador`, bucle de juego dentro del constructor de `Juego`, `Scanner` no serializable, entrada sin validar, constantes 8 y 64 fijas, campos sin uso.

### 2. Recuperación de partida (T-001)
- `GestorPartidas` guarda y recupera el `Juego` completo (tablero, jugadores y turno) en `partida.dat`.
- `Juego`: el constructor ya no arranca el bucle; nuevo método `iniciar()`.
- `Scanner` de `Juego` y `JugadorHumano` pasan a `transient` y se crean cuando hacen falta.
- `MenuPrincipal`: la opción "Recuperar Partida" carga y ejecuta la partida; se quitó `menuPartidaGuardada`.
- Corregido el `break` faltante en `menuMultijugador`.
- Compila sin errores. No se probó el ciclo guardar y recuperar en ejecución.
- Los `partida.dat` del formato anterior ya no se pueden recuperar.

### 3. Aclaración
- Se explicó qué significa `transient`: el campo se omite al serializar y queda en `null` al recuperar.

### 4. Commits
Rama `feature/jugadores`:
- `e19b70b` GestorPartidas guarda y recupera el Juego completo
- `50d9cbb` Separar iniciar() del constructor de Juego y marcar Scanner como transient
- `6cb7ea5` Conectar la opción Recuperar Partida del menú y corregir break en multijugador
- `9955643` Agregar archivo TAREAS.md con listado de tareas
- `3a49315` Agregar README y marcar T-002 como hecha
- `675a373` Agregar tareas T-003 a T-012 surgidas de la revisión de la PR
- `a27dc77` Agregar bitácora del proyecto

Los commits van sin atribución de Claude Code (pedido del usuario).

### 5. Documentación
- `TAREAS.md`: formato `T-NroTarea | Estado | Descripción`, estados `Pendiente`, `En curso`, `Hecha`.
- `README.md` (T-002): descripción, requisitos (JDK 15+), compilar y ejecutar, cómo se juega, estructura. Los comandos de compilación del README no se probaron tal como están escritos.

### 6. Pull request
- Se redactó la descripción de la PR de `feature/jugadores`.
- Push de la rama a `origin` (`6cb7ea5..3a49315`).
- PR #8 "Feature/jugadores": ya está cerrada y mergeada (2026-11-20), sin comentarios. Se mergeó solo hasta `6cb7ea5`: los commits de documentación (`TAREAS.md`, `README.md`) quedaron fuera y `main` no los tenía. `gh` no está instalado; se consultó por la API pública de GitHub.
- Se escribieron comentarios de revisión del código y se convirtieron en tareas T-003 a T-012.

### 7. Rama de documentación
- Al intentar llevar los commits a una rama nueva desde `main`, el cherry-pick de solo dos commits dio conflicto: `main` no tenía `TAREAS.md`. Se abortó y se rehizo con los cuatro commits de documentación.
- Rama `docs/tareas-bitacora` creada desde `origin/main` y subida al remoto, con hashes nuevos:
  - `62f0252` Agregar archivo TAREAS.md con listado de tareas
  - `46572cc` Agregar README y marcar T-002 como hecha
  - `f27e7d6` Agregar tareas T-003 a T-012 surgidas de la revisión de la PR
  - `4c89280` Agregar bitácora del proyecto
- Los hashes de la sección 4 corresponden a `feature/jugadores`; los de esta sección son los que quedan en la rama nueva.

### 8. PR de documentación y tareas de tests
- Se redactó la descripción de la PR de `docs/tareas-bitacora` y se abrió contra `main` (PR #9, mergeada). `gh` no está instalado, así que la PR se creó desde el navegador.
- Después se mergeó también la PR #10 de `feature/jugadores`. `main` quedó en `b4e5543` con `README.md`, `TAREAS.md` y `BITACORA.md`.
- Se agregaron a `TAREAS.md` las tareas de tests T-013 a T-023: JUnit 5, `Celda`, `Tablero` (construcción, `posicionValida`, `descubrirCelda`, `abrirCeros`, `ganoJugador` y `revelarMinas`), `Jugador` y `JugadorHumano`, `Juego`, `GestorPartidas` y `MenuPrincipal`. Solo se agregaron como tareas; no se escribió ningún test.

### 9. Revisión de ramas
Tras `git fetch --prune`, todas las ramas locales y remotas están completamente incluidas en `main` (no tienen commits propios sin mergear):
- `dev`, `feature/recuperar-partida`, `feature/reorganizar-menu-juego`: antiguas, ya mergeadas.
- `docs/tareas-bitacora`: mergeada con la PR #9.
- `feature/jugadores`: mergeada; la local está 11 commits por detrás de la remota.
- `master`: rama local sin remoto, apunta al mismo commit que `docs/tareas-bitacora` (`4078813`).
- Decisiones del usuario: se borraron, locales y remotas, `feature/recuperar-partida`, `feature/reorganizar-menu-juego`, `docs/tareas-bitacora` y `feature/jugadores` (`git branch -d` confirmó que estaban mergeadas). Se conservaron `dev` y `master`.

## Estado actual
- Rama actual: `main`, al día con `origin/main`.
- Cambios sin commitear en `main`: `TAREAS.md` (T-013 a T-023) y `BITACORA.md` (esta sección). Se dejan así a pedido del usuario, para decidir cómo subirlos.
- Tareas pendientes: T-003 a T-023 (ver `TAREAS.md`).

## Próximos pasos sugeridos
- Subir los cambios de documentación a `main` (rama nueva y PR, o commit directo).
- Configurar JUnit (T-013) y probar el ciclo guardar y recuperar en ejecución.
- Empezar por T-003 y T-005.
