package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

public class InscripcionDTO {
	
	private Long idEquipo;
	private String nombreEquipo;
	private Long idTorneo;
	private Long idJugador;
	private String nombreJugador;
	private Integer dorsal;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public InscripcionDTO() {
		
	}

	public InscripcionDTO(Long idEquipo, String nombreEquipo, Long idTorneo, Long idJugador, String nombreJugador,
			Integer dorsal, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
		this.idTorneo = idTorneo;
		this.idJugador = idJugador;
		this.nombreJugador = nombreJugador;
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

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
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

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
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
		return Objects.hash(dorsal, fechaFin, fechaInicio, idEquipo, idJugador, idTorneo, nombreEquipo, nombreJugador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InscripcionDTO other = (InscripcionDTO) obj;
		return Objects.equals(dorsal, other.dorsal) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(idEquipo, other.idEquipo)
				&& Objects.equals(idJugador, other.idJugador) && Objects.equals(idTorneo, other.idTorneo)
				&& Objects.equals(nombreEquipo, other.nombreEquipo)
				&& Objects.equals(nombreJugador, other.nombreJugador);
	}

	
}
