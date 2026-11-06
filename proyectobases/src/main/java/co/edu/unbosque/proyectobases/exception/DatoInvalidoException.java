package co.edu.unbosque.proyectobases.exception;

/**
 * Clase que representa una excepción personalizada para indicar
 * que se ha proporcionado un dato inválido dentro de la aplicación.
 *
 * Extiende RuntimeException para permitir el manejo de errores
 * relacionados con datos incorrectos durante la ejecución.
 */
public class DatoInvalidoException extends RuntimeException {

    /**
     * Constructor que permite crear una excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del dato inválido o del error ocurrido.
     */
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }

}