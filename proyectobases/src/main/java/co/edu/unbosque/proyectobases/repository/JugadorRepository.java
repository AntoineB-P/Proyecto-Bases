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
import co.edu.unbosque.proyectobases.entity.Jugador;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class JugadorRepository {

	private Jugador mapear(ResultSet rs) throws SQLException {
		Jugador j = new Jugador();
		j.setIdJugador(rs.getLong("id_jugador"));
		j.setNombres(rs.getString("nombres"));
		j.setApellidos(rs.getString("apellidos"));
		j.setNacionalidad(rs.getString("nacionalidad"));
		j.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
		j.setPosicion(rs.getString("posicion"));
		return j;
	}

	public List<Jugador> listar() {
		String sql = "SELECT id_jugador, nombres, apellidos, nacionalidad, fecha_nacimiento, posicion "
				+ "FROM jugador ORDER BY id_jugador";
		List<Jugador> lista = new ArrayList<>();
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
	public Jugador buscarPorId(Long id) {
		String sql = "SELECT id_jugador, nombres, apellidos, nacionalidad, fecha_nacimiento, posicion "
				+ "FROM jugador WHERE id_jugador = ?";
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

	/** Plantilla de un equipo en un torneo (se obtiene por la tabla inscripcion). */
	public List<Jugador> listarPorEquipoYTorneo(Long idEquipo, Long idTorneo) {
		String sql = "SELECT j.id_jugador, j.nombres, j.apellidos, j.nacionalidad, j.fecha_nacimiento, j.posicion "
				+ "FROM jugador j JOIN inscripcion i ON i.id_jugador = j.id_jugador "
				+ "WHERE i.id_equipo = ? AND i.id_torneo = ? ORDER BY j.apellidos, j.nombres";
		List<Jugador> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			ps.setLong(2, idTorneo);
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

	public List<Jugador> listarPorPosicion(String posicion) {
		String sql = "SELECT id_jugador, nombres, apellidos, nacionalidad, fecha_nacimiento, posicion "
				+ "FROM jugador WHERE posicion = ? ORDER BY apellidos, nombres";
		List<Jugador> lista = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, posicion);
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

	public long siguienteId() {
		String sql = "SELECT NVL(MAX(id_jugador), 0) + 1 FROM jugador";
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int insertar(Jugador j) {
		String sql = "INSERT INTO jugador (id_jugador, nombres, apellidos, nacionalidad, fecha_nacimiento, posicion) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, j.getIdJugador());
			ps.setString(2, j.getNombres());
			ps.setString(3, j.getApellidos());
			ps.setString(4, j.getNacionalidad());
			ps.setDate(5, Date.valueOf(j.getFechaNacimiento()));
			ps.setString(6, j.getPosicion());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int actualizar(Jugador j) {
		String sql = "UPDATE jugador SET nombres = ?, apellidos = ?, nacionalidad = ?, fecha_nacimiento = ?, "
				+ "posicion = ? WHERE id_jugador = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, j.getNombres());
			ps.setString(2, j.getApellidos());
			ps.setString(3, j.getNacionalidad());
			ps.setDate(4, Date.valueOf(j.getFechaNacimiento()));
			ps.setString(5, j.getPosicion());
			ps.setLong(6, j.getIdJugador());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	public int eliminar(Long id) {
		String sql = "DELETE FROM jugador WHERE id_jugador = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}
}