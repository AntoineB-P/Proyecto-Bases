package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class TipoTarjeta {

	private Long idTipo;
	private String colorTarjeta;
	
	public TipoTarjeta() {
		
	
	}

	public TipoTarjeta(Long idTipo, String colorTarjeta) {
		super();
		this.idTipo = idTipo;
		this.colorTarjeta = colorTarjeta;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public String getColorTarjeta() {
		return colorTarjeta;
	}

	public void setColorTarjeta(String colorTarjeta) {
		this.colorTarjeta = colorTarjeta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(colorTarjeta, idTipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoTarjeta other = (TipoTarjeta) obj;
		return Objects.equals(colorTarjeta, other.colorTarjeta) && Objects.equals(idTipo, other.idTipo);
	}

	@Override
	public String toString() {
		return "TipoTarjeta [idTipo=" + idTipo + ", colorTarjeta=" + colorTarjeta + "]";
	}
	
	
}
