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
import co.edu.unbosque.proyectobases.entity.Torneo;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class TorneoRepository {

	private Torneo mapear(ResultSet rs) throws SQLException {
		Torneo t = new Torneo();
		t.setIdTorneo(rs.getLong("id_torneo"));
		t.setNombreTorneo(rs.getString("nombre_torneo"));
		t.setAnio(rs.getInt("anio"));
		t.setSemestre(rs.getInt("semestre"));
		t.setFechaInicioTorneo(rs.getDate("fecha_inicio_torneo").toLocalDate());
		t.setFechaFinTorneo(rs.getDate("fecha_fin_torneo").toLocalDate());
		return t;
	}

	public List<Torneo> listar() {
		String sql = "SELECT id_torneo, nombre_torneo, anio, semestre, fecha_inicio_torneo, fecha_fin_torneo "
				+ "FROM torneo ORDER BY anio DESC, semestre DESC";
		List<Torneo> lista = new ArrayList<>();
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
	public Torneo buscarPorId(Long id) {
		String sql = "SELECT id_torneo, nombre_torneo, anio, semestre, fecha_inicio_torneo, fecha_fin_torneo "
				+ "FROM torneo WHERE id_torneo = ?";
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
		String sql = "SELECT NVL(MAX(id_torneo), 0) + 1 FROM torneo";
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(Torneo t) {
		String sql = "INSERT INTO torneo (id_torneo, nombre_torneo, anio, semestre, fecha_inicio_torneo, fecha_fin_torneo) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, t.getIdTorneo());
			ps.setString(2, t.getNombreTorneo());
			ps.setInt(3, t.getAnio());
			ps.setInt(4, t.getSemestre());
			ps.setDate(5, Date.valueOf(t.getFechaInicioTorneo()));
			ps.setDate(6, Date.valueOf(t.getFechaFinTorneo()));
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Torneo t) {
		String sql = "UPDATE torneo SET nombre_torneo = ?, anio = ?, semestre = ?, fecha_inicio_torneo = ?, "
				+ "fecha_fin_torneo = ? WHERE id_torneo = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, t.getNombreTorneo());
			ps.setInt(2, t.getAnio());
			ps.setInt(3, t.getSemestre());
			ps.setDate(4, Date.valueOf(t.getFechaInicioTorneo()));
			ps.setDate(5, Date.valueOf(t.getFechaFinTorneo()));
			ps.setLong(6, t.getIdTorneo());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long id) {
		String sql = "DELETE FROM torneo WHERE id_torneo = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}