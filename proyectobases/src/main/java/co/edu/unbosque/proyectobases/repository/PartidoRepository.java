package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Partido;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class PartidoRepository {

	private static final String SELECT = "SELECT id_partido, fecha, hora, jornada, id_torneo, id_estadio, "
			+ "id_local, id_visitante FROM partido";

	private Partido mapear(ResultSet rs) throws SQLException {
		Partido p = new Partido();
		p.setIdPartido(rs.getLong("id_partido"));
		p.setFecha(rs.getDate("fecha").toLocalDate());
		p.setHora(rs.getString("hora"));
		p.setJornada(rs.getInt("jornada"));
		p.setIdTorneo(rs.getLong("id_torneo"));
		p.setIdEstadio(rs.getLong("id_estadio"));
		p.setIdLocal(rs.getLong("id_local"));
		p.setIdVisitante(rs.getLong("id_visitante"));
		return p;
	}

	public List<Partido> listar() {
		String sql = SELECT + " ORDER BY fecha, hora";
		List<Partido> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
		return lista;
	}

	/** Devuelve null si no existe. */
	public Partido buscarPorId(Long id) {
		String sql = SELECT + " WHERE id_partido = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
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

	public List<Partido> listarPorTorneo(Long idTorneo) {
		String sql = SELECT + " WHERE id_torneo = ? ORDER BY jornada, fecha, hora";
		List<Partido> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idTorneo);
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

	public List<Partido> listarPorJornada(Long idTorneo, Integer jornada) {
		String sql = SELECT + " WHERE id_torneo = ? AND jornada = ? ORDER BY fecha, hora";
		List<Partido> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idTorneo);
			ps.setInt(2, jornada);
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

	/** Todos los partidos de un equipo, sea local o visitante. */
	public List<Partido> listarPorEquipo(Long idEquipo) {
		String sql = SELECT + " WHERE id_local = ? OR id_visitante = ? ORDER BY fecha, hora";
		List<Partido> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			ps.setLong(2, idEquipo);
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

	public long siguienteId() {
		String sql = "SELECT NVL(MAX(id_partido), 0) + 1 FROM partido";
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(Partido p) {
		String sql = "INSERT INTO partido (id_partido, fecha, hora, jornada, id_torneo, id_estadio, id_local, id_visitante) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, p.getIdPartido());
			ps.setDate(2, Date.valueOf(p.getFecha()));
			ps.setString(3, p.getHora());
			ps.setInt(4, p.getJornada());
			ps.setLong(5, p.getIdTorneo());
			ps.setLong(6, p.getIdEstadio());
			ps.setLong(7, p.getIdLocal());
			ps.setLong(8, p.getIdVisitante());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Partido p) {
		String sql = "UPDATE partido SET fecha = ?, hora = ?, jornada = ?, id_torneo = ?, id_estadio = ?, "
				+ "id_local = ?, id_visitante = ? WHERE id_partido = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setDate(1, Date.valueOf(p.getFecha()));
			ps.setString(2, p.getHora());
			ps.setInt(3, p.getJornada());
			ps.setLong(4, p.getIdTorneo());
			ps.setLong(5, p.getIdEstadio());
			ps.setLong(6, p.getIdLocal());
			ps.setLong(7, p.getIdVisitante());
			ps.setLong(8, p.getIdPartido());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	/** Borra también su resultado y su arbitraje (ON DELETE CASCADE). */
	public int eliminar(Long id) {
		String sql = "DELETE FROM partido WHERE id_partido = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}