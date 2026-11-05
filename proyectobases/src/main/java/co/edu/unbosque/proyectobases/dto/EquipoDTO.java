package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) encargada de transportar la información
 * relacionada con los equipos de fútbol.
 *
 * Contiene los datos principales del equipo, como su identificador, nombre,
 * fecha de fundación, ciudad, colores representativos, sede, estadio y técnico.
 *
 * Implementa los métodos equals(), hashCode() y toString() para facilitar
 * la comparación y representación textual de los objetos.
 *
 * @author
 * @version 1.0
 */
public class EquipoDTO {

	/** Identificador único del equipo. */
	private Long idEquipo;

	/** Nombre oficial del equipo. */
	private String nombre;

	/** Fecha de fundación del equipo. */
	private LocalDate fechaFundacion;

	/** Ciudad a la que pertenece el equipo. */
	private String ciudad;

	/** Lista de colores representativos del equipo. */
	private List<String> colores;

	/** Identificador de la sede del equipo. */
	private Long idSede;

	/** Nombre del estadio donde juega el equipo. */
	private String nombreEstadio;

	/** Identificador del técnico encargado del equipo. */
	private Long idTecnico;

	/** Nombre del técnico encargado del equipo. */
	private String nombreTecnico;

	/**
	 * Constructor vacío de la clase EquipoDTO.
	 * Permite crear un objeto sin inicializar sus atributos.
	 */
	public EquipoDTO() {

	}

	/**
	 * Constructor parametrizado que permite inicializar todos los atributos
	 * de un objeto EquipoDTO.
	 *
	 * @param idEquipo identificador único del equipo.
	 * @param nombre nombre oficial del equipo.
	 * @param fechaFundacion fecha de fundación del equipo.
	 * @param ciudad ciudad a la que pertenece el equipo.
	 * @param colores lista de colores representativos del equipo.
	 * @param idSede identificador de la sede del equipo.
	 * @param nombreEstadio nombre del estadio del equipo.
	 * @param idTecnico identificador del técnico encargado.
	 * @param nombreTecnico nombre del técnico encargado.
	 */
	public EquipoDTO(Long idEquipo, String nombre, LocalDate fechaFundacion, String ciudad, List<String> colores,
			Long idSede, String nombreEstadio, Long idTecnico, String nombreTecnico) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.fechaFundacion = fechaFundacion;
		this.ciudad = ciudad;
		this.colores = colores;
		this.idSede = idSede;
		this.nombreEstadio = nombreEstadio;
		this.idTecnico = idTecnico;
		this.nombreTecnico = nombreTecnico;
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
	 * @return nombre oficial del equipo.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modifica el nombre del equipo.
	 *
	 * @param nombre nuevo nombre del equipo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la fecha de fundación del equipo.
	 *
	 * @return fecha de fundación.
	 */
	public LocalDate getFechaFundacion() {
		return fechaFundacion;
	}

	/**
	 * Modifica la fecha de fundación del equipo.
	 *
	 * @param fechaFundacion nueva fecha de fundación.
	 */
	public void setFechaFundacion(LocalDate fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}

	/**
	 * Obtiene la ciudad a la que pertenece el equipo.
	 *
	 * @return ciudad del equipo.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Modifica la ciudad del equipo.
	 *
	 * @param ciudad nueva ciudad del equipo.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Obtiene la lista de colores representativos del equipo.
	 *
	 * @return lista de colores del equipo.
	 */
	public List<String> getColores() {
		return colores;
	}

	/**
	 * Modifica la lista de colores representativos del equipo.
	 *
	 * @param colores nueva lista de colores.
	 */
	public void setColores(List<String> colores) {
		this.colores = colores;
	}

	/**
	 * Obtiene el identificador de la sede del equipo.
	 *
	 * @return identificador de la sede.
	 */
	public Long getIdSede() {
		return idSede;
	}

	/**
	 * Modifica el identificador de la sede del equipo.
	 *
	 * @param idSede nuevo identificador de la sede.
	 */
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	/**
	 * Obtiene el nombre del estadio del equipo.
	 *
	 * @return nombre del estadio.
	 */
	public String getNombreEstadio() {
		return nombreEstadio;
	}

	/**
	 * Modifica el nombre del estadio del equipo.
	 *
	 * @param nombreEstadio nuevo nombre del estadio.
	 */
	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	/**
	 * Obtiene el identificador del técnico del equipo.
	 *
	 * @return identificador del técnico.
	 */
	public Long getIdTecnico() {
		return idTecnico;
	}

	/**
	 * Modifica el identificador del técnico del equipo.
	 *
	 * @param idTecnico nuevo identificador del técnico.
	 */
	public void setIdTecnico(Long idTecnico) {
		this.idTecnico = idTecnico;
	}

	/**
	 * Obtiene el nombre del técnico del equipo.
	 *
	 * @return nombre del técnico.
	 */
	public String getNombreTecnico() {
		return nombreTecnico;
	}

	/**
	 * Modifica el nombre del técnico del equipo.
	 *
	 * @param nombreTecnico nuevo nombre del técnico.
	 */
	public void setNombreTecnico(String nombreTecnico) {
		this.nombreTecnico = nombreTecnico;
	}

	/**
	 * Genera un código hash utilizando todos los atributos de la clase.
	 *
	 * @return código hash correspondiente al objeto EquipoDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ciudad, colores, escudoUrl, fechaFundacion, idEquipo, idSede, idTecnico, nombre,
				nombreEstadio, nombreTecnico);
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
		EquipoDTO other = (EquipoDTO) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(colores, other.colores)
				&& Objects.equals(escudoUrl, other.escudoUrl)
				&& Objects.equals(fechaFundacion, other.fechaFundacion) && Objects.equals(idEquipo, other.idEquipo)
				&& Objects.equals(idSede, other.idSede) && Objects.equals(idTecnico, other.idTecnico)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nombreEstadio, other.nombreEstadio)
				&& Objects.equals(nombreTecnico, other.nombreTecnico);
	}

	/**
	 * Genera una representación textual del objeto EquipoDTO.
	 * Incluye los valores de todos sus atributos.
	 *
	 * @return cadena de texto con la información completa del equipo.
	 */
	@Override
	public String toString() {
		return "EquipoDTO [idEquipo=" + idEquipo + ", nombre=" + nombre + ", fechaFundacion=" + fechaFundacion
				+ ", ciudad=" + ciudad + ", colores=" + colores + ", idSede=" + idSede + ", nombreEstadio="
				+ nombreEstadio + ", idTecnico=" + idTecnico + ", nombreTecnico=" + nombreTecnico
				+ ", escudoUrl=" + escudoUrl + "]";
	}





}