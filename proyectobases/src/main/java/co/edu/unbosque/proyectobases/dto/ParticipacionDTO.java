package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class ParticipacionDTO {
	
	private Long idParticipacion;
	private Long idJugador;
	private String nombreJugador;
	private Long idPartido;
	private Boolean titular;
	private Integer minutosJugados;
	
	public ParticipacionDTO() {
		
	}

	public ParticipacionDTO(Long idParticipacion, Long idJugador, String nombreJugador, Long idPartido, Boolean titular,
			Integer minutosJugados) {
		super();
		this.idParticipacion = idParticipacion;
		this.idJugador = idJugador;
		this.nombreJugador = nombreJugador;
		this.idPartido = idPartido;
		this.titular = titular;
		this.minutosJugados = minutosJugados;
	}

	public Long getIdParticipacion() {
		return idParticipacion;
	}

	public void setIdParticipacion(Long idParticipacion) {
		this.idParticipacion = idParticipacion;
	}

	public Long getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public Boolean getTitular() {
		return titular;
	}

	public void setTitular(Boolean titular) {
		this.titular = titular;
	}

	public Integer getMinutosJugados() {
		return minutosJugados;
	}

	public void setMinutosJugados(Integer minutosJugados) {
		this.minutosJugados = minutosJugados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idJugador, idParticipacion, idPartido, minutosJugados, nombreJugador, titular);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipacionDTO other = (ParticipacionDTO) obj;
		return Objects.equals(idJugador, other.idJugador) && Objects.equals(idParticipacion, other.idParticipacion)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(minutosJugados, other.minutosJugados)
				&& Objects.equals(nombreJugador, other.nombreJugador) && Objects.equals(titular, other.titular);
	}

	@Override
	public String toString() {
		return "ParticipacionDTO [idParticipacion=" + idParticipacion + ", idJugador=" + idJugador + ", nombreJugador="
				+ nombreJugador + ", idPartido=" + idPartido + ", titular=" + titular + ", minutosJugados="
				+ minutosJugados + "]";
	}
	
	
}
