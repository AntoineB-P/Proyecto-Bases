package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Rol;
import co.edu.unbosque.proyectobases.entity.Usuario;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class UsuarioRepository {

    private static final String SELECT = "SELECT u.id_usuario, u.usuario, u.contrasenia, u.correo, "
            + "u.id_equipo_favorito, u.verificado, u.codigo_verificacion, u.expira_verificacion, r.nombre_rol "
            + "FROM usuario u JOIN rol r ON r.id_rol = u.id_rol ";

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getLong("id_usuario"));
        u.setUsuario(rs.getString("usuario"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setRol(Rol.valueOf(rs.getString("nombre_rol")));
        u.setCorreo(rs.getString("correo"));
        long favorito = rs.getLong("id_equipo_favorito");
        if (!rs.wasNull()) {
            u.setIdEquipoFavorito(favorito);
        }
        u.setVerificado(rs.getInt("verificado") == 1);
        u.setCodigoVerificacion(rs.getString("codigo_verificacion"));
        Timestamp expira = rs.getTimestamp("expira_verificacion");
        u.setExpiraVerificacion(expira == null ? null : expira.toLocalDateTime());
        return u;
    }

    /** Devuelve null si no existe. */
    public Usuario buscarPorUsuario(String usuario) {
        String sql = SELECT + "WHERE u.usuario = ?";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
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

    /** Devuelve null si no existe. */
    public Usuario buscarPorCorreo(String correo) {
        String sql = SELECT + "WHERE u.correo = ?";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
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

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE correo = ?";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong(1) > 0;
            }
        } catch (SQLException e) {
            throw ErrorBD.traducir(e);
        }
    }

    public long siguienteId() {
        String sql = "SELECT NVL(MAX(id_usuario), 0) + 1 FROM usuario";
        try (Connection con = ConexionBD.obtener();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw ErrorBD.traducir(e);
        }
    }

    public void insertar(Usuario u) {
        String sql = "INSERT INTO usuario (id_usuario, usuario, contrasenia, correo, id_rol, "
                + "verificado, codigo_verificacion, expira_verificacion) "
                + "VALUES (?, ?, ?, ?, (SELECT id_rol FROM rol WHERE nombre_rol = ?), ?, ?, ?)";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, u.getIdUsuario());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getContrasenia());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getRol().name());
            ps.setInt(6, u.isVerificado() ? 1 : 0);
            ps.setString(7, u.getCodigoVerificacion());
            if (u.getExpiraVerificacion() == null) {
                ps.setNull(8, Types.TIMESTAMP);
            } else {
                ps.setTimestamp(8, Timestamp.valueOf(u.getExpiraVerificacion()));
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw ErrorBD.traducir(e);
        }
    }

    /** Guarda un código nuevo y su vencimiento (para reenviar). */
    public void guardarCodigo(Long idUsuario, String codigo, LocalDateTime expira) {
        String sql = "UPDATE usuario SET codigo_verificacion = ?, expira_verificacion = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setTimestamp(2, Timestamp.valueOf(expira));
            ps.setLong(3, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw ErrorBD.traducir(e);
        }
    }

    public void marcarVerificado(Long idUsuario) {
        String sql = "UPDATE usuario SET verificado = 1, codigo_verificacion = NULL, "
                + "expira_verificacion = NULL WHERE id_usuario = ?";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw ErrorBD.traducir(e);
        }
    }

    public void actualizarFavorito(Long idUsuario, Long idEquipo) {
        String sql = "UPDATE usuario SET id_equipo_favorito = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (idEquipo == null) {
                ps.setNull(1, Types.NUMERIC);
            } else {
                ps.setLong(1, idEquipo);
            }
            ps.setLong(2, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw ErrorBD.traducir(e);
        }
    }
}