package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class TarjetaDTO {
	
	private Long idTarjeta;
	private Long idParticipacion;
	private Long idTipo;
	private String color;
	private String motivo;
	private Integer minuto;
	
	public TarjetaDTO() {
		
	}

	public TarjetaDTO(Long idTarjeta, Long idParticipacion, Long idTipo, String color, String motivo, Integer minuto) {
		super();
		this.idTarjeta = idTarjeta;
		this.idParticipacion = idParticipacion;
		this.idTipo = idTipo;
		this.color = color;
		this.motivo = motivo;
		this.minuto = minuto;
	}

	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public Long getIdParticipacion() {
		return idParticipacion;
	}

	public void setIdParticipacion(Long idParticipacion) {
		this.idParticipacion = idParticipacion;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, idParticipacion, idTarjeta, idTipo, minuto, motivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarjetaDTO other = (TarjetaDTO) obj;
		return Objects.equals(color, other.color) && Objects.equals(idParticipacion, other.idParticipacion)
				&& Objects.equals(idTarjeta, other.idTarjeta) && Objects.equals(idTipo, other.idTipo)
				&& Objects.equals(minuto, other.minuto) && Objects.equals(motivo, other.motivo);
	}

	@Override
	public String toString() {
		return "TarjetaDTO [idTarjeta=" + idTarjeta + ", idParticipacion=" + idParticipacion + ", idTipo=" + idTipo
				+ ", color=" + color + ", motivo=" + motivo + ", minuto=" + minuto + "]";
	}

	
	
}
