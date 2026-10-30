package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Participacion;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class ParticipacionRepository {

	private static final String SELECT = "SELECT id_participacion, id_jugador, id_partido, titular, minutos_jugados "
			+ "FROM participacion";

	private Participacion mapear(ResultSet rs) throws SQLException {
		Participacion p = new Participacion();
		p.setIdParticipacion(rs.getLong("id_participacion"));
		p.setIdJugador(rs.getLong("id_jugador"));
		p.setIdPartido(rs.getLong("id_partido"));
		p.setTitular(rs.getString("titular"));
		p.setMinutosJugados(rs.getInt("minutos_jugados"));
		return p;
	}

	/** Devuelve null si no existe. */
	public Participacion buscarPorId(Long id) {
		String sql = SELECT + " WHERE id_participacion = ?";
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

	/** Los jugadores que participaron en un partido (titulares primero). */
	public List<Participacion> listarPorPartido(Long idPartido) {
		String sql = SELECT + " WHERE id_partido = ? ORDER BY titular DESC, id_participacion";
		List<Participacion> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idPartido);
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

	/** Los partidos en los que ha participado un jugador. */
	public List<Participacion> listarPorJugador(Long idJugador) {
		String sql = SELECT + " WHERE id_jugador = ? ORDER BY id_partido";
		List<Participacion> lista = new ArrayList<>();
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

	/** Devuelve null si ese jugador no participó en ese partido. */
	public Participacion buscarPorJugadorYPartido(Long idJugador, Long idPartido) {
		String sql = SELECT + " WHERE id_jugador = ? AND id_partido = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idJugador);
			ps.setLong(2, idPartido);
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

	public long siguienteId() {
		String sql = "SELECT NVL(MAX(id_participacion), 0) + 1 FROM participacion";
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(Participacion p) {
		String sql = "INSERT INTO participacion (id_participacion, id_jugador, id_partido, titular, minutos_jugados) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, p.getIdParticipacion());
			ps.setLong(2, p.getIdJugador());
			ps.setLong(3, p.getIdPartido());
			ps.setString(4, p.getTitular());
			ps.setInt(5, p.getMinutosJugados());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Participacion p) {
		String sql = "UPDATE participacion SET titular = ?, minutos_jugados = ? WHERE id_participacion = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, p.getTitular());
			ps.setInt(2, p.getMinutosJugados());
			ps.setLong(3, p.getIdParticipacion());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	/** Borra también sus goles y tarjetas (ON DELETE CASCADE). */
	public int eliminar(Long id) {
		String sql = "DELETE FROM participacion WHERE id_participacion = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}