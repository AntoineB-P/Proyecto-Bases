package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class GolDTO {
	
	private Long idGol;
	private Long idParticipacion;
	private String tipo;
	private Integer minuto;
	
	public GolDTO() {
		
	}

	public GolDTO(Long idGol, Long idParticipacion, String tipo, Integer minuto) {
		super();
		this.idGol = idGol;
		this.idParticipacion = idParticipacion;
		this.tipo = tipo;
		this.minuto = minuto;
	}

	public Long getIdGol() {
		return idGol;
	}

	public void setIdGol(Long idGol) {
		this.idGol = idGol;
	}

	public Long getIdParticipacion() {
		return idParticipacion;
	}

	public void setIdParticipacion(Long idParticipacion) {
		this.idParticipacion = idParticipacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGol, idParticipacion, minuto, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GolDTO other = (GolDTO) obj;
		return Objects.equals(idGol, other.idGol) && Objects.equals(idParticipacion, other.idParticipacion)
				&& Objects.equals(minuto, other.minuto) && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "GolDTO [idGol=" + idGol + ", idParticipacion=" + idParticipacion + ", tipo=" + tipo + ", minuto="
				+ minuto + "]";
	}
	
	

}
