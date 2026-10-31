package co.edu.unbosque.proyectobases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.proyectobases.configuration.ConexionBD;
import co.edu.unbosque.proyectobases.entity.Rol;
import co.edu.unbosque.proyectobases.entity.Usuario;
import co.edu.unbosque.proyectobases.exception.ErrorBD;

@Repository
public class UsuarioRepository {

    public Usuario buscarPorUsuario(String usuario) {
        String sql = "SELECT u.id_usuario, u.usuario, u.contrasenia, u.correo, u.id_equipo_favorito, r.nombre_rol "
                + "FROM usuario u JOIN rol r ON r.id_rol = u.id_rol WHERE u.usuario = ?";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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
                    return u;
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
        String sql = "INSERT INTO usuario (id_usuario, usuario, contrasenia, correo, id_rol) "
                + "VALUES (?, ?, ?, ?, (SELECT id_rol FROM rol WHERE nombre_rol = ?))";
        try (Connection con = ConexionBD.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, u.getIdUsuario());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getContrasenia());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getRol().name());
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