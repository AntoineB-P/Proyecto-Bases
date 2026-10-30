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
import co.edu.unbosque.proyectobases.entity.Equipo;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class EquipoRepository {

	private Equipo mapear(ResultSet rs) throws SQLException {
		Equipo e = new Equipo();
		e.setIdEquipo(rs.getLong("id_equipo"));
		e.setNombreEquipo(rs.getString("nombre_equipo"));
		e.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
		e.setCiudadEquipo(rs.getString("ciudad_equipo"));
		e.setIdTecnico(rs.getLong("id_tecnico"));
		e.setIdSede(rs.getLong("id_sede"));
		return e;
	}

	public List<Equipo> listar() {
		String sql = "SELECT id_equipo, nombre_equipo, fecha_fundacion, ciudad_equipo, id_tecnico, id_sede "
				+ "FROM equipo ORDER BY id_equipo";
		List<Equipo> lista = new ArrayList<>();
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

	/** Equipos inscritos en un torneo (tabla equipo_torneo). */
	public List<Equipo> listarPorTorneo(Long idTorneo) {
		String sql = "SELECT e.id_equipo, e.nombre_equipo, e.fecha_fundacion, e.ciudad_equipo, e.id_tecnico, e.id_sede "
				+ "FROM equipo e JOIN equipo_torneo et ON et.id_equipo = e.id_equipo "
				+ "WHERE et.id_torneo = ? ORDER BY e.nombre_equipo";
		List<Equipo> lista = new ArrayList<>();
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

	/** Devuelve null si no existe. */
	public Equipo buscarPorId(Long id) {
		String sql = "SELECT id_equipo, nombre_equipo, fecha_fundacion, ciudad_equipo, id_tecnico, id_sede "
				+ "FROM equipo WHERE id_equipo = ?";
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
		String sql = "SELECT NVL(MAX(id_equipo), 0) + 1 FROM equipo";
		try (Connection con = ConexionBD.obtener();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	// ---------------- colores (tabla equipo_color) ----------------

	public List<String> listarColores(Long idEquipo) {
		String sql = "SELECT color FROM equipo_color WHERE id_equipo = ? ORDER BY color";
		List<String> colores = new ArrayList<>();
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEquipo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					colores.add(rs.getString("color"));
				}
			}
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
		return colores;
	}

	// Usa la conexión que ya está abierta para que quede dentro de la transacción
	private void insertarColores(Connection con, Long idEquipo, List<String> colores) throws SQLException {
		String sql = "INSERT INTO equipo_color (id_equipo, color) VALUES (?, ?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			for (String color : colores) {
				ps.setLong(1, idEquipo);
				ps.setString(2, color);
				ps.executeUpdate();
			}
		}
	}

	// ---------------- escritura (transacción manual) ----------------

	/** Inserta el equipo y sus colores: o se guardan los dos, o ninguno. */
	public void insertar(Equipo e, List<String> colores) {
		String sql = "INSERT INTO equipo (id_equipo, nombre_equipo, fecha_fundacion, ciudad_equipo, id_tecnico, id_sede) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		Connection con = null;
		try {
			con = ConexionBD.obtener();
			con.setAutoCommit(false);
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setLong(1, e.getIdEquipo());
				ps.setString(2, e.getNombreEquipo());
				ps.setDate(3, Date.valueOf(e.getFechaFundacion()));
				ps.setString(4, e.getCiudadEquipo());
				ps.setLong(5, e.getIdTecnico());
				ps.setLong(6, e.getIdSede());
				ps.executeUpdate();
			}
			insertarColores(con, e.getIdEquipo(), colores);
			con.commit();
		} catch (SQLException ex) {
			deshacer(con);
			throw ErrorBD.traducir(ex);
		} finally {
			cerrar(con);
		}
	}

	/** Actualiza el equipo y reemplaza todos sus colores. */
	public void actualizar(Equipo e, List<String> colores) {
		String sqlEquipo = "UPDATE equipo SET nombre_equipo = ?, fecha_fundacion = ?, ciudad_equipo = ?, "
				+ "id_tecnico = ?, id_sede = ? WHERE id_equipo = ?";
		String sqlBorrarColores = "DELETE FROM equipo_color WHERE id_equipo = ?";
		Connection con = null;
		try {
			con = ConexionBD.obtener();
			con.setAutoCommit(false);
			try (PreparedStatement ps = con.prepareStatement(sqlEquipo)) {
				ps.setString(1, e.getNombreEquipo());
				ps.setDate(2, Date.valueOf(e.getFechaFundacion()));
				ps.setString(3, e.getCiudadEquipo());
				ps.setLong(4, e.getIdTecnico());
				ps.setLong(5, e.getIdSede());
				ps.setLong(6, e.getIdEquipo());
				ps.executeUpdate();
			}
			try (PreparedStatement ps = con.prepareStatement(sqlBorrarColores)) {
				ps.setLong(1, e.getIdEquipo());
				ps.executeUpdate();
			}
			insertarColores(con, e.getIdEquipo(), colores);
			con.commit();
		} catch (SQLException ex) {
			deshacer(con);
			throw ErrorBD.traducir(ex);
		} finally {
			cerrar(con);
		}
	}

	/** Los colores se borran solos (ON DELETE CASCADE en equipo_color). */
	public int eliminar(Long id) {
		String sql = "DELETE FROM equipo WHERE id_equipo = ?";
		try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw ErrorBD.traducir(e);
		}
	}

	private void deshacer(Connection con) {
		try {
			if (con != null) {
				con.rollback();
			}
		} catch (SQLException ignorada) {
			// si el rollback falla, la excepción original es la importante
		}
	}

	private void cerrar(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException ignorada) {
		}
	}
}