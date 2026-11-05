package co.edu.unbosque.proyectobases.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Inscripcion {

	private Long idEquipo;
	private Long idTorneo;
	private Long idJugador;
	private Integer dorsal;
	private LocalDate fechaInicio;
	private LocalDate fechaFin; // puede ser null
	
	public Inscripcion() {
		
	}

	public Inscripcion(Long idEquipo, Long idTorneo, Long idJugador, Integer dorsal, LocalDate fechaInicio,
			LocalDate fechaFin) {
		super();
		this.idEquipo = idEquipo;
		this.idTorneo = idTorneo;
		this.idJugador = idJugador;
		this.dorsal = dorsal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public Long getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	public Integer getDorsal() {
		return dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dorsal, fechaFin, fechaInicio, idEquipo, idJugador, idTorneo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inscripcion other = (Inscripcion) obj;
		return Objects.equals(dorsal, other.dorsal) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(idEquipo, other.idEquipo)
				&& Objects.equals(idJugador, other.idJugador) && Objects.equals(idTorneo, other.idTorneo);
	}

	@Override
	public String toString() {
		return "Inscripcion [idEquipo=" + idEquipo + ", idTorneo=" + idTorneo + ", idJugador=" + idJugador + ", dorsal="
				+ dorsal + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
}
