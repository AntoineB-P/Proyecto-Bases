package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class Estadio {

	private Long idEstadio;
	private String nombreEstadio;
	private Integer capacidad;
	private String direccion;
	private String ciudadEstadio;
	private String barrio;
	
	public Estadio() {
		
	}

	public Estadio(Long idEstadio, String nombreEstadio, Integer capacidad, String direccion, String ciudadEstadio,
			String barrio) {
		super();
		this.idEstadio = idEstadio;
		this.nombreEstadio = nombreEstadio;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.ciudadEstadio = ciudadEstadio;
		this.barrio = barrio;
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

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudadEstadio() {
		return ciudadEstadio;
	}

	public void setCiudadEstadio(String ciudadEstadio) {
		this.ciudadEstadio = ciudadEstadio;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barrio, capacidad, ciudadEstadio, direccion, idEstadio, nombreEstadio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estadio other = (Estadio) obj;
		return Objects.equals(barrio, other.barrio) && Objects.equals(capacidad, other.capacidad)
				&& Objects.equals(ciudadEstadio, other.ciudadEstadio) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(idEstadio, other.idEstadio) && Objects.equals(nombreEstadio, other.nombreEstadio);
	}

	@Override
	public String toString() {
		return "Estadio [idEstadio=" + idEstadio + ", nombreEstadio=" + nombreEstadio + ", capacidad=" + capacidad
				+ ", direccion=" + direccion + ", ciudadEstadio=" + ciudadEstadio + ", barrio=" + barrio + "]";
	}
	
	
}
