package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class TipoTarjetaDTO {

	private Long idTipo;
	private String color;
	
	public TipoTarjetaDTO() {
		
	}

	public TipoTarjetaDTO(Long idTipo, String color) {
		super();
		this.idTipo = idTipo;
		this.color = color;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, idTipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoTarjetaDTO other = (TipoTarjetaDTO) obj;
		return Objects.equals(color, other.color) && Objects.equals(idTipo, other.idTipo);
	}

	@Override
	public String toString() {
		return "TipoTarjetaDTO [idTipo=" + idTipo + ", color=" + color + "]";
	}
	
	
}
