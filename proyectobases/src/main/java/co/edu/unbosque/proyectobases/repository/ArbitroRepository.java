package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Arbitro;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class ArbitroRepository {

	private Arbitro mapear(ResultSet rs) throws SQLException {
		Arbitro a = new Arbitro();
		a.setCedula(rs.getString("cedula"));
		a.setNombres(rs.getString("nombres"));
		a.setApellidos(rs.getString("apellidos"));
		a.setCiudadArbitro(rs.getString("ciudad_arbitro"));
		a.setCategoria(rs.getString("categoria"));
		return a;
	}

	public List<Arbitro> listar() {
		String sql = "SELECT cedula, nombres, apellidos, ciudad_arbitro, categoria "
				+ "FROM arbitro ORDER BY apellidos, nombres";
		List<Arbitro> lista = new ArrayList<>();
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
	public Arbitro buscarPorCedula(String cedula) {
		String sql = "SELECT cedula, nombres, apellidos, ciudad_arbitro, categoria "
				+ "FROM arbitro WHERE cedula = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, cedula);
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

	public int insertar(Arbitro a) {
		String sql = "INSERT INTO arbitro (cedula, nombres, apellidos, ciudad_arbitro, categoria) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, a.getCedula());
			ps.setString(2, a.getNombres());
			ps.setString(3, a.getApellidos());
			ps.setString(4, a.getCiudadArbitro());
			ps.setString(5, a.getCategoria());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Arbitro a) {
		String sql = "UPDATE arbitro SET nombres = ?, apellidos = ?, ciudad_arbitro = ?, categoria = ? "
				+ "WHERE cedula = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, a.getNombres());
			ps.setString(2, a.getApellidos());
			ps.setString(3, a.getCiudadArbitro());
			ps.setString(4, a.getCategoria());
			ps.setString(5, a.getCedula());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(String cedula) {
		String sql = "DELETE FROM arbitro WHERE cedula = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, cedula);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}
