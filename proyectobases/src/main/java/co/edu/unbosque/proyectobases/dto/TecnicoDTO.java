package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) que representa la información de un técnico
 * o entrenador perteneciente a un equipo de fútbol.
 *
 * Permite transportar los datos personales del técnico, incluyendo su
 * identificación, nombres, apellidos, nacionalidad y fecha de nacimiento.
 *
 * @author
 * @version 1.0
 */
public class TecnicoDTO {

	/**
	 * Identificador único del técnico.
	 */
	private Long idTecnico;

	/**
	 * Nombres del técnico.
	 */
	private String nombres;

	/**
	 * Apellidos del técnico.
	 */
	private String apellidos;

	/**
	 * Nacionalidad del técnico.
	 */
	private String nacionalidad;

	/**
	 * Fecha de nacimiento del técnico.
	 */
	private LocalDate fechaNacimiento;

	/**
	 * Constructor vacío de la clase.
	 * Permite crear un objeto TecnicoDTO sin inicializar sus atributos.
	 */
	public TecnicoDTO() {

	}

	/**
	 * Constructor parametrizado de la clase.
	 * Permite crear un objeto TecnicoDTO inicializando todos sus atributos.
	 *
	 * @param idTecnico Identificador único del técnico.
	 * @param nombres Nombres del técnico.
	 * @param apellidos Apellidos del técnico.
	 * @param nacionalidad Nacionalidad del técnico.
	 * @param fechaNacimiento Fecha de nacimiento del técnico.
	 */
	public TecnicoDTO(Long idTecnico, String nombres, String apellidos, String nacionalidad,
			LocalDate fechaNacimiento) {
		super();
		this.idTecnico = idTecnico;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Obtiene el identificador del técnico.
	 *
	 * @return Identificador único del técnico.
	 */
	public Long getIdTecnico() {
		return idTecnico;
	}

	/**
	 * Modifica el identificador del técnico.
	 *
	 * @param idTecnico Nuevo identificador del técnico.
	 */
	public void setIdTecnico(Long idTecnico) {
		this.idTecnico = idTecnico;
	}

	/**
	 * Obtiene los nombres del técnico.
	 *
	 * @return Nombres del técnico.
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * Modifica los nombres del técnico.
	 *
	 * @param nombres Nuevos nombres del técnico.
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Obtiene los apellidos del técnico.
	 *
	 * @return Apellidos del técnico.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Modifica los apellidos del técnico.
	 *
	 * @param apellidos Nuevos apellidos del técnico.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Obtiene la nacionalidad del técnico.
	 *
	 * @return Nacionalidad del técnico.
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * Modifica la nacionalidad del técnico.
	 *
	 * @param nacionalidad Nueva nacionalidad del técnico.
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * Obtiene la fecha de nacimiento del técnico.
	 *
	 * @return Fecha de nacimiento del técnico.
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Modifica la fecha de nacimiento del técnico.
	 *
	 * @param fechaNacimiento Nueva fecha de nacimiento del técnico.
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Genera un código hash utilizando todos los atributos del objeto.
	 * Facilita la organización y comparación de objetos en estructuras
	 * de datos que utilizan códigos hash.
	 *
	 * @return Código hash del objeto TecnicoDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, fechaNacimiento, idTecnico, nacionalidad, nombres);
	}

	/**
	 * Compara este objeto con otro para determinar si ambos son iguales.
	 * Primero verifica si se trata de la misma instancia, si el objeto recibido
	 * es nulo y si pertenece a la misma clase. Posteriormente compara todos
	 * los atributos de ambos objetos.
	 *
	 * @param obj Objeto con el que se realizará la comparación.
	 * @return true si todos los atributos coinciden, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TecnicoDTO other = (TecnicoDTO) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& Objects.equals(idTecnico, other.idTecnico) && Objects.equals(nacionalidad, other.nacionalidad)
				&& Objects.equals(nombres, other.nombres);
	}

	/**
	 * Devuelve una representación textual del objeto con los valores
	 * de todos sus atributos.
	 *
	 * @return Cadena de texto con la información completa del técnico.
	 */
	@Override
	public String toString() {
		return "TecnicoDTO [idTecnico=" + idTecnico + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

}