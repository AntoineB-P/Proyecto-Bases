package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase DTO que representa la información de un torneo dentro del sistema.
 * Permite transportar los datos generales, las fechas y la duración del torneo.
 */
public class TorneoDTO {

	/** Identificador único del torneo. */
	private Long idTorneo;

	/** Nombre del torneo. */
	private String nombre;

	/** Año en el que se realiza el torneo. */
	private int anio;

	/** Semestre académico en el que se realiza el torneo. */
	private int semestre;

	/** Fecha de inicio del torneo. */
	private LocalDate fechaInicio;

	/** Fecha de finalización del torneo. */
	private LocalDate fechaFin;

	/** Duración total del torneo en días (atributo derivado). */
	private int duracionDias;

	/**
	 * Constructor vacío de la clase TorneoDTO.
	 */
	public TorneoDTO() {

	}

	/**
	 * Constructor parametrizado de la clase TorneoDTO.
	 *
	 * @param idTorneo identificador único del torneo.
	 * @param nombre nombre del torneo.
	 * @param anio año en el que se realiza el torneo.
	 * @param semestre semestre en el que se realiza el torneo.
	 * @param fechaInicio fecha de inicio del torneo.
	 * @param fechaFin fecha de finalización del torneo.
	 * @param duracionDias duración del torneo expresada en días.
	 */
	public TorneoDTO(Long idTorneo, String nombre, int anio, int semestre, LocalDate fechaInicio, LocalDate fechaFin,
			int duracionDias) {
		super();
		this.idTorneo = idTorneo;
		this.nombre = nombre;
		this.anio = anio;
		this.semestre = semestre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracionDias = duracionDias;
	}

	/**
	 * Obtiene el identificador del torneo.
	 *
	 * @return identificador del torneo.
	 */
	public Long getIdTorneo() {
		return idTorneo;
	}

	/**
	 * Asigna el identificador del torneo.
	 *
	 * @param idTorneo identificador que se desea asignar.
	 */
	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	/**
	 * Obtiene el nombre del torneo.
	 *
	 * @return nombre del torneo.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre del torneo.
	 *
	 * @param nombre nombre que se desea asignar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el año en el que se realiza el torneo.
	 *
	 * @return año del torneo.
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Asigna el año en el que se realiza el torneo.
	 *
	 * @param anio año que se desea asignar.
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * Obtiene el semestre en el que se realiza el torneo.
	 *
	 * @return semestre del torneo.
	 */
	public int getSemestre() {
		return semestre;
	}

	/**
	 * Asigna el semestre en el que se realiza el torneo.
	 *
	 * @param semestre semestre que se desea asignar.
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	/**
	 * Obtiene la fecha de inicio del torneo.
	 *
	 * @return fecha de inicio.
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio del torneo.
	 *
	 * @param fechaInicio fecha de inicio que se desea asignar.
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Obtiene la fecha de finalización del torneo.
	 *
	 * @return fecha de finalización.
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * Asigna la fecha de finalización del torneo.
	 *
	 * @param fechaFin fecha de finalización que se desea asignar.
	 */
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Obtiene la duración del torneo en días.
	 *
	 * @return duración del torneo en días.
	 */
	public int getDuracionDias() {
		return duracionDias;
	}

	/**
	 * Asigna la duración del torneo en días.
	 *
	 * @param duracionDias duración que se desea asignar.
	 */
	public void setDuracionDias(int duracionDias) {
		this.duracionDias = duracionDias;
	}

	/**
	 * Genera el código hash del objeto utilizando todos sus atributos.
	 *
	 * @return código hash del torneo.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(anio, duracionDias, fechaFin, fechaInicio, idTorneo, nombre, semestre);
	}

	/**
	 * Compara este objeto con otro para determinar si son iguales.
	 * La comparación tiene en cuenta todos los atributos del torneo.
	 *
	 * @param obj objeto con el que se realizará la comparación.
	 * @return true si ambos objetos tienen los mismos atributos; false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TorneoDTO other = (TorneoDTO) obj;
		return anio == other.anio && duracionDias == other.duracionDias && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(idTorneo, other.idTorneo)
				&& Objects.equals(nombre, other.nombre) && semestre == other.semestre;
	}

	/**
	 * Devuelve una representación en texto del objeto TorneoDTO,
	 * incluyendo todos sus atributos.
	 *
	 * @return cadena de texto con la información del torneo.
	 */
	@Override
	public String toString() {
		return "TorneoDTO [idTorneo=" + idTorneo + ", nombre=" + nombre + ", anio=" + anio + ", semestre=" + semestre
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", duracionDias=" + duracionDias + "]";
	}

}