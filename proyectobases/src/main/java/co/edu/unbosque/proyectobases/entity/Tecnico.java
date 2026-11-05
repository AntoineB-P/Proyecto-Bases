package co.edu.unbosque.proyectobases.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Tecnico {
	
	private Long idTecnico;
	private String nombres;
	private String apellidos;
	private String nacionalidad;
	private LocalDate fechaNacimiento;
	
	public Tecnico() {
		
	}

	public Tecnico(Long idTecnico, String nombres, String apellidos, String nacionalidad, LocalDate fechaNacimiento) {
		super();
		this.idTecnico = idTecnico;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Long idTecnico) {
		this.idTecnico = idTecnico;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, fechaNacimiento, idTecnico, nacionalidad, nombres);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tecnico other = (Tecnico) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& Objects.equals(idTecnico, other.idTecnico) && Objects.equals(nacionalidad, other.nacionalidad)
				&& Objects.equals(nombres, other.nombres);
	}

	@Override
	public String toString() {
		return "Tecnico [idTecnico=" + idTecnico + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
}
