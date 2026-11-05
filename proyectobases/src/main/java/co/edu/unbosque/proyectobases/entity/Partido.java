package co.edu.unbosque.proyectobases.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Partido {
	
	private Long idPartido;
	private LocalDate fecha;
	private String hora; // formato "HH:MM"
	private Integer jornada;
	private Long idTorneo;
	private Long idEstadio;
	private Long idLocal;
	private Long idVisitante;
	
	public Partido() {
		
	}

	public Partido(Long idPartido, LocalDate fecha, String hora, Integer jornada, Long idTorneo, Long idEstadio,
			Long idLocal, Long idVisitante) {
		super();
		this.idPartido = idPartido;
		this.fecha = fecha;
		this.hora = hora;
		this.jornada = jornada;
		this.idTorneo = idTorneo;
		this.idEstadio = idEstadio;
		this.idLocal = idLocal;
		this.idVisitante = idVisitante;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getJornada() {
		return jornada;
	}

	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	public Long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public Long getIdEstadio() {
		return idEstadio;
	}

	public void setIdEstadio(Long idEstadio) {
		this.idEstadio = idEstadio;
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	public Long getIdVisitante() {
		return idVisitante;
	}

	public void setIdVisitante(Long idVisitante) {
		this.idVisitante = idVisitante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, hora, idEstadio, idLocal, idPartido, idTorneo, idVisitante, jornada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora)
				&& Objects.equals(idEstadio, other.idEstadio) && Objects.equals(idLocal, other.idLocal)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(idTorneo, other.idTorneo)
				&& Objects.equals(idVisitante, other.idVisitante) && Objects.equals(jornada, other.jornada);
	}

	@Override
	public String toString() {
		return "Partido [idPartido=" + idPartido + ", fecha=" + fecha + ", hora=" + hora + ", jornada=" + jornada
				+ ", idTorneo=" + idTorneo + ", idEstadio=" + idEstadio + ", idLocal=" + idLocal + ", idVisitante="
				+ idVisitante + "]";
	}
	
	

}
