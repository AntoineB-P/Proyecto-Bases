package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class EstadioDTO {
	
	private Long idEstadio;
	private String nombre;
	private int capacidad;
	private String direccion;
	private String ciudad;
	private String barrio;

	public EstadioDTO() {
		
	}

	public EstadioDTO(Long idEstadio, String nombre, int capacidad, String direccion, String ciudad, String barrio) {
		super();
		this.idEstadio = idEstadio;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.barrio = barrio;
	}

	public Long getIdEstadio() {
		return idEstadio;
	}

	public void setIdEstadio(Long idEstadio) {
		this.idEstadio = idEstadio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barrio, capacidad, ciudad, direccion, idEstadio, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadioDTO other = (EstadioDTO) obj;
		return Objects.equals(barrio, other.barrio) && capacidad == other.capacidad
				&& Objects.equals(ciudad, other.ciudad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(idEstadio, other.idEstadio) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "EstadioDTO [idEstadio=" + idEstadio + ", nombre=" + nombre + ", capacidad=" + capacidad + ", direccion="
				+ direccion + ", ciudad=" + ciudad + ", barrio=" + barrio + "]";
	}
	
	
}
