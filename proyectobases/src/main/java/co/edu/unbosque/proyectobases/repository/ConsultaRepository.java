package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.dto.EstadisticaJugadorDTO;
import co.edu.unbosque.proyectobases.dto.HistorialEquipoDTO;
import co.edu.unbosque.proyectobases.dto.PosicionDTO;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class ConsultaRepository {

	/**
	 * Tabla de posiciones de un torneo. Cada partido con resultado se cuenta dos
	 * veces (una desde el lado del local y otra desde el del visitante) y luego se
	 * suma por equipo. Los equipos sin partidos jugados salen con ceros.
	 */
	public List<PosicionDTO> tablaPosiciones(Long idTorneo) {
		String sql = "SELECT e.id_equipo, e.nombre_equipo, COUNT(r.gf) AS partidos_jugados, "
				+ "  NVL(SUM(CASE WHEN r.gf > r.gc THEN 1 ELSE 0 END), 0) AS ganados, "
				+ "  NVL(SUM(CASE WHEN r.gf = r.gc THEN 1 ELSE 0 END), 0) AS empatados, "
				+ "  NVL(SUM(CASE WHEN r.gf < r.gc THEN 1 ELSE 0 END), 0) AS perdidos, "
				+ "  NVL(SUM(r.gf), 0) AS goles_favor, "
				+ "  NVL(SUM(r.gc), 0) AS goles_contra, "
				+ "  NVL(SUM(r.gf - r.gc), 0) AS diferencia, "
				+ "  NVL(SUM(CASE WHEN r.gf > r.gc THEN 3 WHEN r.gf = r.gc THEN 1 ELSE 0 END), 0) AS puntos "
				+ "FROM equipo_torneo et "
				+ "JOIN equipo e ON e.id_equipo = et.id_equipo "
				+ "LEFT JOIN ( "
				+ "  SELECT p.id_local AS id_equipo, res.goles_local AS gf, res.goles_visitante AS gc "
				+ "  FROM partido p JOIN resultado res ON res.id_partido = p.id_partido "
				+ "  WHERE p.id_torneo = ? "
				+ "  UNION ALL "
				+ "  SELECT p.id_visitante, res.goles_visitante, res.goles_local "
				+ "  FROM partido p JOIN resultado res ON res.id_partido = p.id_partido "
				+ "  WHERE p.id_torneo = ? "
				+ ") r ON r.id_equipo = e.id_equipo "
				+ "WHERE et.id_torneo = ? "
				+ "GROUP BY e.id_equipo, e.nombre_equipo "
				+ "ORDER BY puntos DESC, diferencia DESC, goles_favor DESC, e.nombre_equipo";
		List<PosicionDTO> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idTorneo);
			ps.setLong(2, idTorneo);
			ps.setLong(3, idTorneo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					PosicionDTO p = new PosicionDTO();
					p.setIdEquipo(rs.getLong("id_equipo"));
					p.setEquipo(rs.getString("nombre_equipo"));
					p.setPartidosJugados(rs.getInt("partidos_jugados"));
					p.setGanados(rs.getInt("ganados"));
					p.setEmpatados(rs.getInt("empatados"));
					p.setPerdidos(rs.getInt("perdidos"));
					p.setGolesFavor(rs.getInt("goles_favor"));
					p.setGolesContra(rs.getInt("goles_contra"));
					p.setDiferencia(rs.getInt("diferencia"));
					p.setPuntos(rs.getInt("puntos"));
					lista.add(p);
				}
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
		return lista;
	}

	/** Partidos jugados por un equipo en un torneo, vistos desde ese equipo. */
	public List<HistorialEquipoDTO> historialEquipo(Long idEquipo, Long idTorneo) {
		String sql = "SELECT p.id_partido, p.fecha, p.jornada, "
				+ "  CASE WHEN p.id_local = ? THEN ev.nombre_equipo ELSE el.nombre_equipo END AS rival, "
				+ "  CASE WHEN p.id_local = ? THEN 'LOCAL' ELSE 'VISITANTE' END AS condicion, "
				+ "  CASE WHEN p.id_local = ? THEN res.goles_local ELSE res.goles_visitante END AS goles_favor, "
				+ "  CASE WHEN p.id_local = ? THEN res.goles_visitante ELSE res.goles_local END AS goles_contra "
				+ "FROM partido p "
				+ "JOIN equipo el ON el.id_equipo = p.id_local "
				+ "JOIN equipo ev ON ev.id_equipo = p.id_visitante "
				+ "JOIN resultado res ON res.id_partido = p.id_partido "
				+ "WHERE p.id_torneo = ? AND (p.id_local = ? OR p.id_visitante = ?) "
				+ "ORDER BY p.fecha, p.jornada";
		List<HistorialEquipoDTO> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			ps.setLong(2, idEquipo);
			ps.setLong(3, idEquipo);
			ps.setLong(4, idEquipo);
			ps.setLong(5, idTorneo);
			ps.setLong(6, idEquipo);
			ps.setLong(7, idEquipo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					HistorialEquipoDTO h = new HistorialEquipoDTO();
					int favor = rs.getInt("goles_favor");
					int contra = rs.getInt("goles_contra");
					h.setIdPartido(rs.getLong("id_partido"));
					h.setFecha(rs.getDate("fecha").toLocalDate());
					h.setJornada(rs.getInt("jornada"));
					h.setRival(rs.getString("rival"));
					h.setCondicion(rs.getString("condicion"));
					h.setGolesFavor(favor);
					h.setGolesContra(contra);
					if (favor > contra) {
						h.setResultado("G");
					} else if (favor == contra) {
						h.setResultado("E");
					} else {
						h.setResultado("P");
					}
					lista.add(h);
				}
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
		return lista;
	}

	/** Partidos, goles y tarjetas de cada jugador inscrito en el torneo. */
	public List<EstadisticaJugadorDTO> estadisticasJugadores(Long idTorneo) {
		String sql = "SELECT j.id_jugador, j.nombres || ' ' || j.apellidos AS nombre_jugador, "
				+ "  (SELECT COUNT(*) FROM participacion pa "
				+ "     JOIN partido pt ON pt.id_partido = pa.id_partido "
				+ "     WHERE pa.id_jugador = j.id_jugador AND pt.id_torneo = ?) AS partidos_jugados, "
				+ "  (SELECT COUNT(*) FROM gol g "
				+ "     JOIN participacion pa ON pa.id_participacion = g.id_participacion "
				+ "     JOIN partido pt ON pt.id_partido = pa.id_partido "
				+ "     WHERE pa.id_jugador = j.id_jugador AND pt.id_torneo = ?) AS goles, "
				+ "  (SELECT COUNT(*) FROM tarjeta t "
				+ "     JOIN tipo_tarjeta tt ON tt.id_tipo = t.id_tipo "
				+ "     JOIN participacion pa ON pa.id_participacion = t.id_participacion "
				+ "     JOIN partido pt ON pt.id_partido = pa.id_partido "
				+ "     WHERE pa.id_jugador = j.id_jugador AND pt.id_torneo = ? "
				+ "       AND tt.color_tarjeta = 'Amarilla') AS amarillas, "
				+ "  (SELECT COUNT(*) FROM tarjeta t "
				+ "     JOIN tipo_tarjeta tt ON tt.id_tipo = t.id_tipo "
				+ "     JOIN participacion pa ON pa.id_participacion = t.id_participacion "
				+ "     JOIN partido pt ON pt.id_partido = pa.id_partido "
				+ "     WHERE pa.id_jugador = j.id_jugador AND pt.id_torneo = ? "
				+ "       AND tt.color_tarjeta = 'Roja') AS rojas "
				+ "FROM jugador j "
				+ "WHERE j.id_jugador IN (SELECT i.id_jugador FROM inscripcion i WHERE i.id_torneo = ?) "
				+ "ORDER BY goles DESC, nombre_jugador";
		List<EstadisticaJugadorDTO> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			for (int i = 1; i <= 5; i++) {
				ps.setLong(i, idTorneo);
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					EstadisticaJugadorDTO e = new EstadisticaJugadorDTO();
					e.setIdJugador(rs.getLong("id_jugador"));
					e.setNombreJugador(rs.getString("nombre_jugador"));
					e.setPartidosJugados(rs.getInt("partidos_jugados"));
					e.setGoles(rs.getInt("goles"));
					e.setAmarillas(rs.getInt("amarillas"));
					e.setRojas(rs.getInt("rojas"));
					lista.add(e);
				}
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
		return lista;
	}
}