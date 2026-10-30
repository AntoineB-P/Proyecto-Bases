package co.edu.unbosque.proyectobases.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Equipo {
	
	private Long idEquipo;
	private String nombreEquipo;
	private LocalDate fechaFundacion;
	private String ciudadEquipo;
	private Long idTecnico;
	private Long idSede;
	
	public Equipo(){
		
	}
	
	public Equipo(Long idEquipo, String nombreEquipo, LocalDate fechaFundacion, String ciudadEquipo, Long idTecnico,
			Long idSede) {
		super();
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
		this.fechaFundacion = fechaFundacion;
		this.ciudadEquipo = ciudadEquipo;
		this.idTecnico = idTecnico;
		this.idSede = idSede;
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

	public LocalDate getFechaFundacion() {
		return fechaFundacion;
	}

	public void setFechaFundacion(LocalDate fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}

	public String getCiudadEquipo() {
		return ciudadEquipo;
	}

	public void setCiudadEquipo(String ciudadEquipo) {
		this.ciudadEquipo = ciudadEquipo;
	}

	public Long getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Long idTecnico) {
		this.idTecnico = idTecnico;
	}

	public Long getIdSede() {
		return idSede;
	}

	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudadEquipo, fechaFundacion, idEquipo, idSede, idTecnico, nombreEquipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(ciudadEquipo, other.ciudadEquipo) && Objects.equals(fechaFundacion, other.fechaFundacion)
				&& Objects.equals(idEquipo, other.idEquipo) && Objects.equals(idSede, other.idSede)
				&& Objects.equals(idTecnico, other.idTecnico) && Objects.equals(nombreEquipo, other.nombreEquipo);
	}

	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombreEquipo=" + nombreEquipo + ", fechaFundacion=" + fechaFundacion
				+ ", ciudadEquipo=" + ciudadEquipo + ", idTecnico=" + idTecnico + ", idSede=" + idSede + "]";
	}
	
	
}
