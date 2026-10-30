package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class PartidoDTO {
	
	private Long idPartido;
	private Long idTorneo;
	private LocalDate fecha;
	private LocalTime hora;
	private Integer jornada;
	private Long idEstadio;
	private String nombreEstadio;
	private Long idLocal;
	private String nombreLocal;
	private Long idVisitante;
	private String nombreVisitante;
	private Integer golesLocal;      // null si no se ha jugado
	private Integer golesVisitante;  // null si no se ha jugado
	
	public PartidoDTO() {
		
	}

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

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public Long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Integer getJornada() {
		return jornada;
	}

	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	public Long getIdEstadio() {
		return idEstadio;
	}

	public void setIdEstadio(Long idEstadio) {
		this.idEstadio = idEstadio;
	}

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public Long getIdVisitante() {
		return idVisitante;
	}

	public void setIdVisitante(Long idVisitante) {
		this.idVisitante = idVisitante;
	}

	public String getNombreVisitante() {
		return nombreVisitante;
	}

	public void setNombreVisitante(String nombreVisitante) {
		this.nombreVisitante = nombreVisitante;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, golesLocal, golesVisitante, hora, idEstadio, idLocal, idPartido, idTorneo,
				idVisitante, jornada, nombreEstadio, nombreLocal, nombreVisitante);
	}

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

	@Override
	public String toString() {
		return "PartidoDTO [idPartido=" + idPartido + ", idTorneo=" + idTorneo + ", fecha=" + fecha + ", hora=" + hora
				+ ", jornada=" + jornada + ", idEstadio=" + idEstadio + ", nombreEstadio=" + nombreEstadio
				+ ", idLocal=" + idLocal + ", nombreLocal=" + nombreLocal + ", idVisitante=" + idVisitante
				+ ", nombreVisitante=" + nombreVisitante + ", golesLocal=" + golesLocal + ", golesVisitante="
				+ golesVisitante + "]";
	}



}
