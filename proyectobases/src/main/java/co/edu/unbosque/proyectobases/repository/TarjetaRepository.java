package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Tarjeta;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class TarjetaRepository {

	private Tarjeta mapear(ResultSet rs) throws SQLException {
		Tarjeta t = new Tarjeta();
		t.setIdParticipacion(rs.getLong("id_participacion"));
		t.setIdTarjeta(rs.getLong("id_tarjeta"));
		t.setIdTipo(rs.getLong("id_tipo"));
		t.setMotivo(rs.getString("motivo"));
		t.setMinutoTarjeta(rs.getInt("minuto_tarjeta"));
		return t;
	}

	/** Tarjetas de un jugador en un partido (por su participación). */
	public List<Tarjeta> listarPorParticipacion(Long idParticipacion) {
		String sql = "SELECT id_participacion, id_tarjeta, id_tipo, motivo, minuto_tarjeta FROM tarjeta "
				+ "WHERE id_participacion = ? ORDER BY minuto_tarjeta";
		List<Tarjeta> lista = new ArrayList<>();
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

	/** Todas las tarjetas de un partido. */
	public List<Tarjeta> listarPorPartido(Long idPartido) {
		String sql = "SELECT t.id_participacion, t.id_tarjeta, t.id_tipo, t.motivo, t.minuto_tarjeta "
				+ "FROM tarjeta t JOIN participacion pa ON pa.id_participacion = t.id_participacion "
				+ "WHERE pa.id_partido = ? ORDER BY t.minuto_tarjeta";
		List<Tarjeta> lista = new ArrayList<>();
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
	public Tarjeta buscar(Long idParticipacion, Long idTarjeta) {
		String sql = "SELECT id_participacion, id_tarjeta, id_tipo, motivo, minuto_tarjeta FROM tarjeta "
				+ "WHERE id_participacion = ? AND id_tarjeta = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idParticipacion);
			ps.setLong(2, idTarjeta);
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
		String sql = "SELECT NVL(MAX(id_tarjeta), 0) + 1 FROM tarjeta WHERE id_participacion = ?";
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

	public int insertar(Tarjeta t) {
		String sql = "INSERT INTO tarjeta (id_participacion, id_tarjeta, id_tipo, motivo, minuto_tarjeta) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, t.getIdParticipacion());
			ps.setLong(2, t.getIdTarjeta());
			ps.setLong(3, t.getIdTipo());
			ps.setString(4, t.getMotivo());
			ps.setInt(5, t.getMinutoTarjeta());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Tarjeta t) {
		String sql = "UPDATE tarjeta SET id_tipo = ?, motivo = ?, minuto_tarjeta = ? "
				+ "WHERE id_participacion = ? AND id_tarjeta = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, t.getIdTipo());
			ps.setString(2, t.getMotivo());
			ps.setInt(3, t.getMinutoTarjeta());
			ps.setLong(4, t.getIdParticipacion());
			ps.setLong(5, t.getIdTarjeta());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long idParticipacion, Long idTarjeta) {
		String sql = "DELETE FROM tarjeta WHERE id_participacion = ? AND id_tarjeta = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idParticipacion);
			ps.setLong(2, idTarjeta);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}