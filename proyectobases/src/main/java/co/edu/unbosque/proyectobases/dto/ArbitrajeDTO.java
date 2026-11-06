package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) encargada de transportar la información
 * relacionada con la asignación de un árbitro a un partido.
 *
 * Contiene los datos de identificación del árbitro, su nombre, el identificador
 * del partido al que está asignado y el rol que desempeña durante el encuentro.
 *
 * También implementa los métodos equals(), hashCode() y toString() para
 * facilitar la comparación, identificación y representación textual de los objetos.
 *
 * @author
 * @version 1.0
 */
public class ArbitrajeDTO {

    /** Número de cédula que identifica al árbitro. */
    private String cedulaArbitro;

    /** Nombre completo del árbitro asignado al partido. */
    private String nombreArbitro;

    /** Identificador único del partido en el que participa el árbitro. */
    private Long idPartido;

    /** Rol o función que desempeña el árbitro durante el partido. */
    private String rol;

    /**
     * Constructor vacío de la clase ArbitrajeDTO.
     * Permite crear un objeto sin inicializar sus atributos.
     */
    public ArbitrajeDTO() {

    }

    /**
     * Constructor parametrizado que permite inicializar todos los atributos
     * de un objeto ArbitrajeDTO.
     *
     * @param cedulaArbitro número de identificación del árbitro.
     * @param nombreArbitro nombre del árbitro.
     * @param idPartido identificador del partido correspondiente.
     * @param rol función que desempeña el árbitro en el partido.
     */
    public ArbitrajeDTO(Long cedulaArbitro, String nombreArbitro, Long idPartido, String rol) {
        super();
        this.cedulaArbitro = cedulaArbitro;
        this.nombreArbitro = nombreArbitro;
        this.idPartido = idPartido;
        this.rol = rol;
    }

    /**
     * Obtiene el número de cédula del árbitro.
     *
     * @return número de identificación del árbitro.
     */
    public Long getCedulaArbitro() {
        return cedulaArbitro;
    }

    /**
     * Modifica el número de cédula del árbitro.
     *
     * @param cedulaArbitro nuevo número de identificación.
     */
    public void setCedulaArbitro(Long cedulaArbitro) {
        this.cedulaArbitro = cedulaArbitro;
    }

    /**
     * Obtiene el nombre del árbitro.
     *
     * @return nombre completo del árbitro.
     */
    public String getNombreArbitro() {
        return nombreArbitro;
    }

    /**
     * Modifica el nombre del árbitro.
     *
     * @param nombreArbitro nuevo nombre del árbitro.
     */
    public void setNombreArbitro(String nombreArbitro) {
        this.nombreArbitro = nombreArbitro;
    }

    /**
     * Obtiene el identificador del partido.
     *
     * @return identificador del partido al que está asignado el árbitro.
     */
    public Long getIdPartido() {
        return idPartido;
    }

    /**
     * Modifica el identificador del partido.
     *
     * @param idPartido nuevo identificador del partido.
     */
    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    /**
     * Obtiene el rol del árbitro en el partido.
     *
     * @return función que desempeña el árbitro.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Modifica el rol del árbitro.
     *
     * @param rol nuevo rol asignado al árbitro.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Genera un código hash utilizando los atributos de la clase.
     *
     * @return código hash correspondiente al objeto ArbitrajeDTO.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cedulaArbitro, idPartido, nombreArbitro, rol);
    }

    /**
     * Compara este objeto con otro para determinar si ambos son iguales.
     * La comparación se realiza verificando los valores de todos los atributos.
     *
     * @param obj objeto con el que se realizará la comparación.
     * @return true si ambos objetos tienen la misma información; false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArbitrajeDTO other = (ArbitrajeDTO) obj;
        return Objects.equals(cedulaArbitro, other.cedulaArbitro) && Objects.equals(idPartido, other.idPartido)
                && Objects.equals(nombreArbitro, other.nombreArbitro) && Objects.equals(rol, other.rol);
    }

    /**
     * Genera una representación textual del objeto ArbitrajeDTO.
     * Incluye los valores de todos sus atributos.
     *
     * @return cadena de texto con la información del arbitraje.
     */
    @Override
    public String toString() {
        return "ArbitrajeDTO [cedulaArbitro=" + cedulaArbitro + ", nombreArbitro=" + nombreArbitro + ", idPartido="
                + idPartido + ", rol=" + rol + "]";
    }

}