package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) que representa una tarjeta asignada
 * a un jugador durante un partido de fútbol.
 *
 * Permite transportar la información relacionada con las tarjetas recibidas
 * por un jugador, incluyendo su tipo, color, motivo de sanción y minuto
 * en el que fue mostrada durante el encuentro.
 *
 * @author
 * @version 1.0
 */
public class TarjetaDTO {

	/**
	 * Identificador único de la tarjeta.
	 */
	private Long idTarjeta;

	/**
	 * Identificador de la participación del jugador en el partido.
	 */
	private Long idParticipacion;

	/**
	 * Identificador del tipo de tarjeta asignada.
	 */
	private Long idTipo;

	/**
	 * Color de la tarjeta mostrada al jugador.
	 * Generalmente corresponde a una tarjeta amarilla o roja.
	 */
	private String color;

	/**
	 * Motivo por el cual se mostró la tarjeta al jugador.
	 */
	private String motivo;

	/**
	 * Minuto del partido en el que se mostró la tarjeta.
	 */
	private Integer minuto;

	/**
	 * Constructor vacío de la clase.
	 * Permite crear un objeto TarjetaDTO sin inicializar sus atributos.
	 */
	public TarjetaDTO() {

	}

	/**
	 * Constructor parametrizado de la clase.
	 * Permite crear un objeto TarjetaDTO inicializando todos sus atributos.
	 *
	 * @param idTarjeta Identificador único de la tarjeta.
	 * @param idParticipacion Identificador de la participación del jugador.
	 * @param idTipo Identificador del tipo de tarjeta.
	 * @param color Color de la tarjeta mostrada.
	 * @param motivo Motivo de la sanción.
	 * @param minuto Minuto del partido en el que se mostró la tarjeta.
	 */
	public TarjetaDTO(Long idTarjeta, Long idParticipacion, Long idTipo, String color, String motivo, Integer minuto) {
		super();
		this.idTarjeta = idTarjeta;
		this.idParticipacion = idParticipacion;
		this.idTipo = idTipo;
		this.color = color;
		this.motivo = motivo;
		this.minuto = minuto;
	}

	/**
	 * Obtiene el identificador de la tarjeta.
	 *
	 * @return Identificador único de la tarjeta.
	 */
	public Long getIdTarjeta() {
		return idTarjeta;
	}

	/**
	 * Modifica el identificador de la tarjeta.
	 *
	 * @param idTarjeta Nuevo identificador de la tarjeta.
	 */
	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	/**
	 * Obtiene el identificador de la participación del jugador.
	 *
	 * @return Identificador de la participación.
	 */
	public Long getIdParticipacion() {
		return idParticipacion;
	}

	/**
	 * Modifica el identificador de la participación del jugador.
	 *
	 * @param idParticipacion Nuevo identificador de la participación.
	 */
	public void setIdParticipacion(Long idParticipacion) {
		this.idParticipacion = idParticipacion;
	}

	/**
	 * Obtiene el identificador del tipo de tarjeta.
	 *
	 * @return Identificador del tipo de tarjeta.
	 */
	public Long getIdTipo() {
		return idTipo;
	}

	/**
	 * Modifica el identificador del tipo de tarjeta.
	 *
	 * @param idTipo Nuevo identificador del tipo de tarjeta.
	 */
	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	/**
	 * Obtiene el color de la tarjeta.
	 *
	 * @return Color de la tarjeta.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Modifica el color de la tarjeta.
	 *
	 * @param color Nuevo color de la tarjeta.
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Obtiene el motivo por el cual se mostró la tarjeta.
	 *
	 * @return Descripción del motivo de la sanción.
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * Modifica el motivo por el cual se mostró la tarjeta.
	 *
	 * @param motivo Nuevo motivo de la sanción.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * Obtiene el minuto en el que se mostró la tarjeta.
	 *
	 * @return Minuto del partido en el que ocurrió la sanción.
	 */
	public Integer getMinuto() {
		return minuto;
	}

	/**
	 * Modifica el minuto en el que se mostró la tarjeta.
	 *
	 * @param minuto Nuevo minuto del partido.
	 */
	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	/**
	 * Genera un código hash utilizando todos los atributos del objeto.
	 * Facilita la organización y comparación de objetos en estructuras
	 * de datos que utilizan códigos hash.
	 *
	 * @return Código hash del objeto TarjetaDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(color, idParticipacion, idTarjeta, idTipo, minuto, motivo);
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
		TarjetaDTO other = (TarjetaDTO) obj;
		return Objects.equals(color, other.color) && Objects.equals(idParticipacion, other.idParticipacion)
				&& Objects.equals(idTarjeta, other.idTarjeta) && Objects.equals(idTipo, other.idTipo)
				&& Objects.equals(minuto, other.minuto) && Objects.equals(motivo, other.motivo);
	}

	/**
	 * Devuelve una representación textual del objeto con los valores
	 * de todos sus atributos.
	 *
	 * @return Cadena de texto con la información completa de la tarjeta.
	 */
	@Override
	public String toString() {
		return "TarjetaDTO [idTarjeta=" + idTarjeta + ", idParticipacion=" + idParticipacion + ", idTipo=" + idTipo
				+ ", color=" + color + ", motivo=" + motivo + ", minuto=" + minuto + "]";
	}

}