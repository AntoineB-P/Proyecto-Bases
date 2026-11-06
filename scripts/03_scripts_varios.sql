SELECT table_name
FROM user_tables
ORDER BY table_name;

SELECT 'TECNICO' AS tabla, COUNT(*) AS registros FROM tecnico
UNION ALL SELECT 'ESTADIO', COUNT(*) FROM estadio
UNION ALL SELECT 'EQUIPO', COUNT(*) FROM equipo
UNION ALL SELECT 'EQUIPO_COLOR', COUNT(*) FROM equipo_color
UNION ALL SELECT 'TORNEO', COUNT(*) FROM torneo
UNION ALL SELECT 'EQUIPO_TORNEO', COUNT(*) FROM equipo_torneo
UNION ALL SELECT 'JUGADOR', COUNT(*) FROM jugador
UNION ALL SELECT 'ATACANTE', COUNT(*) FROM atacante
UNION ALL SELECT 'MEDIOCAMPISTA', COUNT(*) FROM mediocampista
UNION ALL SELECT 'DEFENSA', COUNT(*) FROM defensa
UNION ALL SELECT 'PORTERO', COUNT(*) FROM portero
UNION ALL SELECT 'INSCRIPCION', COUNT(*) FROM inscripcion
UNION ALL SELECT 'ARBITRO', COUNT(*) FROM arbitro
UNION ALL SELECT 'PARTIDO', COUNT(*) FROM partido
UNION ALL SELECT 'RESULTADO', COUNT(*) FROM resultado
UNION ALL SELECT 'ARBITRAJE', COUNT(*) FROM arbitraje
UNION ALL SELECT 'PARTICIPACION', COUNT(*) FROM participacion
UNION ALL SELECT 'GOL', COUNT(*) FROM gol
UNION ALL SELECT 'TIPO_TARJETA', COUNT(*) FROM tipo_tarjeta
UNION ALL SELECT 'TARJETA', COUNT(*) FROM tarjeta
UNION ALL SELECT 'ROL', COUNT(*) FROM rol
UNION ALL SELECT 'USUARIO', COUNT(*) FROM usuario;

SELECT e.nombre_equipo,
       COUNT(r.gf) AS pj,
       NVL(SUM(CASE WHEN r.gf > r.gc THEN 1 ELSE 0 END), 0) AS pg,
       NVL(SUM(CASE WHEN r.gf = r.gc THEN 1 ELSE 0 END), 0) AS pe,
       NVL(SUM(CASE WHEN r.gf < r.gc THEN 1 ELSE 0 END), 0) AS pp,
       NVL(SUM(r.gf), 0) AS gf,
       NVL(SUM(r.gc), 0) AS gc,
       NVL(SUM(r.gf - r.gc), 0) AS dg,
       NVL(SUM(CASE WHEN r.gf > r.gc THEN 3 WHEN r.gf = r.gc THEN 1 ELSE 0 END), 0) AS pts
FROM equipo_torneo et
JOIN equipo e ON e.id_equipo = et.id_equipo
LEFT JOIN (SELECT p.id_local AS id_equipo, res.goles_local AS gf, res.goles_visitante AS gc
           FROM partido p JOIN resultado res ON res.id_partido = p.id_partido
           WHERE p.id_torneo = 1
           UNION ALL
           SELECT p.id_visitante, res.goles_visitante, res.goles_local
           FROM partido p JOIN resultado res ON res.id_partido = p.id_partido
           WHERE p.id_torneo = 1) r ON r.id_equipo = e.id_equipo
WHERE et.id_torneo = 1
GROUP BY e.nombre_equipo
ORDER BY pts DESC, dg DESC, gf DESC, e.nombre_equipo;

SELECT j.nombres || ' ' || j.apellidos AS jugador,
       (SELECT COUNT(*) FROM participacion pa
        WHERE pa.id_jugador = j.id_jugador) AS partidos,
       (SELECT COUNT(*) FROM gol g
        JOIN participacion pa ON pa.id_participacion = g.id_participacion
        WHERE pa.id_jugador = j.id_jugador) AS goles,
       (SELECT COUNT(*) FROM tarjeta t
        JOIN tipo_tarjeta tt ON tt.id_tipo = t.id_tipo
        JOIN participacion pa ON pa.id_participacion = t.id_participacion
        WHERE pa.id_jugador = j.id_jugador AND tt.color_tarjeta = 'Amarilla') AS amarillas,
       (SELECT COUNT(*) FROM tarjeta t
        JOIN tipo_tarjeta tt ON tt.id_tipo = t.id_tipo
        JOIN participacion pa ON pa.id_participacion = t.id_participacion
        WHERE pa.id_jugador = j.id_jugador AND tt.color_tarjeta = 'Roja') AS rojas
FROM jugador j
ORDER BY goles DESC, jugador
FETCH FIRST 10 ROWS ONLY;

SELECT p.id_partido,
       r.goles_local + r.goles_visitante AS marcador,
       COUNT(g.id_gol) AS goles_registrados
FROM partido p
JOIN resultado r ON r.id_partido = p.id_partido
LEFT JOIN participacion pa ON pa.id_partido = p.id_partido
LEFT JOIN gol g ON g.id_participacion = pa.id_participacion
GROUP BY p.id_partido, r.goles_local, r.goles_visitante
HAVING r.goles_local + r.goles_visitante <> COUNT(g.id_gol)
ORDER BY p.id_partido;

SELECT p.id_partido
FROM partido p
WHERE p.id_partido NOT IN (SELECT id_partido FROM arbitraje);

SET DEFINE ON;

UPDATE usuario
SET id_rol = (SELECT id_rol FROM rol WHERE nombre_rol = 'ADMIN')
WHERE usuario = '&nombre_usuario';

COMMIT;
