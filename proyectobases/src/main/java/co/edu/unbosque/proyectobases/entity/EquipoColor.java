package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class EquipoColor {
	
	private Long idEquipo;
	private String color;
	
	public EquipoColor() {
		
	}

	public EquipoColor(Long idEquipo, String color) {
		super();
		this.idEquipo = idEquipo;
		this.color = color;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, idEquipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipoColor other = (EquipoColor) obj;
		return Objects.equals(color, other.color) && Objects.equals(idEquipo, other.idEquipo);
	}

	@Override
	public String toString() {
		return "EquipoColor [idEquipo=" + idEquipo + ", color=" + color + "]";
	}

	
}
