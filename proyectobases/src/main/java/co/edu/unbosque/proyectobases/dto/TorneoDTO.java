package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

public class TorneoDTO {

	private Long idTorneo;
	private String nombre;
	private int anio;
	private int semestre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int duracionDias; //derivado

	public TorneoDTO() {
		
	}

	public TorneoDTO(Long idTorneo, String nombre, int anio, int semestre, LocalDate fechaInicio, LocalDate fechaFin,
			int duracionDias) {
		super();
		this.idTorneo = idTorneo;
		this.nombre = nombre;
		this.anio = anio;
		this.semestre = semestre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracionDias = duracionDias;
	}

	public Long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
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

	public int getDuracionDias() {
		return duracionDias;
	}

	public void setDuracionDias(int duracionDias) {
		this.duracionDias = duracionDias;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(anio, duracionDias, fechaFin, fechaInicio, idTorneo, nombre, semestre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TorneoDTO other = (TorneoDTO) obj;
		return anio == other.anio && duracionDias == other.duracionDias && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(idTorneo, other.idTorneo)
				&& Objects.equals(nombre, other.nombre) && semestre == other.semestre;
	}

	@Override
	public String toString() {
		return "TorneoDTO [idTorneo=" + idTorneo + ", nombre=" + nombre + ", anio=" + anio + ", semestre=" + semestre
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", duracionDias=" + duracionDias + "]";
	}
	
	
}
