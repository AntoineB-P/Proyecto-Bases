package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO que representa la información de un tipo de tarjeta
 * dentro del sistema.
 * Permite transportar el identificador y el color de la tarjeta.
 */
public class TipoTarjetaDTO {

	/** Identificador único del tipo de tarjeta. */
	private Long idTipo;

	/** Color asociado al tipo de tarjeta. */
	private String color;

	/**
	 * Constructor vacío de la clase TipoTarjetaDTO.
	 */
	public TipoTarjetaDTO() {

	}

	/**
	 * Constructor parametrizado de la clase TipoTarjetaDTO.
	 *
	 * @param idTipo identificador único del tipo de tarjeta.
	 * @param color color asociado al tipo de tarjeta.
	 */
	public TipoTarjetaDTO(Long idTipo, String color) {
		super();
		this.idTipo = idTipo;
		this.color = color;
	}

	/**
	 * Obtiene el identificador del tipo de tarjeta.
	 *
	 * @return identificador del tipo de tarjeta.
	 */
	public Long getIdTipo() {
		return idTipo;
	}

	/**
	 * Asigna el identificador del tipo de tarjeta.
	 *
	 * @param idTipo identificador que se desea asignar.
	 */
	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	/**
	 * Obtiene el color asociado al tipo de tarjeta.
	 *
	 * @return color del tipo de tarjeta.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Asigna el color al tipo de tarjeta.
	 *
	 * @param color color que se desea asignar.
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Genera el código hash del objeto utilizando sus atributos.
	 *
	 * @return código hash calculado a partir del color y el identificador.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(color, idTipo);
	}

	/**
	 * Compara este objeto con otro para determinar si son iguales.
	 * La comparación se realiza teniendo en cuenta el identificador
	 * y el color del tipo de tarjeta.
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
		TipoTarjetaDTO other = (TipoTarjetaDTO) obj;
		return Objects.equals(color, other.color) && Objects.equals(idTipo, other.idTipo);
	}

	/**
	 * Devuelve una representación en texto del objeto TipoTarjetaDTO,
	 * incluyendo su identificador y color.
	 *
	 * @return cadena de texto con los atributos del objeto.
	 */
	@Override
	public String toString() {
		return "TipoTarjetaDTO [idTipo=" + idTipo + ", color=" + color + "]";
	}

}