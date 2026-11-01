package co.edu.unbosque.proyectobases.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyectobases.dto.EquipoDTO;
import co.edu.unbosque.proyectobases.dto.UsuarioDTO;
import co.edu.unbosque.proyectobases.dto.VerificacionDTO;
import co.edu.unbosque.proyectobases.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsuarioDTO dto) {
        usuarioService.registrar(dto);
        return new ResponseEntity<>("Usuario registrado. Revisa tu correo para verificar la cuenta",
                HttpStatus.CREATED);
    }

    @PostMapping("/verificar")
    public ResponseEntity<String> verificar(@RequestBody VerificacionDTO dto) {
        usuarioService.verificar(dto.getCorreo(), dto.getCodigo());
        return new ResponseEntity<>("Cuenta verificada. Ya puedes iniciar sesión", HttpStatus.OK);
    }

    @PostMapping("/reenviar-codigo")
    public ResponseEntity<String> reenviarCodigo(@RequestBody VerificacionDTO dto) {
        usuarioService.reenviarCodigo(dto.getCorreo());
        return new ResponseEntity<>("Te enviamos un nuevo código", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO dto) {
        String token = usuarioService.login(dto.getUsuario(), dto.getContrasenia());
        if (token == null) {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PutMapping("/favorito/{idEquipo}")
    public ResponseEntity<String> marcarFavorito(@PathVariable Long idEquipo) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        usuarioService.marcarFavorito(username, idEquipo);
        return new ResponseEntity<>("Club favorito guardado", HttpStatus.OK);
    }

    @DeleteMapping("/favorito")
    public ResponseEntity<String> quitarFavorito() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        usuarioService.quitarFavorito(username);
        return new ResponseEntity<>("Club favorito eliminado", HttpStatus.OK);
    }

    @GetMapping("/favorito")
    public ResponseEntity<EquipoDTO> obtenerFavorito() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(usuarioService.obtenerFavorito(username), HttpStatus.OK);
    }
}