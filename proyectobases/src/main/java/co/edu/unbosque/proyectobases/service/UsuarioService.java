package co.edu.unbosque.proyectobases.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.EquipoDTO;
import co.edu.unbosque.proyectobases.dto.UsuarioDTO;
import co.edu.unbosque.proyectobases.entity.Rol;
import co.edu.unbosque.proyectobases.entity.Usuario;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.CuentaNoVerificadaException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.EquipoRepository;
import co.edu.unbosque.proyectobases.repository.UsuarioRepository;
import co.edu.unbosque.proyectobases.security.JwtUtil;

@Service
public class UsuarioService {

    private static final int MINUTOS_VALIDEZ_CODIGO = 15;

    @Autowired
    private UsuarioRepository uRep;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** Código de 6 dígitos, por ejemplo 042913. */
    private String generarCodigo() {
        return String.format("%06d", new SecureRandom().nextInt(1_000_000));
    }

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

        String codigo = generarCodigo();
        Usuario nuevo = new Usuario();
        nuevo.setIdUsuario(uRep.siguienteId());
        nuevo.setUsuario(dto.getUsuario());
        nuevo.setContrasenia(passwordEncoder.encode(dto.getContrasenia())); // se guarda el hash BCrypt
        nuevo.setRol(Rol.USUARIO);
        nuevo.setCorreo(dto.getCorreo());
        nuevo.setVerificado(false);
        nuevo.setCodigoVerificacion(codigo);
        nuevo.setExpiraVerificacion(LocalDateTime.now().plusMinutes(MINUTOS_VALIDEZ_CODIGO));
        uRep.insertar(nuevo);

        // Si el envío falla, la cuenta queda creada y puede pedir el código otra vez
        emailService.enviarCodigoVerificacion(nuevo.getCorreo(), nuevo.getUsuario(), codigo);
    }

    public void verificar(String correo, String codigo) {
        if (correo == null || correo.isBlank() || codigo == null || codigo.isBlank()) {
            throw new DatoInvalidoException("El correo y el código son obligatorios");
        }
        Usuario u = uRep.buscarPorCorreo(correo.trim());
        if (u == null) {
            throw new RecursoNoEncontradoException("No existe una cuenta con ese correo");
        }
        if (u.isVerificado()) {
            throw new ConflictoException("La cuenta ya está verificada");
        }
        if (u.getCodigoVerificacion() == null || u.getExpiraVerificacion() == null
                || LocalDateTime.now().isAfter(u.getExpiraVerificacion())) {
            throw new DatoInvalidoException("El código expiró. Solicita uno nuevo");
        }
        if (!u.getCodigoVerificacion().equals(codigo.trim())) {
            throw new DatoInvalidoException("El código es incorrecto");
        }
        uRep.marcarVerificado(u.getIdUsuario());
    }

    public void reenviarCodigo(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new DatoInvalidoException("El correo es obligatorio");
        }
        Usuario u = uRep.buscarPorCorreo(correo.trim());
        if (u == null) {
            throw new RecursoNoEncontradoException("No existe una cuenta con ese correo");
        }
        if (u.isVerificado()) {
            throw new ConflictoException("La cuenta ya está verificada");
        }
        String codigo = generarCodigo();
        uRep.guardarCodigo(u.getIdUsuario(), codigo, LocalDateTime.now().plusMinutes(MINUTOS_VALIDEZ_CODIGO));
        emailService.enviarCodigoVerificacion(u.getCorreo(), u.getUsuario(), codigo);
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
        // Se revisa después de la contraseña para no revelar si la cuenta existe
        if (!encontrado.isVerificado()) {
            throw new CuentaNoVerificadaException("Debes verificar tu correo antes de iniciar sesión");
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