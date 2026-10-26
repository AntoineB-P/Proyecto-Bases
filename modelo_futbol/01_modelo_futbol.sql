-- Modelo de fútbol (01_modelo_futbol) - Oracle
-- Incluye: atributos derivados edad/duracion_dias, obligatoriedad (1,1) y comentarios.

CREATE TABLE ARBITRO (
    cedula VARCHAR2(15) NOT NULL,
    nombres VARCHAR2(60) NOT NULL,
    apellidos VARCHAR2(60) NOT NULL,
    ciudad VARCHAR2(60),
    categoria VARCHAR2(30),
    CONSTRAINT pk_arbitro PRIMARY KEY (cedula)
);

CREATE TABLE ESTADIO (
    id_estadio NUMBER(10) NOT NULL,
    nombre VARCHAR2(80) NOT NULL,
    capacidad NUMBER(7),
    direccion VARCHAR2(120),
    ciudad VARCHAR2(60),
    barrio VARCHAR2(60),
    CONSTRAINT pk_estadio PRIMARY KEY (id_estadio)
);

CREATE TABLE JUGADOR (
    id_jugador NUMBER(10) NOT NULL,
    nombres VARCHAR2(60) NOT NULL,
    apellidos VARCHAR2(60) NOT NULL,
    nacionalidad VARCHAR2(40),
    fecha_nacimiento DATE,
    posicion VARCHAR2(30),
    edad NUMBER(7),
    CONSTRAINT pk_jugador PRIMARY KEY (id_jugador)
);

CREATE TABLE TECNICO (
    id_tecnico NUMBER(10) NOT NULL,
    nombres VARCHAR2(60) NOT NULL,
    apellidos VARCHAR2(60) NOT NULL,
    nacionalidad VARCHAR2(40),
    fecha_nacimiento DATE,
    CONSTRAINT pk_tecnico PRIMARY KEY (id_tecnico)
);

CREATE TABLE TIPO_TARJETA (
    id_tipo NUMBER(10) NOT NULL,
    color VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_tipo_tarjeta PRIMARY KEY (id_tipo)
);

CREATE TABLE TORNEO (
    id_torneo NUMBER(10) NOT NULL,
    nombre VARCHAR2(80) NOT NULL,
    anio NUMBER(4),
    semestre NUMBER(1),
    fecha_inicio DATE,
    fecha_fin DATE,
    duracion_dias NUMBER(7),
    CONSTRAINT pk_torneo PRIMARY KEY (id_torneo)
);

CREATE TABLE EQUIPO (
    id_equipo NUMBER(10) NOT NULL,
    nombre VARCHAR2(80) NOT NULL,
    fecha_fundacion DATE,
    ciudad VARCHAR2(60),
    id_tecnico NUMBER(10) NOT NULL,
    id_estadio NUMBER(10) NOT NULL,
    CONSTRAINT pk_equipo PRIMARY KEY (id_equipo),
    CONSTRAINT uq_equipo_tecnico UNIQUE (id_tecnico)
);

CREATE TABLE EQUIPO_COLOR (
    id_equipo NUMBER(10) NOT NULL,
    color VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_equipo_color PRIMARY KEY (id_equipo, color)
);

CREATE TABLE EQUIPO_TORNEO (
    id_equipo NUMBER(10) NOT NULL,
    id_torneo NUMBER(10) NOT NULL,
    CONSTRAINT pk_equipo_torneo PRIMARY KEY (id_equipo, id_torneo)
);

CREATE TABLE INSCRIPCION (
    id_equipo NUMBER(10) NOT NULL,
    id_torneo NUMBER(10) NOT NULL,
    id_jugador NUMBER(10) NOT NULL,
    fecha_inicio DATE,
    fecha_fin DATE,
    dorsal NUMBER(3),
    CONSTRAINT pk_inscripcion PRIMARY KEY (id_equipo, id_torneo, id_jugador)
);

CREATE TABLE PARTIDO (
    id_partido NUMBER(10) NOT NULL,
    fecha DATE,
    hora VARCHAR2(5),
    jornada NUMBER(3),
    id_torneo NUMBER(10) NOT NULL,
    id_estadio NUMBER(10) NOT NULL,
    id_equipo_local NUMBER(10) NOT NULL,
    id_equipo_visitante NUMBER(10) NOT NULL,
    CONSTRAINT pk_partido PRIMARY KEY (id_partido)
);

CREATE TABLE ARBITRAJE (
    id_partido NUMBER(10) NOT NULL,
    cedula VARCHAR2(15) NOT NULL,
    rol VARCHAR2(30),
    CONSTRAINT pk_arbitraje PRIMARY KEY (id_partido, cedula)
);

CREATE TABLE PARTICIPACION (
    id_participacion NUMBER(10) NOT NULL,
    id_partido NUMBER(10) NOT NULL,
    id_jugador NUMBER(10) NOT NULL,
    minutos_jugados NUMBER(3),
    titular CHAR(1) DEFAULT 'N',
    CONSTRAINT pk_participacion PRIMARY KEY (id_participacion)
);

CREATE TABLE RESULTADO (
    id_partido NUMBER(10) NOT NULL,
    id_resultado NUMBER(10) NOT NULL,
    goles_local NUMBER(3) DEFAULT 0,
    goles_visitante NUMBER(3) DEFAULT 0,
    CONSTRAINT pk_resultado PRIMARY KEY (id_partido, id_resultado),
    CONSTRAINT uq_resultado_partido UNIQUE (id_partido)
);

CREATE TABLE GOL (
    id_participacion NUMBER(10) NOT NULL,
    id_gol NUMBER(10) NOT NULL,
    tipo VARCHAR2(30),
    minuto NUMBER(3),
    CONSTRAINT pk_gol PRIMARY KEY (id_participacion, id_gol)
);

CREATE TABLE TARJETA (
    id_participacion NUMBER(10) NOT NULL,
    id_tarjeta NUMBER(10) NOT NULL,
    motivo VARCHAR2(120),
    minuto NUMBER(3),
    id_tipo NUMBER(10) NOT NULL,
    CONSTRAINT pk_tarjeta PRIMARY KEY (id_participacion, id_tarjeta)
);

ALTER TABLE EQUIPO ADD CONSTRAINT fk_equipo_estadio FOREIGN KEY (id_estadio)
    REFERENCES ESTADIO (id_estadio);

ALTER TABLE EQUIPO ADD CONSTRAINT fk_equipo_tecnico FOREIGN KEY (id_tecnico)
    REFERENCES TECNICO (id_tecnico);

ALTER TABLE EQUIPO_COLOR ADD CONSTRAINT fk_equipocolor_equipo FOREIGN KEY (id_equipo)
    REFERENCES EQUIPO (id_equipo);

ALTER TABLE EQUIPO_TORNEO ADD CONSTRAINT fk_eqtor_equipo FOREIGN KEY (id_equipo)
    REFERENCES EQUIPO (id_equipo);

ALTER TABLE EQUIPO_TORNEO ADD CONSTRAINT fk_eqtor_torneo FOREIGN KEY (id_torneo)
    REFERENCES TORNEO (id_torneo);

ALTER TABLE INSCRIPCION ADD CONSTRAINT fk_insc_equipo FOREIGN KEY (id_equipo)
    REFERENCES EQUIPO (id_equipo);

ALTER TABLE INSCRIPCION ADD CONSTRAINT fk_insc_jugador FOREIGN KEY (id_jugador)
    REFERENCES JUGADOR (id_jugador);

ALTER TABLE INSCRIPCION ADD CONSTRAINT fk_insc_torneo FOREIGN KEY (id_torneo)
    REFERENCES TORNEO (id_torneo);

ALTER TABLE PARTIDO ADD CONSTRAINT fk_partido_estadio FOREIGN KEY (id_estadio)
    REFERENCES ESTADIO (id_estadio);

ALTER TABLE PARTIDO ADD CONSTRAINT fk_partido_local FOREIGN KEY (id_equipo_local)
    REFERENCES EQUIPO (id_equipo);

ALTER TABLE PARTIDO ADD CONSTRAINT fk_partido_torneo FOREIGN KEY (id_torneo)
    REFERENCES TORNEO (id_torneo);

ALTER TABLE PARTIDO ADD CONSTRAINT fk_partido_visitante FOREIGN KEY (id_equipo_visitante)
    REFERENCES EQUIPO (id_equipo);

ALTER TABLE ARBITRAJE ADD CONSTRAINT fk_arbitraje_arbitro FOREIGN KEY (cedula)
    REFERENCES ARBITRO (cedula);

ALTER TABLE ARBITRAJE ADD CONSTRAINT fk_arbitraje_partido FOREIGN KEY (id_partido)
    REFERENCES PARTIDO (id_partido);

ALTER TABLE PARTICIPACION ADD CONSTRAINT fk_particip_jugador FOREIGN KEY (id_jugador)
    REFERENCES JUGADOR (id_jugador);

ALTER TABLE PARTICIPACION ADD CONSTRAINT fk_particip_partido FOREIGN KEY (id_partido)
    REFERENCES PARTIDO (id_partido);

ALTER TABLE RESULTADO ADD CONSTRAINT fk_resultado_partido FOREIGN KEY (id_partido)
    REFERENCES PARTIDO (id_partido);

ALTER TABLE GOL ADD CONSTRAINT fk_gol_particip FOREIGN KEY (id_participacion)
    REFERENCES PARTICIPACION (id_participacion);

ALTER TABLE TARJETA ADD CONSTRAINT fk_tarjeta_particip FOREIGN KEY (id_participacion)
    REFERENCES PARTICIPACION (id_participacion);

ALTER TABLE TARJETA ADD CONSTRAINT fk_tarjeta_tipo FOREIGN KEY (id_tipo)
    REFERENCES TIPO_TARJETA (id_tipo);


COMMENT ON TABLE ARBITRO IS 'Árbitros que dirigen los partidos. Guarda sus datos personales, su ciudad y su categoría.';
COMMENT ON COLUMN ARBITRO.cedula IS 'Cédula (documento de identidad) del árbitro. Es la llave primaria y la referencia ARBITRAJE.';
COMMENT ON COLUMN ARBITRO.nombres IS 'Nombres de pila del árbitro. Junto con apellidos forma el atributo compuesto Nombre del diagrama; se usa para mostrarlo y buscarlo.';
COMMENT ON COLUMN ARBITRO.apellidos IS 'Apellidos del árbitro. Junto con nombres forma el atributo compuesto Nombre del diagrama; se usa para mostrarlo y ordenarlo.';
COMMENT ON COLUMN ARBITRO.ciudad IS 'Ciudad del árbitro (opcional). Permite filtrar árbitros por ubicación al asignarlos a partidos.';
COMMENT ON COLUMN ARBITRO.categoria IS 'Categoría o nivel del árbitro (por ejemplo nacional o internacional) (opcional). Sirve para asignar árbitros según la importancia del partido.';

COMMENT ON TABLE ESTADIO IS 'Estadios donde se juegan los partidos y que sirven de sede a los equipos. Guarda su capacidad y su ubicación.';
COMMENT ON COLUMN ESTADIO.id_estadio IS 'Identificador único del estadio (llave primaria). Lo referencian EQUIPO (sede) y PARTIDO (lugar del encuentro).';
COMMENT ON COLUMN ESTADIO.nombre IS 'Nombre del estadio. Se usa para identificarlo en calendarios y reportes.';
COMMENT ON COLUMN ESTADIO.capacidad IS 'Aforo máximo de espectadores del estadio (opcional). Sirve para reportes y para comparar escenarios.';
COMMENT ON COLUMN ESTADIO.direccion IS 'Dirección del estadio (opcional). Hace parte del atributo compuesto Ubicación del diagrama.';
COMMENT ON COLUMN ESTADIO.ciudad IS 'Ciudad donde queda el estadio (opcional). Hace parte del atributo compuesto Ubicación; permite filtrar estadios por ciudad.';
COMMENT ON COLUMN ESTADIO.barrio IS 'Barrio donde queda el estadio (opcional). Hace parte del atributo compuesto Ubicación; precisa la localización.';

COMMENT ON TABLE JUGADOR IS 'Jugadores registrados en el sistema. Se inscriben a equipos en cada torneo (INSCRIPCION) y participan en los partidos (PARTICIPACION).';
COMMENT ON COLUMN JUGADOR.id_jugador IS 'Identificador único del jugador (llave primaria). Lo referencian INSCRIPCION y PARTICIPACION.';
COMMENT ON COLUMN JUGADOR.nombres IS 'Nombres de pila del jugador. Junto con apellidos forma el atributo compuesto Nombre del diagrama; se usa para mostrarlo y buscarlo.';
COMMENT ON COLUMN JUGADOR.apellidos IS 'Apellidos del jugador. Junto con nombres forma el atributo compuesto Nombre del diagrama; se usa para mostrarlo y ordenarlo.';
COMMENT ON COLUMN JUGADOR.nacionalidad IS 'País de origen del jugador (opcional). Sirve para reportes y estadísticas, por ejemplo jugadores extranjeros por equipo.';
COMMENT ON COLUMN JUGADOR.fecha_nacimiento IS 'Fecha de nacimiento del jugador (opcional). Es la base para calcular el atributo derivado edad.';
COMMENT ON COLUMN JUGADOR.posicion IS 'Posición en la que juega (por ejemplo portero, defensa, volante o delantero) (opcional). Sirve para armar alineaciones y filtrar jugadores.';
COMMENT ON COLUMN JUGADOR.edad IS 'ATRIBUTO DERIVADO: edad en años del jugador, calculada a partir de fecha_nacimiento y la fecha actual. Se incluye porque aparece en el diagrama; para que no quede desactualizado conviene calcularlo en consultas o vistas en vez de capturarlo a mano.';

COMMENT ON TABLE TECNICO IS 'Directores técnicos (entrenadores) del torneo. Cada técnico dirige como máximo un equipo (relación 1:1 Dirigir) y sirve para saber quién está a cargo de cada equipo.';
COMMENT ON COLUMN TECNICO.id_tecnico IS 'Identificador único del técnico (llave primaria). EQUIPO lo referencia para indicar qué técnico dirige a cada equipo.';
COMMENT ON COLUMN TECNICO.nombres IS 'Nombres de pila del técnico. Junto con apellidos forma el atributo compuesto Nombre del diagrama; se usa para mostrarlo y buscarlo.';
COMMENT ON COLUMN TECNICO.apellidos IS 'Apellidos del técnico. Junto con nombres forma el atributo compuesto Nombre del diagrama; se usa para mostrarlo y ordenarlo.';
COMMENT ON COLUMN TECNICO.nacionalidad IS 'País de origen del técnico (opcional). Sirve para reportes y estadísticas, por ejemplo cuántos técnicos extranjeros hay.';
COMMENT ON COLUMN TECNICO.fecha_nacimiento IS 'Fecha de nacimiento del técnico (opcional). Permite conocer y calcular su edad.';

COMMENT ON TABLE TIPO_TARJETA IS 'Catálogo de tipos de tarjeta (por ejemplo amarilla y roja). Evita repetir el color en cada tarjeta que se registra.';
COMMENT ON COLUMN TIPO_TARJETA.id_tipo IS 'Identificador único del tipo de tarjeta (llave primaria). TARJETA lo referencia.';
COMMENT ON COLUMN TIPO_TARJETA.color IS 'Color del tipo de tarjeta (por ejemplo amarilla o roja). Es el nombre con el que se reconoce el tipo.';

COMMENT ON TABLE TORNEO IS 'Torneos o campeonatos organizados por periodo (año y semestre). Agrupan los equipos participantes, los jugadores inscritos y los partidos que se juegan.';
COMMENT ON COLUMN TORNEO.id_torneo IS 'Identificador único del torneo (llave primaria). Lo referencian PARTIDO, EQUIPO_TORNEO e INSCRIPCION.';
COMMENT ON COLUMN TORNEO.nombre IS 'Nombre del torneo o campeonato. Se usa para identificarlo en calendarios y reportes.';
COMMENT ON COLUMN TORNEO.anio IS 'Año en que se juega el torneo (opcional). Sirve para ordenar y filtrar torneos por periodo.';
COMMENT ON COLUMN TORNEO.semestre IS 'Semestre del año en que se juega el torneo, por ejemplo 1 o 2 (opcional). Complementa al año para distinguir torneos del mismo año.';
COMMENT ON COLUMN TORNEO.fecha_inicio IS 'Fecha en que arranca el torneo (opcional). Se usa para calcular la duración del torneo.';
COMMENT ON COLUMN TORNEO.fecha_fin IS 'Fecha en que termina el torneo (opcional). Se usa para calcular la duración del torneo.';
COMMENT ON COLUMN TORNEO.duracion_dias IS 'ATRIBUTO DERIVADO: duración del torneo en días, calculada como fecha_fin menos fecha_inicio. Se incluye porque aparece en el diagrama; conviene calcularlo en consultas o vistas para que siempre sea consistente con las fechas.';

COMMENT ON TABLE EQUIPO IS 'Equipos (clubes) que participan en los torneos. Es la entidad central: juega partidos como local o visitante, tiene un técnico, un estadio sede, colores y jugadores inscritos.';
COMMENT ON COLUMN EQUIPO.id_equipo IS 'Identificador único del equipo (llave primaria). Lo referencian PARTIDO, EQUIPO_TORNEO, EQUIPO_COLOR e INSCRIPCION.';
COMMENT ON COLUMN EQUIPO.nombre IS 'Nombre del equipo. Se usa para identificarlo en calendarios, resultados y reportes.';
COMMENT ON COLUMN EQUIPO.fecha_fundacion IS 'Fecha en que se fundó el equipo (opcional). Dato histórico del club.';
COMMENT ON COLUMN EQUIPO.ciudad IS 'Ciudad de origen del equipo (opcional). Permite agrupar o filtrar equipos por ubicación.';
COMMENT ON COLUMN EQUIPO.id_tecnico IS 'Llave foránea a TECNICO: técnico que dirige al equipo (relación Dirigir 1:1). Es obligatoria y única: un equipo tiene exactamente un técnico y un técnico dirige como máximo un equipo.';
COMMENT ON COLUMN EQUIPO.id_estadio IS 'Llave foránea a ESTADIO: estadio sede del equipo (relación Tener:sede). Es obligatoria; un estadio puede ser sede de varios equipos.';

COMMENT ON TABLE EQUIPO_COLOR IS 'Resuelve el atributo multivaluado colores de EQUIPO: guarda un registro por cada color que tiene un equipo, ya que un equipo puede tener varios colores.';
COMMENT ON COLUMN EQUIPO_COLOR.id_equipo IS 'Llave foránea a EQUIPO y parte de la llave primaria: indica a qué equipo pertenece el color.';
COMMENT ON COLUMN EQUIPO_COLOR.color IS 'Nombre del color del equipo (por ejemplo rojo o blanco). Parte de la llave primaria para que un color no se repita en el mismo equipo.';

COMMENT ON TABLE EQUIPO_TORNEO IS 'Resuelve la relación M:N Participar entre EQUIPO y TORNEO: registra qué equipos participan en qué torneos (un equipo puede jugar varios torneos y un torneo tiene varios equipos).';
COMMENT ON COLUMN EQUIPO_TORNEO.id_equipo IS 'Llave foránea a EQUIPO y parte de la llave primaria: equipo que participa en el torneo.';
COMMENT ON COLUMN EQUIPO_TORNEO.id_torneo IS 'Llave foránea a TORNEO y parte de la llave primaria: torneo en el que participa el equipo.';

COMMENT ON TABLE INSCRIPCION IS 'Resuelve la relación ternaria Inscribir (M:N:M) entre EQUIPO, TORNEO y JUGADOR: registra con qué equipo y en qué torneo está inscrito cada jugador, con su dorsal y las fechas de vigencia.';
COMMENT ON COLUMN INSCRIPCION.id_equipo IS 'Llave foránea a EQUIPO y parte de la llave primaria: equipo con el que se inscribe el jugador.';
COMMENT ON COLUMN INSCRIPCION.id_torneo IS 'Llave foránea a TORNEO y parte de la llave primaria: torneo en el que se inscribe el jugador.';
COMMENT ON COLUMN INSCRIPCION.id_jugador IS 'Llave foránea a JUGADOR y parte de la llave primaria: jugador que se inscribe.';
COMMENT ON COLUMN INSCRIPCION.fecha_inicio IS 'Fecha desde la que el jugador está inscrito con el equipo en ese torneo (opcional).';
COMMENT ON COLUMN INSCRIPCION.fecha_fin IS 'Fecha hasta la que el jugador estuvo inscrito con el equipo en ese torneo (opcional). Permite conservar el historial de traspasos.';
COMMENT ON COLUMN INSCRIPCION.dorsal IS 'Número de camiseta que usa el jugador con ese equipo en ese torneo (opcional).';

COMMENT ON TABLE PARTIDO IS 'Encuentros entre dos equipos (local y visitante) dentro de un torneo, jugados en un estadio en una fecha y hora. Es el eje del que dependen el resultado, los árbitros y las participaciones.';
COMMENT ON COLUMN PARTIDO.id_partido IS 'Identificador único del partido (llave primaria). Lo referencian RESULTADO, ARBITRAJE y PARTICIPACION.';
COMMENT ON COLUMN PARTIDO.fecha IS 'Fecha en que se juega el partido (opcional). Sirve para armar el calendario y ordenar los encuentros.';
COMMENT ON COLUMN PARTIDO.hora IS 'Hora programada de inicio del partido, guardada como texto (por ejemplo 15:30) (opcional).';
COMMENT ON COLUMN PARTIDO.jornada IS 'Número de jornada del torneo a la que pertenece el partido (opcional). Agrupa los partidos del calendario.';
COMMENT ON COLUMN PARTIDO.id_torneo IS 'Llave foránea a TORNEO: torneo al que pertenece el partido (relación Tener). Es obligatoria.';
COMMENT ON COLUMN PARTIDO.id_estadio IS 'Llave foránea a ESTADIO: estadio donde se juega el partido (relación Jugar). Es obligatoria; un estadio puede albergar varios partidos.';
COMMENT ON COLUMN PARTIDO.id_equipo_local IS 'Llave foránea a EQUIPO: equipo que juega como local (relación Ser_local). Es obligatoria.';
COMMENT ON COLUMN PARTIDO.id_equipo_visitante IS 'Llave foránea a EQUIPO: equipo que juega como visitante (relación Ser_visitante). Es obligatoria.';

COMMENT ON TABLE ARBITRAJE IS 'Resuelve la relación M:N Arbitrar entre PARTIDO y ARBITRO: registra qué árbitros dirigen cada partido y con qué rol.';
COMMENT ON COLUMN ARBITRAJE.id_partido IS 'Llave foránea a PARTIDO y parte de la llave primaria: partido que se arbitra.';
COMMENT ON COLUMN ARBITRAJE.cedula IS 'Llave foránea a ARBITRO y parte de la llave primaria: árbitro asignado al partido.';
COMMENT ON COLUMN ARBITRAJE.rol IS 'Función del árbitro en ese partido (por ejemplo central, asistente o cuarto árbitro) (opcional).';

COMMENT ON TABLE PARTICIPACION IS 'Intervención de un jugador en un partido (resuelve la relación M:N Jugar entre PARTIDO y JUGADOR). De ella dependen los goles y las tarjetas.';
COMMENT ON COLUMN PARTICIPACION.id_participacion IS 'Identificador único de la participación (llave primaria). Lo referencian GOL y TARJETA.';
COMMENT ON COLUMN PARTICIPACION.id_partido IS 'Llave foránea a PARTIDO: partido en el que interviene el jugador (relación Incluir). Es obligatoria.';
COMMENT ON COLUMN PARTICIPACION.id_jugador IS 'Llave foránea a JUGADOR: jugador que participa en el partido (relación Realizar). Es obligatoria.';
COMMENT ON COLUMN PARTICIPACION.minutos_jugados IS 'Minutos que el jugador estuvo en cancha durante el partido (opcional). Sirve para estadísticas de rendimiento.';
COMMENT ON COLUMN PARTICIPACION.titular IS 'Indica si el jugador salió en el equipo inicial (verdadero) o como suplente (falso) (opcional).';

COMMENT ON TABLE RESULTADO IS 'Marcador final de un partido. Es una entidad débil de PARTIDO (relación 1:1 Tener): guarda los goles de cada equipo para determinar el ganador y calcular posiciones.';
COMMENT ON COLUMN RESULTADO.id_partido IS 'Llave foránea a PARTIDO y parte de la llave primaria (entidad débil). Además es única: cada partido tiene como máximo un resultado.';
COMMENT ON COLUMN RESULTADO.id_resultado IS 'Identificador del resultado; junto con id_partido forma la llave primaria.';
COMMENT ON COLUMN RESULTADO.goles_local IS 'Cantidad de goles anotados por el equipo local en el partido (opcional).';
COMMENT ON COLUMN RESULTADO.goles_visitante IS 'Cantidad de goles anotados por el equipo visitante en el partido (opcional).';

COMMENT ON TABLE GOL IS 'Goles anotados en un partido. Es una entidad débil de PARTICIPACION (relación Marcar): cada gol queda ligado al jugador y al partido en que se anotó.';
COMMENT ON COLUMN GOL.id_participacion IS 'Llave foránea a PARTICIPACION y parte de la llave primaria (entidad débil): participación del jugador que anotó el gol.';
COMMENT ON COLUMN GOL.id_gol IS 'Identificador del gol; junto con id_participacion forma la llave primaria.';
COMMENT ON COLUMN GOL.tipo IS 'Tipo de gol (por ejemplo jugada, penal, tiro libre o autogol) (opcional). Sirve para estadísticas.';
COMMENT ON COLUMN GOL.minuto IS 'Minuto del partido en que se anotó el gol (opcional).';

COMMENT ON TABLE TARJETA IS 'Tarjetas disciplinarias mostradas a un jugador durante su participación en un partido. Es una entidad débil de PARTICIPACION (relación Ocurre).';
COMMENT ON COLUMN TARJETA.id_participacion IS 'Llave foránea a PARTICIPACION y parte de la llave primaria (entidad débil): participación del jugador sancionado.';
COMMENT ON COLUMN TARJETA.id_tarjeta IS 'Identificador de la tarjeta; junto con id_participacion forma la llave primaria.';
COMMENT ON COLUMN TARJETA.motivo IS 'Razón por la que se mostró la tarjeta (opcional). Sirve para el control disciplinario.';
COMMENT ON COLUMN TARJETA.minuto IS 'Minuto del partido en que se mostró la tarjeta (opcional).';
COMMENT ON COLUMN TARJETA.id_tipo IS 'Llave foránea a TIPO_TARJETA: indica si la tarjeta es amarilla, roja, etc. (relación Ser). Es obligatoria.';
