package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) que representa el resultado de un partido
 * dentro de un torneo deportivo.
 *
 * Permite transportar la información correspondiente al marcador final
 * de un encuentro, incluyendo los goles anotados por el equipo local
 * y el equipo visitante.
 *
 * @author
 * @version 1.0
 */
public class ResultadoDTO {

	/**
	 * Identificador único del resultado.
	 */
	private Long idResultado;

	/**
	 * Identificador del partido al que pertenece el resultado.
	 */
	private Long idPartido;

	/**
	 * Cantidad de goles anotados por el equipo local.
	 */
	private Integer golesLocal;

	/**
	 * Cantidad de goles anotados por el equipo visitante.
	 */
	private Integer golesVisitante;

	/**
	 * Constructor vacío de la clase.
	 * Permite crear un objeto ResultadoDTO sin inicializar sus atributos.
	 */
	public ResultadoDTO() {

	}

	/**
	 * Constructor parametrizado de la clase.
	 * Permite crear un objeto ResultadoDTO inicializando todos sus atributos.
	 *
	 * @param idResultado Identificador único del resultado.
	 * @param idPartido Identificador del partido correspondiente.
	 * @param golesLocal Cantidad de goles del equipo local.
	 * @param golesVisitante Cantidad de goles del equipo visitante.
	 */
	public ResultadoDTO(Long idResultado, Long idPartido, Integer golesLocal, Integer golesVisitante) {
		super();
		this.idResultado = idResultado;
		this.idPartido = idPartido;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}

	/**
	 * Obtiene el identificador del resultado.
	 *
	 * @return Identificador único del resultado.
	 */
	public Long getIdResultado() {
		return idResultado;
	}

	/**
	 * Modifica el identificador del resultado.
	 *
	 * @param idResultado Nuevo identificador del resultado.
	 */
	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	/**
	 * Obtiene el identificador del partido.
	 *
	 * @return Identificador del partido asociado al resultado.
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
	 * Obtiene la cantidad de goles anotados por el equipo local.
	 *
	 * @return Número de goles del equipo local.
	 */
	public Integer getGolesLocal() {
		return golesLocal;
	}

	/**
	 * Modifica la cantidad de goles del equipo local.
	 *
	 * @param golesLocal Nueva cantidad de goles del equipo local.
	 */
	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	/**
	 * Obtiene la cantidad de goles anotados por el equipo visitante.
	 *
	 * @return Número de goles del equipo visitante.
	 */
	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	/**
	 * Modifica la cantidad de goles del equipo visitante.
	 *
	 * @param golesVisitante Nueva cantidad de goles del equipo visitante.
	 */
	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	/**
	 * Genera un código hash utilizando todos los atributos del objeto.
	 * Facilita la organización y comparación de objetos en estructuras
	 * de datos que utilizan códigos hash.
	 *
	 * @return Código hash del objeto ResultadoDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(golesLocal, golesVisitante, idPartido, idResultado);
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
		ResultadoDTO other = (ResultadoDTO) obj;
		return Objects.equals(golesLocal, other.golesLocal) && Objects.equals(golesVisitante, other.golesVisitante)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(idResultado, other.idResultado);
	}

	/**
	 * Devuelve una representación textual del objeto con los valores
	 * de todos sus atributos.
	 *
	 * @return Cadena de texto con la información completa del resultado.
	 */
	@Override
	public String toString() {
		return "ResultadoDTO [idResultado=" + idResultado + ", idPartido=" + idPartido + ", golesLocal=" + golesLocal
				+ ", golesVisitante=" + golesVisitante + "]";
	}

}