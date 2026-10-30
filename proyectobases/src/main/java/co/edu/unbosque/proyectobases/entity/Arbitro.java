package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class Arbitro {

	private String cedula;
	private String nombres;
	private String apellidos;
	private String ciudadArbitro;
	private String categoria;
	
	public Arbitro() {
		
	}

	public Arbitro(String cedula, String nombres, String apellidos, String ciudadArbitro, String categoria) {
		super();
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.ciudadArbitro = ciudadArbitro;
		this.categoria = categoria;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
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

	public String getCiudadArbitro() {
		return ciudadArbitro;
	}

	public void setCiudadArbitro(String ciudadArbitro) {
		this.ciudadArbitro = ciudadArbitro;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, categoria, cedula, ciudadArbitro, nombres);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitro other = (Arbitro) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(categoria, other.categoria)
				&& Objects.equals(cedula, other.cedula) && Objects.equals(ciudadArbitro, other.ciudadArbitro)
				&& Objects.equals(nombres, other.nombres);
	}

	@Override
	public String toString() {
		return "Arbitro [cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", ciudadArbitro="
				+ ciudadArbitro + ", categoria=" + categoria + "]";
	}
	
	
}
