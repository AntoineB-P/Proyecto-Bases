package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class Gol {
	
	private Long idParticipacion;
	private Long idGol;
	private String tipoGol;
	private Integer minutoGol;
	
	public Gol() {
		
	}

	public Gol(Long idParticipacion, Long idGol, String tipoGol, Integer minutoGol) {
		super();
		this.idParticipacion = idParticipacion;
		this.idGol = idGol;
		this.tipoGol = tipoGol;
		this.minutoGol = minutoGol;
	}

	public Long getIdParticipacion() {
		return idParticipacion;
	}

	public void setIdParticipacion(Long idParticipacion) {
		this.idParticipacion = idParticipacion;
	}

	public Long getIdGol() {
		return idGol;
	}

	public void setIdGol(Long idGol) {
		this.idGol = idGol;
	}

	public String getTipoGol() {
		return tipoGol;
	}

	public void setTipoGol(String tipoGol) {
		this.tipoGol = tipoGol;
	}

	public Integer getMinutoGol() {
		return minutoGol;
	}

	public void setMinutoGol(Integer minutoGol) {
		this.minutoGol = minutoGol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGol, idParticipacion, minutoGol, tipoGol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gol other = (Gol) obj;
		return Objects.equals(idGol, other.idGol) && Objects.equals(idParticipacion, other.idParticipacion)
				&& Objects.equals(minutoGol, other.minutoGol) && Objects.equals(tipoGol, other.tipoGol);
	}

	@Override
	public String toString() {
		return "Gol [idParticipacion=" + idParticipacion + ", idGol=" + idGol + ", tipoGol=" + tipoGol + ", minutoGol="
				+ minutoGol + "]";
	}

	
}
