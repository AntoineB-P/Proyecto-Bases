package co.edu.unbosque.proyectobases.exception;

/**
 * Clase que representa una excepción personalizada para indicar
 * que un recurso solicitado no existe o no ha sido encontrado
 * dentro de la aplicación.
 *
 * Extiende RuntimeException para permitir el manejo de este tipo
 * de errores durante la ejecución del programa.
 */
public class RecursoNoEncontradoException extends RuntimeException {

    /**
     * Constructor que permite crear una excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del recurso que no fue encontrado.
     */
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

}