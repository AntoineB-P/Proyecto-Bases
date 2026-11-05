package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) encargada de transportar la información
 * relacionada con los goles registrados durante un partido de fútbol.
 *
 * Contiene los datos del gol, incluyendo su identificador, la participación
 * del jugador que lo realizó, el tipo de gol y el minuto en el que fue anotado.
 *
 * Implementa los métodos equals(), hashCode() y toString() para facilitar
 * la comparación y representación textual de los objetos.
 *
 * @author
 * @version 1.0
 */
public class GolDTO {

	/** Identificador único del gol. */
	private Long idGol;

	/** Identificador de la participación del jugador que anotó el gol. */
	private Long idParticipacion;

	/** Tipo de gol registrado, por ejemplo, penalti, autogol o gol de jugada. */
	private String tipo;

	/** Minuto del partido en el que se anotó el gol. */
	private Integer minuto;

	/**
	 * Constructor vacío de la clase GolDTO.
	 * Permite crear un objeto sin inicializar sus atributos.
	 */
	public GolDTO() {

	}

	/**
	 * Constructor parametrizado que permite inicializar todos los atributos
	 * de un objeto GolDTO.
	 *
	 * @param idGol identificador único del gol.
	 * @param idParticipacion identificador de la participación del jugador.
	 * @param tipo tipo de gol registrado.
	 * @param minuto minuto del partido en el que se anotó el gol.
	 */
	public GolDTO(Long idGol, Long idParticipacion, String tipo, Integer minuto) {
		super();
		this.idGol = idGol;
		this.idParticipacion = idParticipacion;
		this.tipo = tipo;
		this.minuto = minuto;
	}

	/**
	 * Obtiene el identificador del gol.
	 *
	 * @return identificador único del gol.
	 */
	public Long getIdGol() {
		return idGol;
	}

	/**
	 * Modifica el identificador del gol.
	 *
	 * @param idGol nuevo identificador del gol.
	 */
	public void setIdGol(Long idGol) {
		this.idGol = idGol;
	}

	/**
	 * Obtiene el identificador de la participación del jugador.
	 *
	 * @return identificador de la participación correspondiente.
	 */
	public Long getIdParticipacion() {
		return idParticipacion;
	}

	/**
	 * Modifica el identificador de la participación del jugador.
	 *
	 * @param idParticipacion nuevo identificador de participación.
	 */
	public void setIdParticipacion(Long idParticipacion) {
		this.idParticipacion = idParticipacion;
	}

	/**
	 * Obtiene el tipo de gol registrado.
	 *
	 * @return tipo de gol.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Modifica el tipo de gol registrado.
	 *
	 * @param tipo nuevo tipo de gol.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtiene el minuto en el que se anotó el gol.
	 *
	 * @return minuto del partido correspondiente al gol.
	 */
	public Integer getMinuto() {
		return minuto;
	}

	/**
	 * Modifica el minuto en el que se anotó el gol.
	 *
	 * @param minuto nuevo minuto de anotación.
	 */
	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	/**
	 * Genera un código hash utilizando todos los atributos de la clase.
	 *
	 * @return código hash correspondiente al objeto GolDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idGol, idParticipacion, minuto, tipo);
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
		GolDTO other = (GolDTO) obj;
		return Objects.equals(idGol, other.idGol) && Objects.equals(idParticipacion, other.idParticipacion)
				&& Objects.equals(minuto, other.minuto) && Objects.equals(tipo, other.tipo);
	}

	/**
	 * Genera una representación textual del objeto GolDTO.
	 * Incluye los valores de todos sus atributos.
	 *
	 * @return cadena de texto con la información completa del gol.
	 */
	@Override
	public String toString() {
		return "GolDTO [idGol=" + idGol + ", idParticipacion=" + idParticipacion + ", tipo=" + tipo + ", minuto="
				+ minuto + "]";
	}

}