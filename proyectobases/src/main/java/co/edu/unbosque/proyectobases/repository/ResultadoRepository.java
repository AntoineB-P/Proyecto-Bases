package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Resultado;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class ResultadoRepository {

	private Resultado mapear(ResultSet rs) throws SQLException {
		Resultado r = new Resultado();
		r.setIdPartido(rs.getLong("id_partido"));
		r.setIdResultado(rs.getLong("id_resultado"));
		r.setGolesLocal(rs.getInt("goles_local"));
		r.setGolesVisitante(rs.getInt("goles_visitante"));
		return r;
	}

	/** Devuelve null si el partido todavía no se ha jugado. */
	public Resultado buscarPorPartido(Long idPartido) {
		String sql = "SELECT id_partido, id_resultado, goles_local, goles_visitante "
				+ "FROM resultado WHERE id_partido = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idPartido);
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

	/** Resultados de todos los partidos jugados de un torneo. */
	public List<Resultado> listarPorTorneo(Long idTorneo) {
		String sql = "SELECT r.id_partido, r.id_resultado, r.goles_local, r.goles_visitante "
				+ "FROM resultado r JOIN partido p ON p.id_partido = r.id_partido "
				+ "WHERE p.id_torneo = ? ORDER BY p.jornada, p.fecha";
		List<Resultado> lista = new ArrayList<>();
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

	public long siguienteId(Long idPartido) {
		String sql = "SELECT NVL(MAX(id_resultado), 0) + 1 FROM resultado WHERE id_partido = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idPartido);
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(Resultado r) {
		String sql = "INSERT INTO resultado (id_partido, id_resultado, goles_local, goles_visitante) "
				+ "VALUES (?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, r.getIdPartido());
			ps.setLong(2, r.getIdResultado());
			ps.setInt(3, r.getGolesLocal());
			ps.setInt(4, r.getGolesVisitante());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Resultado r) {
		String sql = "UPDATE resultado SET goles_local = ?, goles_visitante = ? WHERE id_partido = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, r.getGolesLocal());
			ps.setInt(2, r.getGolesVisitante());
			ps.setLong(3, r.getIdPartido());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminarPorPartido(Long idPartido) {
		String sql = "DELETE FROM resultado WHERE id_partido = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idPartido);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}