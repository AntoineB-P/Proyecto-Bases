package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) que representa la información de la
 * participación de un jugador en un partido.
 *
 * Permite transportar los datos relacionados con un jugador y su participación
 * en un encuentro deportivo, incluyendo si fue titular y los minutos jugados.
 *
 * @author
 * @version 1.0
 */
public class ParticipacionDTO {

	/**
	 * Identificador único de la participación.
	 */
	private Long idParticipacion;

	/**
	 * Identificador del jugador que participa en el partido.
	 */
	private Long idJugador;

	/**
	 * Nombre completo del jugador que participa en el partido.
	 */
	private String nombreJugador;

	/**
	 * Identificador del partido en el que participa el jugador.
	 */
	private Long idPartido;

	/**
	 * Indica si el jugador inició el partido como titular.
	 * True significa que fue titular y false que fue suplente.
	 */
	private Boolean titular;

	/**
	 * Cantidad de minutos que el jugador disputó durante el partido.
	 */
	private Integer minutosJugados;

	/**
	 * Constructor vacío de la clase.
	 * Permite crear un objeto ParticipacionDTO sin inicializar sus atributos.
	 */
	public ParticipacionDTO() {

	}

	/**
	 * Constructor parametrizado de la clase.
	 * Permite crear un objeto ParticipacionDTO inicializando todos sus atributos.
	 *
	 * @param idParticipacion Identificador único de la participación.
	 * @param idJugador Identificador del jugador.
	 * @param nombreJugador Nombre del jugador.
	 * @param idPartido Identificador del partido.
	 * @param titular Indica si el jugador fue titular.
	 * @param minutosJugados Minutos disputados por el jugador.
	 */
	public ParticipacionDTO(Long idParticipacion, Long idJugador, String nombreJugador, Long idPartido, Boolean titular,
			Integer minutosJugados) {
		super();
		this.idParticipacion = idParticipacion;
		this.idJugador = idJugador;
		this.nombreJugador = nombreJugador;
		this.idPartido = idPartido;
		this.titular = titular;
		this.minutosJugados = minutosJugados;
	}

	/**
	 * Obtiene el identificador de la participación.
	 *
	 * @return Identificador de la participación.
	 */
	public Long getIdParticipacion() {
		return idParticipacion;
	}

	/**
	 * Modifica el identificador de la participación.
	 *
	 * @param idParticipacion Nuevo identificador de la participación.
	 */
	public void setIdParticipacion(Long idParticipacion) {
		this.idParticipacion = idParticipacion;
	}

	/**
	 * Obtiene el identificador del jugador.
	 *
	 * @return Identificador del jugador.
	 */
	public Long getIdJugador() {
		return idJugador;
	}

	/**
	 * Modifica el identificador del jugador.
	 *
	 * @param idJugador Nuevo identificador del jugador.
	 */
	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	/**
	 * Obtiene el nombre del jugador.
	 *
	 * @return Nombre del jugador.
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}

	/**
	 * Modifica el nombre del jugador.
	 *
	 * @param nombreJugador Nuevo nombre del jugador.
	 */
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	/**
	 * Obtiene el identificador del partido.
	 *
	 * @return Identificador del partido.
	 */
	public Long getIdPartido() {
		return idPartido;
	}

	/**
	 * Modifica el identificador del partido.
	 *
	 * @param idPartido Nuevo identificador del partido.
	 */
	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	/**
	 * Obtiene el estado de titularidad del jugador.
	 *
	 * @return true si el jugador fue titular, false si fue suplente.
	 */
	public Boolean getTitular() {
		return titular;
	}

	/**
	 * Modifica el estado de titularidad del jugador.
	 *
	 * @param titular Nuevo estado de titularidad.
	 */
	public void setTitular(Boolean titular) {
		this.titular = titular;
	}

	/**
	 * Obtiene la cantidad de minutos jugados.
	 *
	 * @return Minutos disputados por el jugador.
	 */
	public Integer getMinutosJugados() {
		return minutosJugados;
	}

	/**
	 * Modifica la cantidad de minutos jugados.
	 *
	 * @param minutosJugados Nueva cantidad de minutos disputados.
	 */
	public void setMinutosJugados(Integer minutosJugados) {
		this.minutosJugados = minutosJugados;
	}

	/**
	 * Genera un código hash a partir de los atributos del objeto.
	 * Se utiliza para facilitar la comparación y organización de objetos
	 * en estructuras de datos basadas en hash.
	 *
	 * @return Código hash del objeto ParticipacionDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idJugador, idParticipacion, idPartido, minutosJugados, nombreJugador, titular);
	}

	/**
	 * Compara este objeto con otro para determinar si ambos son iguales.
	 * Primero verifica si se trata del mismo objeto, si el objeto recibido
	 * es nulo y si ambos pertenecen a la misma clase. Posteriormente compara
	 * todos los atributos.
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
		ParticipacionDTO other = (ParticipacionDTO) obj;
		return Objects.equals(idJugador, other.idJugador) && Objects.equals(idParticipacion, other.idParticipacion)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(minutosJugados, other.minutosJugados)
				&& Objects.equals(nombreJugador, other.nombreJugador) && Objects.equals(titular, other.titular);
	}

	/**
	 * Devuelve una representación textual del objeto con los valores
	 * de todos sus atributos.
	 *
	 * @return Cadena de texto con la información de la participación.
	 */
	@Override
	public String toString() {
		return "ParticipacionDTO [idParticipacion=" + idParticipacion + ", idJugador=" + idJugador + ", nombreJugador="
				+ nombreJugador + ", idPartido=" + idPartido + ", titular=" + titular + ", minutosJugados="
				+ minutosJugados + "]";
	}

}