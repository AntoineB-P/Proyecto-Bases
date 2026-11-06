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
import co.edu.unbosque.proyectobases.entity.Tecnico;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class TecnicoRepository {

	private Tecnico mapear(ResultSet rs) throws SQLException {
		Tecnico t = new Tecnico();
		t.setIdTecnico(rs.getLong("id_tecnico"));
		t.setNombres(rs.getString("nombres"));
		t.setApellidos(rs.getString("apellidos"));
		t.setNacionalidad(rs.getString("nacionalidad"));
		t.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
		return t;
	}

	public List<Tecnico> listar() {
		String sql = "SELECT id_tecnico, nombres, apellidos, nacionalidad, fecha_nacimiento "
				+ "FROM tecnico ORDER BY id_tecnico";
		List<Tecnico> lista = new ArrayList<>();
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
	public Tecnico buscarPorId(Long id) {
		String sql = "SELECT id_tecnico, nombres, apellidos, nacionalidad, fecha_nacimiento "
				+ "FROM tecnico WHERE id_tecnico = ?";
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

	public long siguienteId() {
		String sql = "SELECT NVL(MAX(id_tecnico), 0) + 1 FROM tecnico";
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(Tecnico t) {
		String sql = "INSERT INTO tecnico (id_tecnico, nombres, apellidos, nacionalidad, fecha_nacimiento) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, t.getIdTecnico());
			ps.setString(2, t.getNombres());
			ps.setString(3, t.getApellidos());
			ps.setString(4, t.getNacionalidad());
			ps.setDate(5, Date.valueOf(t.getFechaNacimiento()));
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Tecnico t) {
		String sql = "UPDATE tecnico SET nombres = ?, apellidos = ?, nacionalidad = ?, fecha_nacimiento = ? "
				+ "WHERE id_tecnico = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, t.getNombres());
			ps.setString(2, t.getApellidos());
			ps.setString(3, t.getNacionalidad());
			ps.setDate(4, Date.valueOf(t.getFechaNacimiento()));
			ps.setLong(5, t.getIdTecnico());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long id) {
		String sql = "DELETE FROM tecnico WHERE id_tecnico = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}