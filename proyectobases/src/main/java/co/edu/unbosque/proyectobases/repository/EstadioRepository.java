package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Estadio;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class EstadioRepository {

	private Estadio mapear(ResultSet rs) throws SQLException {
		Estadio e = new Estadio();
		e.setIdEstadio(rs.getLong("id_estadio"));
		e.setNombreEstadio(rs.getString("nombre_estadio"));
		e.setCapacidad(rs.getInt("capacidad"));
		e.setDireccion(rs.getString("direccion"));
		e.setCiudadEstadio(rs.getString("ciudad_estadio"));
		e.setBarrio(rs.getString("barrio"));
		return e;
	}

	public List<Estadio> listar() {
		String sql = "SELECT id_estadio, nombre_estadio, capacidad, direccion, ciudad_estadio, barrio "
				+ "FROM estadio ORDER BY id_estadio";
		List<Estadio> lista = new ArrayList<>();
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
	public Estadio buscarPorId(Long id) {
		String sql = "SELECT id_estadio, nombre_estadio, capacidad, direccion, ciudad_estadio, barrio "
				+ "FROM estadio WHERE id_estadio = ?";
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
		String sql = "SELECT NVL(MAX(id_estadio), 0) + 1 FROM estadio";
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(Estadio e) {
		String sql = "INSERT INTO estadio (id_estadio, nombre_estadio, capacidad, direccion, ciudad_estadio, barrio) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, e.getIdEstadio());
			ps.setString(2, e.getNombreEstadio());
			ps.setInt(3, e.getCapacidad());
			ps.setString(4, e.getDireccion());
			ps.setString(5, e.getCiudadEstadio());
			ps.setString(6, e.getBarrio());
			return ps.executeUpdate();
		} catch (SQLException ex) {
			throw ErrorBD.traducir(ex);
		}
	}

	public int actualizar(Estadio e) {
		String sql = "UPDATE estadio SET nombre_estadio = ?, capacidad = ?, direccion = ?, ciudad_estadio = ?, "
				+ "barrio = ? WHERE id_estadio = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, e.getNombreEstadio());
			ps.setInt(2, e.getCapacidad());
			ps.setString(3, e.getDireccion());
			ps.setString(4, e.getCiudadEstadio());
			ps.setString(5, e.getBarrio());
			ps.setLong(6, e.getIdEstadio());
			return ps.executeUpdate();
		} catch (SQLException ex) {
			throw ErrorBD.traducir(ex);
		}
	}

	public int eliminar(Long id) {
		String sql = "DELETE FROM estadio WHERE id_estadio = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}