CREATE TABLE tecnico (
  id_tecnico       NUMBER(10) NOT NULL,
  nombres          VARCHAR2(80) NOT NULL,
  apellidos        VARCHAR2(80) NOT NULL,
  nacionalidad     VARCHAR2(50) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  CONSTRAINT pk_tecnico PRIMARY KEY (id_tecnico)
);

CREATE TABLE estadio (
  id_estadio     NUMBER(10) NOT NULL,
  nombre_estadio VARCHAR2(100) NOT NULL,
  capacidad      NUMBER(10) NOT NULL,
  direccion      VARCHAR2(150) NOT NULL,
  ciudad_estadio VARCHAR2(60) NOT NULL,
  barrio         VARCHAR2(60) NOT NULL,
  CONSTRAINT pk_estadio PRIMARY KEY (id_estadio),
  CONSTRAINT uq_estadio_nombre UNIQUE (nombre_estadio)
);

CREATE TABLE equipo (
  id_equipo       NUMBER(10) NOT NULL,
  nombre_equipo   VARCHAR2(100) NOT NULL,
  fecha_fundacion DATE NOT NULL,
  ciudad_equipo   VARCHAR2(60) NOT NULL,
  id_tecnico      NUMBER(10) NOT NULL,
  id_sede         NUMBER(10) NOT NULL,
  escudo_url      VARCHAR2(500),
  CONSTRAINT pk_equipo PRIMARY KEY (id_equipo),
  CONSTRAINT uq_equipo_nombre UNIQUE (nombre_equipo),
  CONSTRAINT uq_equipo_tecnico UNIQUE (id_tecnico),
  CONSTRAINT fk_equipo_tecnico FOREIGN KEY (id_tecnico) REFERENCES tecnico (id_tecnico),
  CONSTRAINT fk_equipo_sede FOREIGN KEY (id_sede) REFERENCES estadio (id_estadio)
);

CREATE TABLE equipo_color (
  id_equipo NUMBER(10) NOT NULL,
  color     VARCHAR2(30) NOT NULL,
  CONSTRAINT pk_equipo_color PRIMARY KEY (id_equipo, color),
  CONSTRAINT fk_equipocolor_equipo FOREIGN KEY (id_equipo) REFERENCES equipo (id_equipo) ON DELETE CASCADE
);

CREATE TABLE torneo (
  id_torneo           NUMBER(10) NOT NULL,
  nombre_torneo       VARCHAR2(100) NOT NULL,
  anio                NUMBER(4) NOT NULL,
  semestre            NUMBER(1) NOT NULL,
  fecha_inicio_torneo DATE NOT NULL,
  fecha_fin_torneo    DATE NOT NULL,
  CONSTRAINT pk_torneo PRIMARY KEY (id_torneo)
);

CREATE TABLE equipo_torneo (
  id_equipo NUMBER(10) NOT NULL,
  id_torneo NUMBER(10) NOT NULL,
  CONSTRAINT pk_equipo_torneo PRIMARY KEY (id_equipo, id_torneo),
  CONSTRAINT fk_eqtor_equipo FOREIGN KEY (id_equipo) REFERENCES equipo (id_equipo),
  CONSTRAINT fk_eqtor_torneo FOREIGN KEY (id_torneo) REFERENCES torneo (id_torneo)
);

CREATE TABLE jugador (
  id_jugador       NUMBER(10) NOT NULL,
  nombres          VARCHAR2(80) NOT NULL,
  apellidos        VARCHAR2(80),
  nacionalidad     VARCHAR2(50) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  posicion         VARCHAR2(15) NOT NULL,
  CONSTRAINT pk_jugador PRIMARY KEY (id_jugador)
);

CREATE TABLE atacante (
  id_jugador   NUMBER(10) NOT NULL,
  tiros_puerta NUMBER(5),
  CONSTRAINT pk_atacante PRIMARY KEY (id_jugador),
  CONSTRAINT fk_atacante_jugador FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE mediocampista (
  id_jugador NUMBER(10) NOT NULL,
  pases      NUMBER(5),
  CONSTRAINT pk_mediocampista PRIMARY KEY (id_jugador),
  CONSTRAINT fk_mediocampista_jugador FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE defensa (
  id_jugador         NUMBER(10) NOT NULL,
  entradas_oportunas NUMBER(5),
  CONSTRAINT pk_defensa PRIMARY KEY (id_jugador),
  CONSTRAINT fk_defensa_jugador FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE portero (
  id_jugador        NUMBER(10) NOT NULL,
  porteria_imbatida NUMBER(5),
  CONSTRAINT pk_portero PRIMARY KEY (id_jugador),
  CONSTRAINT fk_portero_jugador FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE inscripcion (
  id_equipo    NUMBER(10) NOT NULL,
  id_torneo    NUMBER(10) NOT NULL,
  id_jugador   NUMBER(10) NOT NULL,
  dorsal       NUMBER(2) NOT NULL,
  fecha_inicio DATE NOT NULL,
  fecha_fin    DATE,
  CONSTRAINT pk_inscripcion PRIMARY KEY (id_equipo, id_torneo, id_jugador),
  CONSTRAINT uq_inscripcion_jugador UNIQUE (id_torneo, id_jugador),
  CONSTRAINT uq_inscripcion_dorsal UNIQUE (id_equipo, id_torneo, dorsal),
  CONSTRAINT fk_inscripcion_eqtor FOREIGN KEY (id_equipo, id_torneo) REFERENCES equipo_torneo (id_equipo, id_torneo),
  CONSTRAINT fk_inscripcion_jugador FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE arbitro (
  cedula         VARCHAR2(15) NOT NULL,
  nombres        VARCHAR2(80) NOT NULL,
  apellidos      VARCHAR2(80) NOT NULL,
  ciudad_arbitro VARCHAR2(60) NOT NULL,
  categoria      VARCHAR2(30) NOT NULL,
  CONSTRAINT pk_arbitro PRIMARY KEY (cedula)
);

CREATE TABLE partido (
  id_partido   NUMBER(10) NOT NULL,
  fecha        DATE NOT NULL,
  hora         VARCHAR2(5) NOT NULL,
  jornada      NUMBER(3) NOT NULL,
  id_torneo    NUMBER(10) NOT NULL,
  id_estadio   NUMBER(10) NOT NULL,
  id_local     NUMBER(10) NOT NULL,
  id_visitante NUMBER(10) NOT NULL,
  CONSTRAINT pk_partido PRIMARY KEY (id_partido),
  CONSTRAINT fk_partido_torneo FOREIGN KEY (id_torneo) REFERENCES torneo (id_torneo),
  CONSTRAINT fk_partido_estadio FOREIGN KEY (id_estadio) REFERENCES estadio (id_estadio),
  CONSTRAINT fk_partido_local FOREIGN KEY (id_local) REFERENCES equipo (id_equipo),
  CONSTRAINT fk_partido_visitante FOREIGN KEY (id_visitante) REFERENCES equipo (id_equipo)
);

CREATE TABLE resultado (
  id_partido      NUMBER(10) NOT NULL,
  id_resultado    NUMBER(10) NOT NULL,
  goles_local     NUMBER(2) NOT NULL,
  goles_visitante NUMBER(2) NOT NULL,
  CONSTRAINT pk_resultado PRIMARY KEY (id_partido, id_resultado),
  CONSTRAINT uq_resultado_partido UNIQUE (id_partido),
  CONSTRAINT fk_resultado_partido FOREIGN KEY (id_partido) REFERENCES partido (id_partido) ON DELETE CASCADE
);

CREATE TABLE arbitraje (
  id_partido NUMBER(10) NOT NULL,
  cedula     VARCHAR2(15) NOT NULL,
  rol        VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_arbitraje PRIMARY KEY (id_partido, cedula),
  CONSTRAINT fk_arbitraje_partido FOREIGN KEY (id_partido) REFERENCES partido (id_partido) ON DELETE CASCADE,
  CONSTRAINT fk_arbitraje_arbitro FOREIGN KEY (cedula) REFERENCES arbitro (cedula)
);

CREATE TABLE participacion (
  id_participacion NUMBER(10) NOT NULL,
  id_jugador       NUMBER(10) NOT NULL,
  id_partido       NUMBER(10) NOT NULL,
  titular          CHAR(1) NOT NULL,
  minutos_jugados  NUMBER(3) NOT NULL,
  CONSTRAINT pk_participacion PRIMARY KEY (id_participacion),
  CONSTRAINT uq_participacion UNIQUE (id_jugador, id_partido),
  CONSTRAINT fk_participacion_jugador FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
  CONSTRAINT fk_participacion_partido FOREIGN KEY (id_partido) REFERENCES partido (id_partido)
);

CREATE TABLE gol (
  id_participacion NUMBER(10) NOT NULL,
  id_gol           NUMBER(10) NOT NULL,
  tipo_gol         VARCHAR2(30) NOT NULL,
  minuto_gol       NUMBER(3) NOT NULL,
  CONSTRAINT pk_gol PRIMARY KEY (id_participacion, id_gol),
  CONSTRAINT fk_gol_participacion FOREIGN KEY (id_participacion) REFERENCES participacion (id_participacion) ON DELETE CASCADE
);

CREATE TABLE tipo_tarjeta (
  id_tipo       NUMBER(10) NOT NULL,
  color_tarjeta VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_tipo_tarjeta PRIMARY KEY (id_tipo),
  CONSTRAINT uq_tipo_color UNIQUE (color_tarjeta)
);

CREATE TABLE tarjeta (
  id_participacion NUMBER(10) NOT NULL,
  id_tarjeta       NUMBER(10) NOT NULL,
  id_tipo          NUMBER(10) NOT NULL,
  motivo           VARCHAR2(150) NOT NULL,
  minuto_tarjeta   NUMBER(3) NOT NULL,
  CONSTRAINT pk_tarjeta PRIMARY KEY (id_participacion, id_tarjeta),
  CONSTRAINT fk_tarjeta_participacion FOREIGN KEY (id_participacion) REFERENCES participacion (id_participacion) ON DELETE CASCADE,
  CONSTRAINT fk_tarjeta_tipo FOREIGN KEY (id_tipo) REFERENCES tipo_tarjeta (id_tipo)
);

CREATE TABLE rol (
  id_rol     NUMBER(10) NOT NULL,
  nombre_rol VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_rol PRIMARY KEY (id_rol),
  CONSTRAINT uq_rol_nombre UNIQUE (nombre_rol)
);

CREATE TABLE usuario (
  id_usuario          NUMBER(10) NOT NULL,
  usuario             VARCHAR2(50) NOT NULL,
  contrasenia         VARCHAR2(100) NOT NULL,
  correo              VARCHAR2(100) NOT NULL,
  id_rol              NUMBER(10) NOT NULL,
  id_equipo_favorito  NUMBER(10),
  verificado          NUMBER(1) DEFAULT 0 NOT NULL,
  codigo_verificacion VARCHAR2(6),
  expira_verificacion TIMESTAMP,
  CONSTRAINT pk_usuario PRIMARY KEY (id_usuario),
  CONSTRAINT uq_usuario_usuario UNIQUE (usuario),
  CONSTRAINT uq_usuario_correo UNIQUE (correo),
  CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES rol (id_rol),
  CONSTRAINT fk_usuario_equipo FOREIGN KEY (id_equipo_favorito) REFERENCES equipo (id_equipo)
);
