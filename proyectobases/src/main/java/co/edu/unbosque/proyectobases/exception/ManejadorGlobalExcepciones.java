package co.edu.unbosque.proyectobases.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase encargada de manejar globalmente las excepciones generadas
 * durante la ejecución de los controladores REST de la aplicación.
 *
 * Permite centralizar el manejo de errores y devolver respuestas HTTP
 * con el código de estado correspondiente y un mensaje descriptivo.
 */
@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    /**
     * Maneja las excepciones relacionadas con recursos que no existen.
     *
     * @param e excepción que contiene el mensaje del recurso no encontrado.
     * @return respuesta HTTP 404 (NOT_FOUND) con el mensaje del error.
     */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> noEncontrado(RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }

    /**
     * Maneja las excepciones generadas cuando se proporciona un dato inválido.
     *
     * @param e excepción que contiene el mensaje del dato inválido.
     * @return respuesta HTTP 400 (BAD_REQUEST) con el mensaje del error.
     */
    @ExceptionHandler(DatoInvalidoException.class)
    public ResponseEntity<Map<String, String>> invalido(DatoInvalidoException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

    /**
     * Maneja las excepciones relacionadas con conflictos durante una operación.
     *
     * @param e excepción que contiene el mensaje del conflicto ocurrido.
     * @return respuesta HTTP 409 (CONFLICT) con el mensaje del error.
     */
    @ExceptionHandler(ConflictoException.class)
    public ResponseEntity<Map<String, String>> conflicto(ConflictoException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
    }
    
    @ExceptionHandler(CuentaNoVerificadaException.class)
    public ResponseEntity<Map<String, String>> noVerificada(CuentaNoVerificadaException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(ServicioCorreoException.class)
    public ResponseEntity<Map<String, String>> errorCorreo(ServicioCorreoException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of("error", e.getMessage()));
    }
}