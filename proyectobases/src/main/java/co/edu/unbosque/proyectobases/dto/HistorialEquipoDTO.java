package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase DTO que representa el historial de partidos de un equipo.
 * Almacena información sobre la fecha del encuentro, la jornada,
 * el equipo rival, la condición de local o visitante, los goles
 * anotados y recibidos, y el resultado obtenido.
 */
public class HistorialEquipoDTO {

	/** Identificador único del partido. */
	private Long idPartido;

	/** Fecha en la que se disputó el partido. */
	private LocalDate fecha;

	/** Número de la jornada en la que se jugó el partido. */
	private Integer jornada;

	/** Nombre del equipo rival. */
	private String rival;

	/** Condición del equipo durante el partido: LOCAL o VISITANTE. */
	private String condicion;

	/** Cantidad de goles anotados por el equipo. */
	private Integer golesFavor;

	/** Cantidad de goles recibidos por el equipo. */
	private Integer golesContra;

	/** Resultado del partido: G (ganado), E (empatado) o P (perdido). */
	private String resultado;

	/**
	 * Constructor vacío de la clase HistorialEquipoDTO.
	 */
	public HistorialEquipoDTO() {

	}

	/**
	 * Constructor que inicializa todos los atributos del historial del equipo.
	 *
	 * @param idPartido identificador único del partido.
	 * @param fecha fecha en la que se disputó el encuentro.
	 * @param jornada número de la jornada correspondiente.
	 * @param rival nombre del equipo contrario.
	 * @param condicion condición del equipo (LOCAL o VISITANTE).
	 * @param golesFavor cantidad de goles anotados por el equipo.
	 * @param golesContra cantidad de goles recibidos por el equipo.
	 * @param resultado resultado del partido (G, E o P).
	 */
	public HistorialEquipoDTO(Long idPartido, LocalDate fecha, Integer jornada, String rival, String condicion,
			Integer golesFavor, Integer golesContra, String resultado) {
		super();
		this.idPartido = idPartido;
		this.fecha = fecha;
		this.jornada = jornada;
		this.rival = rival;
		this.condicion = condicion;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
		this.resultado = resultado;
	}

	/**
	 * Obtiene el identificador del partido.
	 *
	 * @return identificador único del partido.
	 */
	public Long getIdPartido() {
		return idPartido;
	}

	/**
	 * Establece el identificador del partido.
	 *
	 * @param idPartido identificador único del partido.
	 */
	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	/**
	 * Obtiene la fecha del partido.
	 *
	 * @return fecha en la que se disputó el encuentro.
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha del partido.
	 *
	 * @param fecha fecha en la que se disputó el encuentro.
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene el número de la jornada.
	 *
	 * @return número de la jornada del partido.
	 */
	public Integer getJornada() {
		return jornada;
	}

	/**
	 * Establece el número de la jornada.
	 *
	 * @param jornada número de la jornada correspondiente.
	 */
	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	/**
	 * Obtiene el nombre del equipo rival.
	 *
	 * @return nombre del equipo contrario.
	 */
	public String getRival() {
		return rival;
	}

	/**
	 * Establece el nombre del equipo rival.
	 *
	 * @param rival nombre del equipo contrario.
	 */
	public void setRival(String rival) {
		this.rival = rival;
	}

	/**
	 * Obtiene la condición del equipo en el partido.
	 *
	 * @return condición del equipo: LOCAL o VISITANTE.
	 */
	public String getCondicion() {
		return condicion;
	}

	/**
	 * Establece la condición del equipo en el partido.
	 *
	 * @param condicion condición del equipo: LOCAL o VISITANTE.
	 */
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	/**
	 * Obtiene la cantidad de goles anotados por el equipo.
	 *
	 * @return número de goles a favor.
	 */
	public Integer getGolesFavor() {
		return golesFavor;
	}

	/**
	 * Establece la cantidad de goles anotados por el equipo.
	 *
	 * @param golesFavor número de goles a favor.
	 */
	public void setGolesFavor(Integer golesFavor) {
		this.golesFavor = golesFavor;
	}

	/**
	 * Obtiene la cantidad de goles recibidos por el equipo.
	 *
	 * @return número de goles en contra.
	 */
	public Integer getGolesContra() {
		return golesContra;
	}

	/**
	 * Establece la cantidad de goles recibidos por el equipo.
	 *
	 * @param golesContra número de goles en contra.
	 */
	public void setGolesContra(Integer golesContra) {
		this.golesContra = golesContra;
	}

	/**
	 * Obtiene el resultado del partido.
	 *
	 * @return resultado del encuentro: G (ganado), E (empatado) o P (perdido).
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Establece el resultado del partido.
	 *
	 * @param resultado resultado del encuentro: G, E o P.
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Calcula el código hash del objeto utilizando sus atributos.
	 *
	 * @return código hash generado a partir de los atributos de la clase.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(condicion, fecha, golesContra, golesFavor, idPartido, jornada, resultado, rival);
	}

	/**
	 * Compara este objeto con otro para determinar si son iguales.
	 * La comparación se realiza teniendo en cuenta todos los atributos
	 * de la clase.
	 *
	 * @param obj objeto con el que se desea realizar la comparación.
	 * @return true si ambos objetos tienen la misma clase y los mismos
	 *         valores en sus atributos; false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistorialEquipoDTO other = (HistorialEquipoDTO) obj;
		return Objects.equals(condicion, other.condicion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(golesContra, other.golesContra) && Objects.equals(golesFavor, other.golesFavor)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(jornada, other.jornada)
				&& Objects.equals(resultado, other.resultado) && Objects.equals(rival, other.rival);
	}

	/**
	 * Genera una representación en cadena de texto del objeto,
	 * incluyendo los valores de todos sus atributos.
	 *
	 * @return representación textual del historial del equipo.
	 */
	@Override
	public String toString() {
		return "HistorialEquipoDTO [idPartido=" + idPartido + ", fecha=" + fecha + ", jornada=" + jornada + ", rival="
				+ rival + ", condicion=" + condicion + ", golesFavor=" + golesFavor + ", golesContra=" + golesContra
				+ ", resultado=" + resultado + "]";
	}



}