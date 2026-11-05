package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class Tarjeta {
	private Long idParticipacion;
	private Long idTarjeta;
	private Long idTipo;
	private String motivo;
	private Integer minutoTarjeta;
	
	public Tarjeta() {
		
	}

	public Tarjeta(Long idParticipacion, Long idTarjeta, Long idTipo, String motivo, Integer minutoTarjeta) {
		super();
		this.idParticipacion = idParticipacion;
		this.idTarjeta = idTarjeta;
		this.idTipo = idTipo;
		this.motivo = motivo;
		this.minutoTarjeta = minutoTarjeta;
	}

	public Long getIdParticipacion() {
		return idParticipacion;
	}

	public void setIdParticipacion(Long idParticipacion) {
		this.idParticipacion = idParticipacion;
	}

	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Integer getMinutoTarjeta() {
		return minutoTarjeta;
	}

	public void setMinutoTarjeta(Integer minutoTarjeta) {
		this.minutoTarjeta = minutoTarjeta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idParticipacion, idTarjeta, idTipo, minutoTarjeta, motivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		return Objects.equals(idParticipacion, other.idParticipacion) && Objects.equals(idTarjeta, other.idTarjeta)
				&& Objects.equals(idTipo, other.idTipo) && Objects.equals(minutoTarjeta, other.minutoTarjeta)
				&& Objects.equals(motivo, other.motivo);
	}

	@Override
	public String toString() {
		return "Tarjeta [idParticipacion=" + idParticipacion + ", idTarjeta=" + idTarjeta + ", idTipo=" + idTipo
				+ ", motivo=" + motivo + ", minutoTarjeta=" + minutoTarjeta + "]";
	}

	
}
