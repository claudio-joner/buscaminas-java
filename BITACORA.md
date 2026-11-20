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

Los commits van sin atribución de Claude Code (pedido del usuario).

### 5. Documentación
- `TAREAS.md`: formato `T-NroTarea | Estado | Descripción`, estados `Pendiente`, `En curso`, `Hecha`.
- `README.md` (T-002): descripción, requisitos (JDK 15+), compilar y ejecutar, cómo se juega, estructura. Los comandos de compilación del README no se probaron tal como están escritos.

### 6. Pull request
- Se redactó la descripción de la PR de `feature/jugadores`.
- Push de la rama a `origin` (`6cb7ea5..3a49315`).
- PR #8 "Feature/jugadores": ya está cerrada y mergeada (2026-11-20), sin comentarios. `gh` no está instalado; se consultó por la API pública de GitHub.
- Se escribieron comentarios de revisión del código y se convirtieron en tareas T-003 a T-012.

## Estado actual
- Rama actual: `feature/jugadores`, que ya está mergeada en `main`.
- El commit `675a373` no está en `main` ni subido al remoto.
- Tareas pendientes: T-003 a T-012 (ver `TAREAS.md`).

## Próximos pasos sugeridos
- Crear una rama nueva desde `main` para llevar `TAREAS.md` y esta bitácora.
- Probar el ciclo guardar y recuperar en ejecución.
- Empezar por T-003 y T-005.
