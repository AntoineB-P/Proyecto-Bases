package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) encargada de transportar la información
 * correspondiente a un árbitro.
 *
 * Contiene los datos personales y profesionales del árbitro, incluyendo
 * su número de cédula, nombres, apellidos, ciudad, categoría y la cantidad
 * de partidos que ha dirigido.
 *
 * Implementa los métodos equals(), hashCode() y toString() para permitir
 * la comparación y representación textual de los objetos.
 *
 * @author
 * @version 1.0
 */
public class ArbitroDTO {

    /** Número de cédula que identifica al árbitro. */
    private String cedula;

    /** Nombres del árbitro. */
    private String nombres;

    /** Apellidos del árbitro. */
    private String apellidos;

    /** Ciudad de procedencia o residencia del árbitro. */
    private String ciudad;

    /** Categoría profesional o clasificación del árbitro. */
    private String categoria;

    /** Cantidad de partidos que ha dirigido el árbitro. */
    private Integer partidosDirigidos;

    /**
     * Constructor vacío de la clase ArbitroDTO.
     * Permite crear un objeto sin inicializar sus atributos.
     */
    public ArbitroDTO() {

    }

    /**
     * Constructor parametrizado que permite inicializar todos los atributos
     * de un objeto ArbitroDTO.
     *
     * @param cedula número de identificación del árbitro.
     * @param nombres nombres del árbitro.
     * @param apellidos apellidos del árbitro.
     * @param ciudad ciudad de procedencia o residencia.
     * @param categoria categoría profesional del árbitro.
     * @param partidosDirigidos cantidad de partidos dirigidos.
     */
    public ArbitroDTO(String cedula, String nombres, String apellidos, String ciudad, String categoria,
            Integer partidosDirigidos) {
        super();
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.categoria = categoria;
        this.partidosDirigidos = partidosDirigidos;
    }

    /**
     * Obtiene el número de cédula del árbitro.
     *
     * @return número de identificación del árbitro.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Modifica el número de cédula del árbitro.
     *
     * @param cedula nuevo número de identificación.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene los nombres del árbitro.
     *
     * @return nombres del árbitro.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Modifica los nombres del árbitro.
     *
     * @param nombres nuevos nombres del árbitro.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del árbitro.
     *
     * @return apellidos del árbitro.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Modifica los apellidos del árbitro.
     *
     * @param apellidos nuevos apellidos del árbitro.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene la ciudad del árbitro.
     *
     * @return ciudad de procedencia o residencia.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modifica la ciudad del árbitro.
     *
     * @param ciudad nueva ciudad del árbitro.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene la categoría profesional del árbitro.
     *
     * @return categoría del árbitro.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Modifica la categoría profesional del árbitro.
     *
     * @param categoria nueva categoría del árbitro.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la cantidad de partidos dirigidos por el árbitro.
     *
     * @return número de partidos dirigidos.
     */
    public Integer getPartidosDirigidos() {
        return partidosDirigidos;
    }

    /**
     * Modifica la cantidad de partidos dirigidos por el árbitro.
     *
     * @param partidosDirigidos nueva cantidad de partidos dirigidos.
     */
    public void setPartidosDirigidos(Integer partidosDirigidos) {
        this.partidosDirigidos = partidosDirigidos;
    }

    /**
     * Genera un código hash utilizando los atributos de la clase.
     *
     * @return código hash correspondiente al objeto ArbitroDTO.
     */
    @Override
    public int hashCode() {
        return Objects.hash(apellidos, categoria, cedula, ciudad, nombres, partidosDirigidos);
    }

    /**
     * Compara este objeto con otro para determinar si ambos son iguales.
     * La comparación se realiza verificando los valores de todos los atributos.
     *
     * @param obj objeto con el que se realizará la comparación.
     * @return true si ambos objetos contienen la misma información; false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArbitroDTO other = (ArbitroDTO) obj;
        return Objects.equals(apellidos, other.apellidos) && Objects.equals(categoria, other.categoria)
                && Objects.equals(cedula, other.cedula) && Objects.equals(ciudad, other.ciudad)
                && Objects.equals(nombres, other.nombres) && Objects.equals(partidosDirigidos, other.partidosDirigidos);
    }

    /**
     * Genera una representación textual del objeto ArbitroDTO.
     * Incluye los valores de todos sus atributos.
     *
     * @return cadena de texto con la información del árbitro.
     */
    @Override
    public String toString() {
        return "ArbitroDTO [cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", ciudad="
                + ciudad + ", categoria=" + categoria + ", partidosDirigidos=" + partidosDirigidos + "]";
    }

}
