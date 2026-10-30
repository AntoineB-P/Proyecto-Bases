package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Inscripcion;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class InscripcionRepository {

	private static final String SELECT = "SELECT id_equipo, id_torneo, id_jugador, dorsal, fecha_inicio, fecha_fin "
			+ "FROM inscripcion";

	private Inscripcion mapear(ResultSet rs) throws SQLException {
		Inscripcion i = new Inscripcion();
		i.setIdEquipo(rs.getLong("id_equipo"));
		i.setIdTorneo(rs.getLong("id_torneo"));
		i.setIdJugador(rs.getLong("id_jugador"));
		i.setDorsal(rs.getInt("dorsal"));
		i.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
		Date fin = rs.getDate("fecha_fin");
		i.setFechaFin(fin == null ? null : fin.toLocalDate());
		return i;
	}

	/** Plantilla inscrita de un equipo en un torneo. */
	public List<Inscripcion> listarPorEquipoYTorneo(Long idEquipo, Long idTorneo) {
		String sql = SELECT + " WHERE id_equipo = ? AND id_torneo = ? ORDER BY dorsal";
		List<Inscripcion> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			ps.setLong(2, idTorneo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
		return lista;
	}

	/** Historial de inscripciones de un jugador. */
	public List<Inscripcion> listarPorJugador(Long idJugador) {
		String sql = SELECT + " WHERE id_jugador = ? ORDER BY fecha_inicio";
		List<Inscripcion> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idJugador);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
		return lista;
	}

	/** Devuelve null si no existe. */
	public Inscripcion buscar(Long idEquipo, Long idTorneo, Long idJugador) {
		String sql = SELECT + " WHERE id_equipo = ? AND id_torneo = ? AND id_jugador = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			ps.setLong(2, idTorneo);
			ps.setLong(3, idJugador);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
		return null;
	}

	public int insertar(Inscripcion i) {
		String sql = "INSERT INTO inscripcion (id_equipo, id_torneo, id_jugador, dorsal, fecha_inicio, fecha_fin) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, i.getIdEquipo());
			ps.setLong(2, i.getIdTorneo());
			ps.setLong(3, i.getIdJugador());
			ps.setInt(4, i.getDorsal());
			ps.setDate(5, Date.valueOf(i.getFechaInicio()));
			if (i.getFechaFin() == null) {
				ps.setNull(6, Types.DATE);
			} else {
				ps.setDate(6, Date.valueOf(i.getFechaFin()));
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Inscripcion i) {
		String sql = "UPDATE inscripcion SET dorsal = ?, fecha_inicio = ?, fecha_fin = ? "
				+ "WHERE id_equipo = ? AND id_torneo = ? AND id_jugador = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, i.getDorsal());
			ps.setDate(2, Date.valueOf(i.getFechaInicio()));
			if (i.getFechaFin() == null) {
				ps.setNull(3, Types.DATE);
			} else {
				ps.setDate(3, Date.valueOf(i.getFechaFin()));
			}
			ps.setLong(4, i.getIdEquipo());
			ps.setLong(5, i.getIdTorneo());
			ps.setLong(6, i.getIdJugador());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long idEquipo, Long idTorneo, Long idJugador) {
		String sql = "DELETE FROM inscripcion WHERE id_equipo = ? AND id_torneo = ? AND id_jugador = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			ps.setLong(2, idTorneo);
			ps.setLong(3, idJugador);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}