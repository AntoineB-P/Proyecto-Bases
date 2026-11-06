package co.edu.unbosque.proyectobases.exception;

/**
 * Clase que representa una excepción personalizada para indicar
 * situaciones de conflicto dentro de la aplicación.
 *
 * Extiende RuntimeException para permitir el manejo de errores
 * relacionados con conflictos durante la ejecución del programa.
 */
public class ConflictoException extends RuntimeException {

    /**
     * Constructor que crea una excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del conflicto ocurrido.
     */
    public ConflictoException(String mensaje) {
        super(mensaje);
    }

}