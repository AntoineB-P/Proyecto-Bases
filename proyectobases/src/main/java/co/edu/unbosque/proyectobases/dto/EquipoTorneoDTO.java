package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) encargada de transportar la información
 * relacionada con la asociación entre un equipo y un torneo.
 *
 * Contiene los identificadores y nombres del equipo y del torneo al que
 * pertenece, permitiendo representar la relación entre ambas entidades.
 *
 * Implementa los métodos equals(), hashCode() y toString() para facilitar
 * la comparación y representación textual de los objetos.
 *
 * @author
 * @version 1.0
 */
public class EquipoTorneoDTO {

	/** Identificador único del equipo. */
	private Long idEquipo;

	/** Nombre del equipo participante en el torneo. */
	private String nombreEquipo;

	/** Identificador único del torneo. */
	private Long idTorneo;

	/** Nombre del torneo al que pertenece el equipo. */
	private String nombreTorneo;

	/**
	 * Constructor vacío de la clase EquipoTorneoDTO.
	 * Permite crear un objeto sin inicializar sus atributos.
	 */
	public EquipoTorneoDTO() {

	}

	/**
	 * Constructor parametrizado que permite inicializar todos los atributos
	 * de un objeto EquipoTorneoDTO.
	 *
	 * @param idEquipo identificador único del equipo.
	 * @param nombreEquipo nombre del equipo participante.
	 * @param idTorneo identificador único del torneo.
	 * @param nombreTorneo nombre del torneo correspondiente.
	 */
	public EquipoTorneoDTO(Long idEquipo, String nombreEquipo, Long idTorneo, String nombreTorneo) {
		super();
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
		this.idTorneo = idTorneo;
		this.nombreTorneo = nombreTorneo;
	}

	/**
	 * Obtiene el identificador del equipo.
	 *
	 * @return identificador único del equipo.
	 */
	public Long getIdEquipo() {
		return idEquipo;
	}

	/**
	 * Modifica el identificador del equipo.
	 *
	 * @param idEquipo nuevo identificador del equipo.
	 */
	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	/**
	 * Obtiene el nombre del equipo.
	 *
	 * @return nombre del equipo participante.
	 */
	public String getNombreEquipo() {
		return nombreEquipo;
	}

	/**
	 * Modifica el nombre del equipo.
	 *
	 * @param nombreEquipo nuevo nombre del equipo.
	 */
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	/**
	 * Obtiene el identificador del torneo.
	 *
	 * @return identificador único del torneo.
	 */
	public Long getIdTorneo() {
		return idTorneo;
	}

	/**
	 * Modifica el identificador del torneo.
	 *
	 * @param idTorneo nuevo identificador del torneo.
	 */
	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	/**
	 * Obtiene el nombre del torneo.
	 *
	 * @return nombre del torneo correspondiente.
	 */
	public String getNombreTorneo() {
		return nombreTorneo;
	}

	/**
	 * Modifica el nombre del torneo.
	 *
	 * @param nombreTorneo nuevo nombre del torneo.
	 */
	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}

	/**
	 * Genera un código hash utilizando todos los atributos de la clase.
	 *
	 * @return código hash correspondiente al objeto EquipoTorneoDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idEquipo, idTorneo, nombreEquipo, nombreTorneo);
	}

	/**
	 * Compara este objeto con otro para determinar si ambos son iguales.
	 * La comparación se realiza verificando los valores de todos los atributos.
	 *
	 * @param obj objeto con el que se realizará la comparación.
	 * @return true si ambos objetos contienen la misma información;
	 *         false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipoTorneoDTO other = (EquipoTorneoDTO) obj;
		return Objects.equals(idEquipo, other.idEquipo) && Objects.equals(idTorneo, other.idTorneo)
				&& Objects.equals(nombreEquipo, other.nombreEquipo) && Objects.equals(nombreTorneo, other.nombreTorneo);
	}

	/**
	 * Genera una representación textual del objeto EquipoTorneoDTO.
	 * Incluye los valores de todos sus atributos.
	 *
	 * @return cadena de texto con la información de la relación entre
	 *         el equipo y el torneo.
	 */
	@Override
	public String toString() {
		return "EquipoTorneoDTO [idEquipo=" + idEquipo + ", nombreEquipo=" + nombreEquipo + ", idTorneo=" + idTorneo
				+ ", nombreTorneo=" + nombreTorneo + "]";
	}

}