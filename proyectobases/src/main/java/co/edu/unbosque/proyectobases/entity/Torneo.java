package co.edu.unbosque.proyectobases.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Torneo {
	
	private Long idTorneo;
	private String nombreTorneo;
	private Integer anio;
	private Integer semestre;
	private LocalDate fechaInicioTorneo;
	private LocalDate fechaFinTorneo;
	
	public Torneo() {
		
	}

	public Torneo(Long idTorneo, String nombreTorneo, Integer anio, Integer semestre, LocalDate fechaInicioTorneo,
			LocalDate fechaFinTorneo) {
		super();
		this.idTorneo = idTorneo;
		this.nombreTorneo = nombreTorneo;
		this.anio = anio;
		this.semestre = semestre;
		this.fechaInicioTorneo = fechaInicioTorneo;
		this.fechaFinTorneo = fechaFinTorneo;
	}

	public Long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getNombreTorneo() {
		return nombreTorneo;
	}

	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public LocalDate getFechaInicioTorneo() {
		return fechaInicioTorneo;
	}

	public void setFechaInicioTorneo(LocalDate fechaInicioTorneo) {
		this.fechaInicioTorneo = fechaInicioTorneo;
	}

	public LocalDate getFechaFinTorneo() {
		return fechaFinTorneo;
	}

	public void setFechaFinTorneo(LocalDate fechaFinTorneo) {
		this.fechaFinTorneo = fechaFinTorneo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, fechaFinTorneo, fechaInicioTorneo, idTorneo, nombreTorneo, semestre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torneo other = (Torneo) obj;
		return Objects.equals(anio, other.anio) && Objects.equals(fechaFinTorneo, other.fechaFinTorneo)
				&& Objects.equals(fechaInicioTorneo, other.fechaInicioTorneo)
				&& Objects.equals(idTorneo, other.idTorneo) && Objects.equals(nombreTorneo, other.nombreTorneo)
				&& Objects.equals(semestre, other.semestre);
	}

	@Override
	public String toString() {
		return "Torneo [idTorneo=" + idTorneo + ", nombreTorneo=" + nombreTorneo + ", anio=" + anio + ", semestre="
				+ semestre + ", fechaInicioTorneo=" + fechaInicioTorneo + ", fechaFinTorneo=" + fechaFinTorneo + "]";
	}

	
}
