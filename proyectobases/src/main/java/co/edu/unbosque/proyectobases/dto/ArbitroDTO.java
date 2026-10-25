package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class ArbitroDTO {

	private Long cedula;
	private String nombres;
	private String apellidos;
	private String ciudad;
	private String categoria;
	
	public ArbitroDTO() {
		
	}

	public ArbitroDTO(Long cedula, String nombres, String apellidos, String ciudad, String categoria) {
		super();
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.ciudad = ciudad;
		this.categoria = categoria;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, categoria, cedula, ciudad, nombres);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArbitroDTO other = (ArbitroDTO) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(categoria, other.categoria)
				&& Objects.equals(cedula, other.cedula) && Objects.equals(ciudad, other.ciudad)
				&& Objects.equals(nombres, other.nombres);
	}

	@Override
	public String toString() {
		return "ArbitroDTO [cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", ciudad="
				+ ciudad + ", categoria=" + categoria + "]";
	}
	
	
}
