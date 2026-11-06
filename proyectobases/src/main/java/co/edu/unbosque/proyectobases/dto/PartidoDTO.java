package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) que representa la información de un partido
 * dentro de un torneo deportivo.
 *
 * Permite transportar los datos relacionados con la programación del partido,
 * los equipos participantes, el estadio donde se disputa y el resultado
 * obtenido por cada equipo.
 *
 * @author
 * @version 1.0
 */
public class PartidoDTO {

	/**
	 * Identificador único del partido.
	 */
	private Long idPartido;

	/**
	 * Identificador del torneo al que pertenece el partido.
	 */
	private Long idTorneo;

	/**
	 * Fecha programada para la realización del partido.
	 */
	private LocalDate fecha;

	/**
	 * Hora programada para el inicio del partido.
	 */
	private LocalTime hora;

	/**
	 * Número de la jornada del torneo en la que se disputa el partido.
	 */
	private Integer jornada;

	/**
	 * Identificador del estadio donde se realiza el partido.
	 */
	private Long idEstadio;

	/**
	 * Nombre del estadio donde se disputa el partido.
	 */
	private String nombreEstadio;

	/**
	 * Identificador del equipo que juega como local.
	 */
	private Long idLocal;

	/**
	 * Nombre del equipo que juega como local.
	 */
	private String nombreLocal;

	/**
	 * Identificador del equipo que juega como visitante.
	 */
	private Long idVisitante;

	/**
	 * Nombre del equipo que juega como visitante.
	 */
	private String nombreVisitante;

	/**
	 * Cantidad de goles anotados por el equipo local.
	 * Su valor es null cuando el partido todavía no se ha jugado.
	 */
	private Integer golesLocal;

	/**
	 * Cantidad de goles anotados por el equipo visitante.
	 * Su valor es null cuando el partido todavía no se ha jugado.
	 */
	private Integer golesVisitante;

	/**
	 * Constructor vacío de la clase.
	 * Permite crear un objeto PartidoDTO sin inicializar sus atributos.
	 */
	public PartidoDTO() {

	}

	/**
	 * Constructor parametrizado de la clase.
	 * Permite crear un objeto PartidoDTO inicializando todos sus atributos.
	 *
	 * @param idPartido Identificador único del partido.
	 * @param idTorneo Identificador del torneo.
	 * @param fecha Fecha programada del partido.
	 * @param hora Hora programada del partido.
	 * @param jornada Número de la jornada correspondiente.
	 * @param idEstadio Identificador del estadio.
	 * @param nombreEstadio Nombre del estadio.
	 * @param idLocal Identificador del equipo local.
	 * @param nombreLocal Nombre del equipo local.
	 * @param idVisitante Identificador del equipo visitante.
	 * @param nombreVisitante Nombre del equipo visitante.
	 * @param golesLocal Goles anotados por el equipo local.
	 * @param golesVisitante Goles anotados por el equipo visitante.
	 */
	public PartidoDTO(Long idPartido, Long idTorneo, LocalDate fecha, LocalTime hora, Integer jornada, Long idEstadio,
			String nombreEstadio, Long idLocal, String nombreLocal, Long idVisitante, String nombreVisitante,
			Integer golesLocal, Integer golesVisitante) {
		super();
		this.idPartido = idPartido;
		this.idTorneo = idTorneo;
		this.fecha = fecha;
		this.hora = hora;
		this.jornada = jornada;
		this.idEstadio = idEstadio;
		this.nombreEstadio = nombreEstadio;
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.idVisitante = idVisitante;
		this.nombreVisitante = nombreVisitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}

	/**
	 * Obtiene el identificador del partido.
	 *
	 * @return Identificador único del partido.
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
	 * Obtiene el identificador del torneo.
	 *
	 * @return Identificador del torneo.
	 */
	public Long getIdTorneo() {
		return idTorneo;
	}

	/**
	 * Modifica el identificador del torneo.
	 *
	 * @param idTorneo Nuevo identificador del torneo.
	 */
	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	/**
	 * Obtiene la fecha programada del partido.
	 *
	 * @return Fecha del partido.
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Modifica la fecha programada del partido.
	 *
	 * @param fecha Nueva fecha del partido.
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene la hora programada del partido.
	 *
	 * @return Hora del partido.
	 */
	public LocalTime getHora() {
		return hora;
	}

	/**
	 * Modifica la hora programada del partido.
	 *
	 * @param hora Nueva hora del partido.
	 */
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	/**
	 * Obtiene el número de la jornada.
	 *
	 * @return Número de la jornada del torneo.
	 */
	public Integer getJornada() {
		return jornada;
	}

	/**
	 * Modifica el número de la jornada.
	 *
	 * @param jornada Nuevo número de jornada.
	 */
	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	/**
	 * Obtiene el identificador del estadio.
	 *
	 * @return Identificador del estadio.
	 */
	public Long getIdEstadio() {
		return idEstadio;
	}

	/**
	 * Modifica el identificador del estadio.
	 *
	 * @param idEstadio Nuevo identificador del estadio.
	 */
	public void setIdEstadio(Long idEstadio) {
		this.idEstadio = idEstadio;
	}

	/**
	 * Obtiene el nombre del estadio.
	 *
	 * @return Nombre del estadio.
	 */
	public String getNombreEstadio() {
		return nombreEstadio;
	}

	/**
	 * Modifica el nombre del estadio.
	 *
	 * @param nombreEstadio Nuevo nombre del estadio.
	 */
	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	/**
	 * Obtiene el identificador del equipo local.
	 *
	 * @return Identificador del equipo local.
	 */
	public Long getIdLocal() {
		return idLocal;
	}

	/**
	 * Modifica el identificador del equipo local.
	 *
	 * @param idLocal Nuevo identificador del equipo local.
	 */
	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	/**
	 * Obtiene el nombre del equipo local.
	 *
	 * @return Nombre del equipo local.
	 */
	public String getNombreLocal() {
		return nombreLocal;
	}

	/**
	 * Modifica el nombre del equipo local.
	 *
	 * @param nombreLocal Nuevo nombre del equipo local.
	 */
	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	/**
	 * Obtiene el identificador del equipo visitante.
	 *
	 * @return Identificador del equipo visitante.
	 */
	public Long getIdVisitante() {
		return idVisitante;
	}

	/**
	 * Modifica el identificador del equipo visitante.
	 *
	 * @param idVisitante Nuevo identificador del equipo visitante.
	 */
	public void setIdVisitante(Long idVisitante) {
		this.idVisitante = idVisitante;
	}

	/**
	 * Obtiene el nombre del equipo visitante.
	 *
	 * @return Nombre del equipo visitante.
	 */
	public String getNombreVisitante() {
		return nombreVisitante;
	}

	/**
	 * Modifica el nombre del equipo visitante.
	 *
	 * @param nombreVisitante Nuevo nombre del equipo visitante.
	 */
	public void setNombreVisitante(String nombreVisitante) {
		this.nombreVisitante = nombreVisitante;
	}

	/**
	 * Obtiene la cantidad de goles del equipo local.
	 *
	 * @return Goles anotados por el equipo local o null si no se ha jugado.
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
	 * Obtiene la cantidad de goles del equipo visitante.
	 *
	 * @return Goles anotados por el equipo visitante o null si no se ha jugado.
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
	 * Genera un código hash utilizando los atributos del objeto.
	 * Facilita la organización y comparación de objetos en estructuras
	 * de datos que utilizan códigos hash.
	 *
	 * @return Código hash del objeto PartidoDTO.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fecha, golesLocal, golesVisitante, hora, idEstadio, idLocal, idPartido, idTorneo,
				idVisitante, jornada, nombreEstadio, nombreLocal, nombreVisitante);
	}

	/**
	 * Compara este objeto con otro para determinar si son iguales.
	 * Verifica si ambos objetos son la misma instancia, si el objeto recibido
	 * es nulo y si pertenecen a la misma clase. Finalmente compara todos
	 * sus atributos.
	 *
	 * @param obj Objeto con el que se desea realizar la comparación.
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
		PartidoDTO other = (PartidoDTO) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(golesLocal, other.golesLocal)
				&& Objects.equals(golesVisitante, other.golesVisitante) && Objects.equals(hora, other.hora)
				&& Objects.equals(idEstadio, other.idEstadio) && Objects.equals(idLocal, other.idLocal)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(idTorneo, other.idTorneo)
				&& Objects.equals(idVisitante, other.idVisitante) && Objects.equals(jornada, other.jornada)
				&& Objects.equals(nombreEstadio, other.nombreEstadio) && Objects.equals(nombreLocal, other.nombreLocal)
				&& Objects.equals(nombreVisitante, other.nombreVisitante);
	}

	/**
	 * Devuelve una representación textual del objeto con los valores
	 * de todos sus atributos.
	 *
	 * @return Cadena de texto con la información completa del partido.
	 */
	@Override
	public String toString() {
		return "PartidoDTO [idPartido=" + idPartido + ", idTorneo=" + idTorneo + ", fecha=" + fecha + ", hora=" + hora
				+ ", jornada=" + jornada + ", idEstadio=" + idEstadio + ", nombreEstadio=" + nombreEstadio
				+ ", idLocal=" + idLocal + ", nombreLocal=" + nombreLocal + ", idVisitante=" + idVisitante
				+ ", nombreVisitante=" + nombreVisitante + ", golesLocal=" + golesLocal + ", golesVisitante="
				+ golesVisitante + "]";
	}

}