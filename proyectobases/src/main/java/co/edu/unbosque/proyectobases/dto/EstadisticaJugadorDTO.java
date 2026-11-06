package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) encargada de transportar la información
 * estadística relacionada con los jugadores de fútbol.
 *
 * Contiene los datos del jugador y sus estadísticas dentro de las
 * competiciones, incluyendo partidos jugados, goles anotados,
 * tarjetas amarillas y tarjetas rojas recibidas.
 *
 * Implementa los métodos equals(), hashCode() y toString() para facilitar
 * la comparación y representación textual de los objetos.
 *
 * @author
 * @version 1.0
 */
public class EstadisticaJugadorDTO {

	/** Identificador único del jugador. */
	private Long idJugador;

	/** Nombre completo del jugador. */
	private String nombreJugador;

	/** Cantidad de partidos disputados por el jugador. */
	private Integer partidosJugados;

	/** Cantidad de goles anotados por el jugador. */
	private Integer goles;

	/** Cantidad de tarjetas amarillas recibidas por el jugador. */
	private Integer amarillas;

	/** Cantidad de tarjetas rojas recibidas por el jugador. */
	private Integer rojas;

	/**
	 * Constructor vacío de la clase EstadisticaJugadorDTO.
	 * Permite crear un objeto sin inicializar sus atributos.
	 */
	public EstadisticaJugadorDTO() {

	}

	/**
	 * Constructor parametrizado que permite inicializar todos los atributos
	 * de un objeto EstadisticaJugadorDTO.
	 *
	 * @param idJugador identificador único del jugador.
	 * @param nombreJugador nombre completo del jugador.
	 * @param partidosJugados cantidad de partidos disputados.
	 * @param goles cantidad de goles anotados.
	 * @param amarillas cantidad de tarjetas amarillas recibidas.
	 * @param rojas cantidad de tarjetas rojas recibidas.
	 */
	public EstadisticaJugadorDTO(Long idJugador, String nombreJugador, Integer partidosJugados, Integer goles,
			Integer amarillas, Integer rojas, String nombreEquipo) {
		super();
		this.idJugador = idJugador;
		this.nombreJugador = nombreJugador;
		this.partidosJugados = partidosJugados;
		this.goles = goles;
		this.amarillas = amarillas;
		this.rojas = rojas;
		this.nombreEquipo = nombreEquipo;
	}

	/**
	 * Obtiene el identificador del jugador.
	 *
	 * @return identificador único del jugador.
	 */
	public Long getIdJugador() {
		return idJugador;
	}

	/**
	 * Modifica el identificador del jugador.
	 *
	 * @param idJugador nuevo identificador del jugador.
	 */
	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	/**
	 * Obtiene el nombre del jugador.
	 *
	 * @return nombre completo del jugador.
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}

	/**
	 * Modifica el nombre del jugador.
	 *
	 * @param nombreJugador nuevo nombre del jugador.
	 */
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	/**
	 * Obtiene la cantidad de partidos jugados por el jugador.
	 *
	 * @return número de partidos disputados.
	 */
	public Integer getPartidosJugados() {
		return partidosJugados;
	}

	/**
	 * Modifica la cantidad de partidos jugados por el jugador.
	 *
	 * @param partidosJugados nuevo número de partidos disputados.
	 */
	public void setPartidosJugados(Integer partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	/**
	 * Obtiene la cantidad de goles anotados por el jugador.
	 *
	 * @return número de goles anotados.
	 */
	public Integer getGoles() {
		return goles;
	}

	/**
	 * Modifica la cantidad de goles anotados por el jugador.
	 *
	 * @param goles nueva cantidad de goles anotados.
	 */
	public void setGoles(Integer goles) {
		this.goles = goles;
	}

	/**
	 * Obtiene la cantidad de tarjetas amarillas recibidas por el jugador.
	 *
	 * @return número de tarjetas amarillas.
	 */
	public Integer getAmarillas() {
		return amarillas;
	}

	/**
	 * Modifica la cantidad de tarjetas amarillas recibidas por el jugador.
	 *
	 * @param amarillas nueva cantidad de tarjetas amarillas.
	 */
	public void setAmarillas(Integer amarillas) {
		this.amarillas = amarillas;
	}

	/**
	 * Obtiene la cantidad de tarjetas rojas recibidas por el jugador.
	 *
	 * @return número de tarjetas rojas.
	 */
	public Integer getRojas() {
		return rojas;
	}

	/**
	 * Modifica la cantidad de tarjetas rojas recibidas por el jugador.
	 *
	 * @param rojas nueva cantidad de tarjetas rojas.
	 */
	public void setRojas(Integer rojas) {
		this.rojas = rojas;
	}

	/**
	 * Genera un código hash utilizando todos los atributos de la clase.
	 *
	 * @return código hash correspondiente al objeto EstadisticaJugadorDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(amarillas, goles, idJugador, nombreEquipo, nombreJugador, partidosJugados, rojas);
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
		EstadisticaJugadorDTO other = (EstadisticaJugadorDTO) obj;
		return Objects.equals(amarillas, other.amarillas) && Objects.equals(goles, other.goles)
				&& Objects.equals(idJugador, other.idJugador) && Objects.equals(nombreEquipo, other.nombreEquipo)
				&& Objects.equals(nombreJugador, other.nombreJugador)
				&& Objects.equals(partidosJugados, other.partidosJugados) && Objects.equals(rojas, other.rojas);
	}

	/**
	 * Genera una representación textual del objeto EstadisticaJugadorDTO.
	 * Incluye los valores de todos sus atributos.
	 *
	 * @return cadena de texto con la información estadística del jugador.
	 */
	@Override
	public String toString() {
		return "EstadisticaJugadorDTO [idJugador=" + idJugador + ", nombreJugador=" + nombreJugador
				+ ", partidosJugados=" + partidosJugados + ", goles=" + goles + ", amarillas=" + amarillas + ", rojas="
				+ rojas + ", nombreEquipo=" + nombreEquipo + "]";
	}

}