package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class Arbitraje {
	
	private Long idPartido;
	private String cedula;
	private String rol;

	public Arbitraje() {
		
	}

	public Arbitraje(Long idPartido, String cedula, String rol) {
		super();
		this.idPartido = idPartido;
		this.cedula = cedula;
		this.rol = rol;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cedula, idPartido, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitraje other = (Arbitraje) obj;
		return Objects.equals(cedula, other.cedula) && Objects.equals(idPartido, other.idPartido)
				&& Objects.equals(rol, other.rol);
	}

	@Override
	public String toString() {
		return "Arbitraje [idPartido=" + idPartido + ", cedula=" + cedula + ", rol=" + rol + "]";
	}
	
	
}
