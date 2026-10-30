package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.TipoTarjeta;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class TipoTarjetaRepository {

	private TipoTarjeta mapear(ResultSet rs) throws SQLException {
		TipoTarjeta t = new TipoTarjeta();
		t.setIdTipo(rs.getLong("id_tipo"));
		t.setColorTarjeta(rs.getString("color_tarjeta"));
		return t;
	}

	public List<TipoTarjeta> listar() {
		String sql = "SELECT id_tipo, color_tarjeta FROM tipo_tarjeta ORDER BY id_tipo";
		List<TipoTarjeta> lista = new ArrayList<>();
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
	public TipoTarjeta buscarPorId(Long id) {
		String sql = "SELECT id_tipo, color_tarjeta FROM tipo_tarjeta WHERE id_tipo = ?";
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
		String sql = "SELECT NVL(MAX(id_tipo), 0) + 1 FROM tipo_tarjeta";
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(TipoTarjeta t) {
		String sql = "INSERT INTO tipo_tarjeta (id_tipo, color_tarjeta) VALUES (?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, t.getIdTipo());
			ps.setString(2, t.getColorTarjeta());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(TipoTarjeta t) {
		String sql = "UPDATE tipo_tarjeta SET color_tarjeta = ? WHERE id_tipo = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, t.getColorTarjeta());
			ps.setLong(2, t.getIdTipo());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long id) {
		String sql = "DELETE FROM tipo_tarjeta WHERE id_tipo = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}