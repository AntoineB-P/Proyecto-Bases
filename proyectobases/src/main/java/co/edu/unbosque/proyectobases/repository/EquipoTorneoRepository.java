package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.EquipoTorneo;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class EquipoTorneoRepository {

	private EquipoTorneo mapear(ResultSet rs) throws SQLException {
		EquipoTorneo et = new EquipoTorneo();
		et.setIdEquipo(rs.getLong("id_equipo"));
		et.setIdTorneo(rs.getLong("id_torneo"));
		return et;
	}

	public List<EquipoTorneo> listarPorTorneo(Long idTorneo) {
		String sql = "SELECT id_equipo, id_torneo FROM equipo_torneo WHERE id_torneo = ? ORDER BY id_equipo";
		List<EquipoTorneo> lista = new ArrayList<>();
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

	public List<EquipoTorneo> listarPorEquipo(Long idEquipo) {
		String sql = "SELECT id_equipo, id_torneo FROM equipo_torneo WHERE id_equipo = ? ORDER BY id_torneo";
		List<EquipoTorneo> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
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

	/** Devuelve null si ese equipo no está en ese torneo. */
	public EquipoTorneo buscar(Long idEquipo, Long idTorneo) {
		String sql = "SELECT id_equipo, id_torneo FROM equipo_torneo WHERE id_equipo = ? AND id_torneo = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			ps.setLong(2, idTorneo);
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

	public int insertar(EquipoTorneo et) {
		String sql = "INSERT INTO equipo_torneo (id_equipo, id_torneo) VALUES (?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, et.getIdEquipo());
			ps.setLong(2, et.getIdTorneo());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long idEquipo, Long idTorneo) {
		String sql = "DELETE FROM equipo_torneo WHERE id_equipo = ? AND id_torneo = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			ps.setLong(2, idTorneo);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}