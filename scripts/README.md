# Scripts de la base de datos – Liga BetPlay

Scripts para pasar el modelo lógico al modelo físico en **Oracle AI Database 26ai Free**. La base del proyecto vive en la base conectable **FREEPDB1**, dentro del esquema del usuario **LIGA**.

| Script | Qué hace |
|---|---|
| `01_creacion_objetos.sql` | Crea las 22 tablas con sus llaves primarias, llaves foráneas, restricciones `UNIQUE` y columnas obligatorias (`NOT NULL`). |
| `02_insercion_registros.sql` | Inserta los datos de la liga: técnicos, estadios, equipos y sus colores, torneo, jugadores con su posición, inscripciones, árbitros, partidos con sus árbitros, resultados, participaciones, goles, tarjetas y roles. |
| `03_scripts_varios.sql` | Consultas de comprobación y de uso diario (ver más abajo). |

## Antes de empezar: crear el usuario LIGA

Conectado a **FREEPDB1** con el usuario **SYSTEM** (en SQL Developer, la conexión `Liga_System`):

```sql
CREATE USER liga IDENTIFIED BY "<clave>"
  DEFAULT TABLESPACE users
  QUOTA UNLIMITED ON users;

GRANT CREATE SESSION, CREATE TABLE TO liga;
```

Después se crea en SQL Developer la conexión `Liga_Normal` con el usuario **LIGA**: host `localhost`, puerto `1521`, nombre de servicio `FREEPDB1`.

## Orden de ejecución

Todos los scripts se ejecutan conectado como **LIGA**, en este orden, con **Ejecutar script (F5)**:

1. `01_creacion_objetos.sql`
2. `02_insercion_registros.sql` (termina con `COMMIT`)
3. `03_scripts_varios.sql` (cuando se necesite)

Las tablas están ordenadas para que cada llave foránea apunte a una tabla que ya existe: primero las que no dependen de nadie (TECNICO, ESTADIO, TORNEO) y al final las que dependen de otras (GOL, TARJETA, USUARIO). Los `INSERT` siguen ese mismo orden.

## Decisiones del modelo físico

- **Restricciones:** la base garantiza las llaves primarias, las llaves foráneas, los valores únicos y las columnas obligatorias. Las reglas de rangos y valores permitidos (minutos entre 1 y 120, posiciones válidas, dorsal entre 1 y 99, etc.) las valida el backend antes de guardar.
- **ON DELETE CASCADE:** lo tienen las tablas que no tienen sentido sin su dueño: RESULTADO y ARBITRAJE (de PARTIDO), GOL y TARJETA (de PARTICIPACION) y EQUIPO_COLOR (de EQUIPO).
- **Especialización de JUGADOR:** ATACANTE, MEDIOCAMPISTA, DEFENSA y PORTERO tienen como llave primaria el `id_jugador`, que también es llave foránea hacia JUGADOR.
- **Atributos derivados:** la edad del jugador, la duración del torneo y la tabla de posiciones no se guardan; se calculan con consultas.
- **Datos cargados:** corresponden al torneo Liga BetPlay Dimayor II-2026. Las jornadas 1 a 6 ya se jugaron (60 partidos con resultado, goles y tarjetas) y la jornada 7 está programada (10 partidos con su árbitro, todavía sin resultado). Por eso hay 70 partidos y 60 resultados.
- **Usuarios:** el script de inserción no trae usuarios, porque sus correos y contraseñas son datos personales. Los usuarios se crean desde la página de registro de la aplicación web, que guarda la contraseña cifrada.

## Contenido de `03_scripts_varios.sql`

1. Lista de las tablas del esquema LIGA.
2. Cantidad de registros de cada tabla.
3. Tabla de posiciones del torneo 1 (se calcula con los resultados, no se guarda).
4. Los 10 jugadores con más goles, con sus partidos y tarjetas.
5. Comprobación de que el marcador de cada partido coincide con los goles registrados (no debe devolver filas).
6. Partidos que no tienen ningún árbitro asignado (no debe devolver filas).
7. Convertir a un usuario en administrador: SQL Developer pide el nombre de usuario (variable de sustitución `&nombre_usuario`). Después, ese usuario debe cerrar sesión y volver a entrar en la aplicación.
