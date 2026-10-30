package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Arbitraje;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class ArbitrajeRepository {

	private Arbitraje mapear(ResultSet rs) throws SQLException {
		Arbitraje a = new Arbitraje();
		a.setIdPartido(rs.getLong("id_partido"));
		a.setCedula(rs.getString("cedula"));
		a.setRol(rs.getString("rol"));
		return a;
	}

	/** Los árbitros que dirigen un partido. */
	public List<Arbitraje> listarPorPartido(Long idPartido) {
		String sql = "SELECT id_partido, cedula, rol FROM arbitraje WHERE id_partido = ? ORDER BY rol";
		List<Arbitraje> lista = new ArrayList<>();
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

	/** Los partidos que ha dirigido un árbitro. */
	public List<Arbitraje> listarPorArbitro(String cedula) {
		String sql = "SELECT id_partido, cedula, rol FROM arbitraje WHERE cedula = ? ORDER BY id_partido";
		List<Arbitraje> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, cedula);
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
	public Arbitraje buscar(Long idPartido, String cedula) {
		String sql = "SELECT id_partido, cedula, rol FROM arbitraje WHERE id_partido = ? AND cedula = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idPartido);
			ps.setString(2, cedula);
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

	public int insertar(Arbitraje a) {
		String sql = "INSERT INTO arbitraje (id_partido, cedula, rol) VALUES (?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, a.getIdPartido());
			ps.setString(2, a.getCedula());
			ps.setString(3, a.getRol());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Arbitraje a) {
		String sql = "UPDATE arbitraje SET rol = ? WHERE id_partido = ? AND cedula = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, a.getRol());
			ps.setLong(2, a.getIdPartido());
			ps.setString(3, a.getCedula());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long idPartido, String cedula) {
		String sql = "DELETE FROM arbitraje WHERE id_partido = ? AND cedula = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idPartido);
			ps.setString(2, cedula);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}
