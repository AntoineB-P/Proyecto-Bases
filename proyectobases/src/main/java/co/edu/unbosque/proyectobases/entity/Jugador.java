package co.edu.unbosque.proyectobases.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Jugador {
	
	private Long idJugador;
	private String nombres;
	private String apellidos;
	private String nacionalidad;
	private LocalDate fechaNacimiento;
	private String posicion;
	
	public Jugador() {
		
	}

	public Jugador(Long idJugador, String nombres, String apellidos, String nacionalidad, LocalDate fechaNacimiento,
			String posicion) {
		super();
		this.idJugador = idJugador;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.posicion = posicion;
	}

	public Long getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
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

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, fechaNacimiento, idJugador, nacionalidad, nombres, posicion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& Objects.equals(idJugador, other.idJugador) && Objects.equals(nacionalidad, other.nacionalidad)
				&& Objects.equals(nombres, other.nombres) && Objects.equals(posicion, other.posicion);
	}

	@Override
	public String toString() {
		return "Jugador [idJugador=" + idJugador + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + ", posicion=" + posicion
				+ "]";
	}

	
}
