package co.edu.unbosque.proyectobases.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.UsuarioDTO;
import co.edu.unbosque.proyectobases.dto.EquipoDTO;
import co.edu.unbosque.proyectobases.entity.Equipo;
import co.edu.unbosque.proyectobases.entity.Rol;
import co.edu.unbosque.proyectobases.entity.Usuario;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.EquipoRepository;
import co.edu.unbosque.proyectobases.repository.UsuarioRepository;
import co.edu.unbosque.proyectobases.security.JwtUtil;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository uRep;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private EquipoService equipoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrar(UsuarioDTO dto) {
        if (dto.getUsuario() == null || dto.getUsuario().trim().length() < 5) {
            throw new DatoInvalidoException("El usuario debe tener mínimo 5 caracteres");
        }
        if (dto.getContrasenia() == null || dto.getContrasenia().length() < 12) {
            throw new DatoInvalidoException("La contraseña debe tener mínimo 12 caracteres");
        }
        if (dto.getCorreo() == null || !dto.getCorreo().matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new DatoInvalidoException("El correo no es válido");
        }
        if (uRep.buscarPorUsuario(dto.getUsuario()) != null) {
            throw new ConflictoException("El usuario ya existe");
        }
        if (uRep.existeCorreo(dto.getCorreo())) {
            throw new ConflictoException("El correo ya está registrado");
        }

        Usuario nuevo = new Usuario(uRep.siguienteId(), dto.getUsuario(),
                passwordEncoder.encode(dto.getContrasenia()), Rol.USUARIO, dto.getCorreo());
        uRep.insertar(nuevo);
    }

    public String login(String usuario, String contrasenia) {
        if (usuario == null || contrasenia == null) {
            return null;
        }
        Usuario encontrado = uRep.buscarPorUsuario(usuario);
        if (encontrado == null) {
            return null;
        }
        if (!passwordEncoder.matches(contrasenia, encontrado.getContrasenia())) {
            return null;
        }
        return jwtUtil.generateToken(encontrado);
    }

    public void marcarFavorito(String username, Long idEquipo) {
        if (equipoRepository.buscarPorId(idEquipo) == null) {
            throw new RecursoNoEncontradoException("El equipo no existe");
        }
        Usuario u = uRep.buscarPorUsuario(username);
        uRep.actualizarFavorito(u.getIdUsuario(), idEquipo);
    }

    public void quitarFavorito(String username) {
        Usuario u = uRep.buscarPorUsuario(username);
        uRep.actualizarFavorito(u.getIdUsuario(), null);
    }

    public EquipoDTO obtenerFavorito(String username) {
        Usuario u = uRep.buscarPorUsuario(username);
        if (u.getIdEquipoFavorito() == null) {
            throw new RecursoNoEncontradoException("No has marcado un club favorito");
        }
        return equipoService.buscarPorId(u.getIdEquipoFavorito());
    }
}