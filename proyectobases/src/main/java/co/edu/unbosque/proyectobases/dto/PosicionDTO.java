package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) que representa la posición de un equipo
 * dentro de la tabla de clasificación de un torneo deportivo.
 *
 * Permite transportar la información estadística de cada equipo, incluyendo
 * los partidos disputados, resultados obtenidos, goles anotados y recibidos,
 * diferencia de goles y puntos acumulados.
 *
 * @author
 * @version 1.0
 */
public class PosicionDTO {

	/**
	 * Identificador único del equipo.
	 */
	private Long idEquipo;

	/**
	 * Nombre del equipo que ocupa una posición en la tabla.
	 */
	private String equipo;

	/**
	 * Cantidad total de partidos que ha disputado el equipo.
	 */
	private Integer partidosJugados;

	/**
	 * Cantidad de partidos que el equipo ha ganado.
	 */
	private Integer ganados;

	/**
	 * Cantidad de partidos que el equipo ha empatado.
	 */
	private Integer empatados;

	/**
	 * Cantidad de partidos que el equipo ha perdido.
	 */
	private Integer perdidos;

	/**
	 * Cantidad total de goles anotados por el equipo.
	 */
	private Integer golesFavor;

	/**
	 * Cantidad total de goles recibidos por el equipo.
	 */
	private Integer golesContra;

	/**
	 * Diferencia entre los goles a favor y los goles en contra.
	 */
	private Integer diferencia;

	/**
	 * Cantidad total de puntos acumulados por el equipo en el torneo.
	 */
	private Integer puntos;

	/**
	 * Constructor vacío de la clase.
	 * Permite crear un objeto PosicionDTO sin inicializar sus atributos.
	 */
	public PosicionDTO() {

	}

	/**
	 * Constructor parametrizado de la clase.
	 * Permite crear un objeto PosicionDTO inicializando todos sus atributos
	 * con la información estadística del equipo.
	 *
	 * @param idEquipo Identificador único del equipo.
	 * @param equipo Nombre del equipo.
	 * @param partidosJugados Cantidad de partidos disputados.
	 * @param ganados Cantidad de partidos ganados.
	 * @param empatados Cantidad de partidos empatados.
	 * @param perdidos Cantidad de partidos perdidos.
	 * @param golesFavor Cantidad de goles anotados.
	 * @param golesContra Cantidad de goles recibidos.
	 * @param diferencia Diferencia entre goles a favor y goles en contra.
	 * @param puntos Total de puntos acumulados.
	 */
	public PosicionDTO(Long idEquipo, String equipo, Integer partidosJugados, Integer ganados, Integer empatados,
			Integer perdidos, Integer golesFavor, Integer golesContra, Integer diferencia, Integer puntos) {
		super();
		this.idEquipo = idEquipo;
		this.equipo = equipo;
		this.partidosJugados = partidosJugados;
		this.ganados = ganados;
		this.empatados = empatados;
		this.perdidos = perdidos;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
		this.diferencia = diferencia;
		this.puntos = puntos;
	}

	/**
	 * Obtiene el identificador del equipo.
	 *
	 * @return Identificador único del equipo.
	 */
	public Long getIdEquipo() {
		return idEquipo;
	}

	/**
	 * Modifica el identificador del equipo.
	 *
	 * @param idEquipo Nuevo identificador del equipo.
	 */
	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	/**
	 * Obtiene el nombre del equipo.
	 *
	 * @return Nombre del equipo.
	 */
	public String getEquipo() {
		return equipo;
	}

	/**
	 * Modifica el nombre del equipo.
	 *
	 * @param equipo Nuevo nombre del equipo.
	 */
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	/**
	 * Obtiene la cantidad de partidos jugados por el equipo.
	 *
	 * @return Total de partidos disputados.
	 */
	public Integer getPartidosJugados() {
		return partidosJugados;
	}

	/**
	 * Modifica la cantidad de partidos jugados por el equipo.
	 *
	 * @param partidosJugados Nueva cantidad de partidos disputados.
	 */
	public void setPartidosJugados(Integer partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	/**
	 * Obtiene la cantidad de partidos ganados.
	 *
	 * @return Total de partidos ganados.
	 */
	public Integer getGanados() {
		return ganados;
	}

	/**
	 * Modifica la cantidad de partidos ganados.
	 *
	 * @param ganados Nueva cantidad de partidos ganados.
	 */
	public void setGanados(Integer ganados) {
		this.ganados = ganados;
	}

	/**
	 * Obtiene la cantidad de partidos empatados.
	 *
	 * @return Total de partidos empatados.
	 */
	public Integer getEmpatados() {
		return empatados;
	}

	/**
	 * Modifica la cantidad de partidos empatados.
	 *
	 * @param empatados Nueva cantidad de partidos empatados.
	 */
	public void setEmpatados(Integer empatados) {
		this.empatados = empatados;
	}

	/**
	 * Obtiene la cantidad de partidos perdidos.
	 *
	 * @return Total de partidos perdidos.
	 */
	public Integer getPerdidos() {
		return perdidos;
	}

	/**
	 * Modifica la cantidad de partidos perdidos.
	 *
	 * @param perdidos Nueva cantidad de partidos perdidos.
	 */
	public void setPerdidos(Integer perdidos) {
		this.perdidos = perdidos;
	}

	/**
	 * Obtiene la cantidad de goles anotados por el equipo.
	 *
	 * @return Total de goles a favor.
	 */
	public Integer getGolesFavor() {
		return golesFavor;
	}

	/**
	 * Modifica la cantidad de goles anotados por el equipo.
	 *
	 * @param golesFavor Nueva cantidad de goles a favor.
	 */
	public void setGolesFavor(Integer golesFavor) {
		this.golesFavor = golesFavor;
	}

	/**
	 * Obtiene la cantidad de goles recibidos por el equipo.
	 *
	 * @return Total de goles en contra.
	 */
	public Integer getGolesContra() {
		return golesContra;
	}

	/**
	 * Modifica la cantidad de goles recibidos por el equipo.
	 *
	 * @param golesContra Nueva cantidad de goles en contra.
	 */
	public void setGolesContra(Integer golesContra) {
		this.golesContra = golesContra;
	}

	/**
	 * Obtiene la diferencia de goles del equipo.
	 *
	 * @return Diferencia entre goles a favor y goles en contra.
	 */
	public Integer getDiferencia() {
		return diferencia;
	}

	/**
	 * Modifica la diferencia de goles del equipo.
	 *
	 * @param diferencia Nueva diferencia de goles.
	 */
	public void setDiferencia(Integer diferencia) {
		this.diferencia = diferencia;
	}

	/**
	 * Obtiene la cantidad de puntos acumulados por el equipo.
	 *
	 * @return Total de puntos obtenidos en el torneo.
	 */
	public Integer getPuntos() {
		return puntos;
	}

	/**
	 * Modifica la cantidad de puntos acumulados por el equipo.
	 *
	 * @param puntos Nuevo total de puntos del equipo.
	 */
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	/**
	 * Genera un código hash utilizando todos los atributos del objeto.
	 * Facilita la organización y comparación de objetos en estructuras
	 * de datos que utilizan códigos hash.
	 *
	 * @return Código hash del objeto PosicionDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(diferencia, empatados, equipo, ganados, golesContra, golesFavor, idEquipo, partidosJugados,
				perdidos, puntos);
	}

	/**
	 * Compara este objeto con otro para determinar si ambos son iguales.
	 * Primero verifica si se trata de la misma instancia, si el objeto recibido
	 * es nulo y si pertenece a la misma clase. Finalmente compara todos
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
		PosicionDTO other = (PosicionDTO) obj;
		return Objects.equals(diferencia, other.diferencia) && Objects.equals(empatados, other.empatados)
				&& Objects.equals(equipo, other.equipo) && Objects.equals(ganados, other.ganados)
				&& Objects.equals(golesContra, other.golesContra) && Objects.equals(golesFavor, other.golesFavor)
				&& Objects.equals(idEquipo, other.idEquipo) && Objects.equals(partidosJugados, other.partidosJugados)
				&& Objects.equals(perdidos, other.perdidos) && Objects.equals(puntos, other.puntos);
	}

	/**
	 * Devuelve una representación textual del objeto con los valores
	 * de todos sus atributos.
	 *
	 * @return Cadena de texto con la información estadística del equipo.
	 */
	@Override
	public String toString() {
		return "PosicionDTO [idEquipo=" + idEquipo + ", equipo=" + equipo + ", partidosJugados=" + partidosJugados
				+ ", ganados=" + ganados + ", empatados=" + empatados + ", perdidos=" + perdidos + ", golesFavor="
				+ golesFavor + ", golesContra=" + golesContra + ", diferencia=" + diferencia + ", puntos=" + puntos
				+ "]";
	}

}