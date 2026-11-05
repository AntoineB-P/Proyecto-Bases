package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Gol;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class GolRepository {

	private Gol mapear(ResultSet rs) throws SQLException {
		Gol g = new Gol();
		g.setIdParticipacion(rs.getLong("id_participacion"));
		g.setIdGol(rs.getLong("id_gol"));
		g.setTipoGol(rs.getString("tipo_gol"));
		g.setMinutoGol(rs.getInt("minuto_gol"));
		return g;
	}

	/** Goles de un jugador en un partido (por su participación). */
	public List<Gol> listarPorParticipacion(Long idParticipacion) {
		String sql = "SELECT id_participacion, id_gol, tipo_gol, minuto_gol FROM gol "
				+ "WHERE id_participacion = ? ORDER BY minuto_gol";
		List<Gol> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idParticipacion);
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

	/** Todos los goles de un partido. */
	public List<Gol> listarPorPartido(Long idPartido) {
		String sql = "SELECT g.id_participacion, g.id_gol, g.tipo_gol, g.minuto_gol "
				+ "FROM gol g JOIN participacion pa ON pa.id_participacion = g.id_participacion "
				+ "WHERE pa.id_partido = ? ORDER BY g.minuto_gol";
		List<Gol> lista = new ArrayList<>();
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

	/** Devuelve null si no existe. */
	public Gol buscar(Long idParticipacion, Long idGol) {
		String sql = "SELECT id_participacion, id_gol, tipo_gol, minuto_gol FROM gol "
				+ "WHERE id_participacion = ? AND id_gol = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idParticipacion);
			ps.setLong(2, idGol);
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

	public long siguienteId(Long idParticipacion) {
		String sql = "SELECT NVL(MAX(id_gol), 0) + 1 FROM gol WHERE id_participacion = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idParticipacion);
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(Gol g) {
		String sql = "INSERT INTO gol (id_participacion, id_gol, tipo_gol, minuto_gol) VALUES (?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, g.getIdParticipacion());
			ps.setLong(2, g.getIdGol());
			ps.setString(3, g.getTipoGol());
			ps.setInt(4, g.getMinutoGol());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Gol g) {
		String sql = "UPDATE gol SET tipo_gol = ?, minuto_gol = ? WHERE id_participacion = ? AND id_gol = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, g.getTipoGol());
			ps.setInt(2, g.getMinutoGol());
			ps.setLong(3, g.getIdParticipacion());
			ps.setLong(4, g.getIdGol());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long idParticipacion, Long idGol) {
		String sql = "DELETE FROM gol WHERE id_participacion = ? AND id_gol = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idParticipacion);
			ps.setLong(2, idGol);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}