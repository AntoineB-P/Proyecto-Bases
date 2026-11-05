package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) encargada de transportar la información
 * relacionada con los estadios deportivos.
 *
 * Contiene los datos principales del estadio, como su identificador,
 * nombre, capacidad, dirección, ciudad y barrio donde se encuentra ubicado.
 *
 * Implementa los métodos equals(), hashCode() y toString() para facilitar
 * la comparación y representación textual de los objetos.
 *
 * @author
 * @version 1.0
 */
public class EstadioDTO {

	/** Identificador único del estadio. */
	private Long idEstadio;

	/** Nombre del estadio deportivo. */
	private String nombre;

	/** Capacidad máxima de espectadores que puede albergar el estadio. */
	private int capacidad;

	/** Dirección física donde se encuentra ubicado el estadio. */
	private String direccion;

	/** Ciudad en la que se encuentra ubicado el estadio. */
	private String ciudad;

	/** Barrio en el que se encuentra ubicado el estadio. */
	private String barrio;

	/**
	 * Constructor vacío de la clase EstadioDTO.
	 * Permite crear un objeto sin inicializar sus atributos.
	 */
	public EstadioDTO() {

	}

	/**
	 * Constructor parametrizado que permite inicializar todos los atributos
	 * de un objeto EstadioDTO.
	 *
	 * @param idEstadio identificador único del estadio.
	 * @param nombre nombre del estadio.
	 * @param capacidad capacidad máxima de espectadores.
	 * @param direccion dirección física del estadio.
	 * @param ciudad ciudad donde se encuentra ubicado.
	 * @param barrio barrio donde se encuentra ubicado.
	 */
	public EstadioDTO(Long idEstadio, String nombre, int capacidad, String direccion, String ciudad, String barrio) {
		super();
		this.idEstadio = idEstadio;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.barrio = barrio;
	}

	/**
	 * Obtiene el identificador del estadio.
	 *
	 * @return identificador único del estadio.
	 */
	public Long getIdEstadio() {
		return idEstadio;
	}

	/**
	 * Modifica el identificador del estadio.
	 *
	 * @param idEstadio nuevo identificador del estadio.
	 */
	public void setIdEstadio(Long idEstadio) {
		this.idEstadio = idEstadio;
	}

	/**
	 * Obtiene el nombre del estadio.
	 *
	 * @return nombre del estadio.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modifica el nombre del estadio.
	 *
	 * @param nombre nuevo nombre del estadio.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la capacidad máxima del estadio.
	 *
	 * @return cantidad máxima de espectadores que puede albergar.
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * Modifica la capacidad máxima del estadio.
	 *
	 * @param capacidad nueva capacidad del estadio.
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * Obtiene la dirección del estadio.
	 *
	 * @return dirección física del estadio.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Modifica la dirección del estadio.
	 *
	 * @param direccion nueva dirección del estadio.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Obtiene la ciudad donde se encuentra ubicado el estadio.
	 *
	 * @return ciudad del estadio.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Modifica la ciudad donde se encuentra ubicado el estadio.
	 *
	 * @param ciudad nueva ciudad del estadio.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Obtiene el barrio donde se encuentra ubicado el estadio.
	 *
	 * @return barrio del estadio.
	 */
	public String getBarrio() {
		return barrio;
	}

	/**
	 * Modifica el barrio donde se encuentra ubicado el estadio.
	 *
	 * @param barrio nuevo barrio del estadio.
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	/**
	 * Genera un código hash utilizando todos los atributos de la clase.
	 *
	 * @return código hash correspondiente al objeto EstadioDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(barrio, capacidad, ciudad, direccion, idEstadio, nombre);
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
		EstadioDTO other = (EstadioDTO) obj;
		return Objects.equals(barrio, other.barrio) && capacidad == other.capacidad
				&& Objects.equals(ciudad, other.ciudad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(idEstadio, other.idEstadio) && Objects.equals(nombre, other.nombre);
	}

	/**
	 * Genera una representación textual del objeto EstadioDTO.
	 * Incluye los valores de todos sus atributos.
	 *
	 * @return cadena de texto con la información completa del estadio.
	 */
	@Override
	public String toString() {
		return "EstadioDTO [idEstadio=" + idEstadio + ", nombre=" + nombre + ", capacidad=" + capacidad + ", direccion="
				+ direccion + ", ciudad=" + ciudad + ", barrio=" + barrio + "]";
	}

}