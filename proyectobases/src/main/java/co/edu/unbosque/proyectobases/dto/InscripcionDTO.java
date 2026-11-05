package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase DTO que representa la inscripción de un jugador en un equipo
 * perteneciente a un torneo.
 * Almacena información sobre el equipo, el torneo, el jugador,
 * su número de dorsal y el periodo de vigencia de la inscripción.
 */
public class InscripcionDTO {

	/** Identificador único del equipo al que pertenece la inscripción. */
	private Long idEquipo;

	/** Nombre del equipo en el que está inscrito el jugador. */
	private String nombreEquipo;

	/** Identificador único del torneo en el que participa el equipo. */
	private Long idTorneo;

	/** Identificador único del jugador inscrito. */
	private Long idJugador;

	/** Nombre del jugador que realiza la inscripción. */
	private String nombreJugador;

	/** Número de dorsal asignado al jugador dentro del equipo. */
	private Integer dorsal;

	/** Fecha de inicio de la inscripción del jugador. */
	private LocalDate fechaInicio;

	/** Fecha de finalización de la inscripción del jugador. */
	private LocalDate fechaFin;

	/**
	 * Constructor vacío de la clase InscripcionDTO.
	 */
	public InscripcionDTO() {

	}

	/**
	 * Constructor que inicializa todos los atributos de la inscripción.
	 *
	 * @param idEquipo identificador único del equipo.
	 * @param nombreEquipo nombre del equipo en el que participa el jugador.
	 * @param idTorneo identificador único del torneo.
	 * @param idJugador identificador único del jugador inscrito.
	 * @param nombreJugador nombre del jugador.
	 * @param dorsal número de dorsal asignado al jugador.
	 * @param fechaInicio fecha de inicio de la inscripción.
	 * @param fechaFin fecha de finalización de la inscripción.
	 */
	public InscripcionDTO(Long idEquipo, String nombreEquipo, Long idTorneo, Long idJugador, String nombreJugador,
			Integer dorsal, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
		this.idTorneo = idTorneo;
		this.idJugador = idJugador;
		this.nombreJugador = nombreJugador;
		this.dorsal = dorsal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
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
	 * Establece el identificador del equipo.
	 *
	 * @param idEquipo identificador único del equipo.
	 */
	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	/**
	 * Obtiene el nombre del equipo.
	 *
	 * @return nombre del equipo en el que está inscrito el jugador.
	 */
	public String getNombreEquipo() {
		return nombreEquipo;
	}

	/**
	 * Establece el nombre del equipo.
	 *
	 * @param nombreEquipo nombre del equipo correspondiente.
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
	 * Establece el identificador del torneo.
	 *
	 * @param idTorneo identificador único del torneo.
	 */
	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	/**
	 * Obtiene el identificador del jugador.
	 *
	 * @return identificador único del jugador inscrito.
	 */
	public Long getIdJugador() {
		return idJugador;
	}

	/**
	 * Establece el identificador del jugador.
	 *
	 * @param idJugador identificador único del jugador.
	 */
	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	/**
	 * Obtiene el nombre del jugador.
	 *
	 * @return nombre del jugador inscrito.
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}

	/**
	 * Establece el nombre del jugador.
	 *
	 * @param nombreJugador nombre del jugador correspondiente.
	 */
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	/**
	 * Obtiene el número de dorsal del jugador.
	 *
	 * @return número de dorsal asignado.
	 */
	public Integer getDorsal() {
		return dorsal;
	}

	/**
	 * Establece el número de dorsal del jugador.
	 *
	 * @param dorsal número de dorsal que se asignará al jugador.
	 */
	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	/**
	 * Obtiene la fecha de inicio de la inscripción.
	 *
	 * @return fecha en la que comienza la inscripción.
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Establece la fecha de inicio de la inscripción.
	 *
	 * @param fechaInicio fecha en la que comienza la inscripción.
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Obtiene la fecha de finalización de la inscripción.
	 *
	 * @return fecha en la que termina la inscripción.
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * Establece la fecha de finalización de la inscripción.
	 *
	 * @param fechaFin fecha en la que termina la inscripción.
	 */
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Calcula el código hash del objeto utilizando todos sus atributos.
	 *
	 * @return código hash generado a partir de los atributos de la clase.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dorsal, fechaFin, fechaInicio, idEquipo, idJugador, idTorneo, nombreEquipo, nombreJugador);
	}

	/**
	 * Compara este objeto con otro para determinar si son iguales.
	 * La comparación tiene en cuenta todos los atributos de la inscripción.
	 *
	 * @param obj objeto con el que se desea realizar la comparación.
	 * @return true si ambos objetos pertenecen a la misma clase y tienen
	 *         los mismos valores en sus atributos; false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InscripcionDTO other = (InscripcionDTO) obj;
		return Objects.equals(dorsal, other.dorsal) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(idEquipo, other.idEquipo)
				&& Objects.equals(idJugador, other.idJugador) && Objects.equals(idTorneo, other.idTorneo)
				&& Objects.equals(nombreEquipo, other.nombreEquipo)
				&& Objects.equals(nombreJugador, other.nombreJugador);
	}

	/**
	 * Genera una representación en cadena de texto del objeto,
	 * incluyendo los valores de todos sus atributos.
	 *
	 * @return representación textual de la inscripción.
	 */
	@Override
	public String toString() {
		return "InscripcionDTO [idEquipo=" + idEquipo + ", nombreEquipo=" + nombreEquipo + ", idTorneo=" + idTorneo
				+ ", idJugador=" + idJugador + ", nombreJugador=" + nombreJugador + ", dorsal=" + dorsal
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}


}