package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class EstadisticaJugadorDTO {
	
	private Long idJugador;
	private String nombreJugador;
	private Integer partidosJugados;
	private Integer goles;
	private Integer amarillas;
	private Integer rojas;
	
	public EstadisticaJugadorDTO() {
		
	}

	public EstadisticaJugadorDTO(Long idJugador, String nombreJugador, Integer partidosJugados, Integer goles,
			Integer amarillas, Integer rojas) {
		super();
		this.idJugador = idJugador;
		this.nombreJugador = nombreJugador;
		this.partidosJugados = partidosJugados;
		this.goles = goles;
		this.amarillas = amarillas;
		this.rojas = rojas;
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

	public Integer getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(Integer partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public Integer getGoles() {
		return goles;
	}

	public void setGoles(Integer goles) {
		this.goles = goles;
	}

	public Integer getAmarillas() {
		return amarillas;
	}

	public void setAmarillas(Integer amarillas) {
		this.amarillas = amarillas;
	}

	public Integer getRojas() {
		return rojas;
	}

	public void setRojas(Integer rojas) {
		this.rojas = rojas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amarillas, goles, idJugador, nombreJugador, partidosJugados, rojas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadisticaJugadorDTO other = (EstadisticaJugadorDTO) obj;
		return Objects.equals(amarillas, other.amarillas) && Objects.equals(goles, other.goles)
				&& Objects.equals(idJugador, other.idJugador) && Objects.equals(nombreJugador, other.nombreJugador)
				&& Objects.equals(partidosJugados, other.partidosJugados) && Objects.equals(rojas, other.rojas);
	}

	@Override
	public String toString() {
		return "EstadisticaJugadorDTO [idJugador=" + idJugador + ", nombreJugador=" + nombreJugador
				+ ", partidosJugados=" + partidosJugados + ", goles=" + goles + ", amarillas=" + amarillas + ", rojas="
				+ rojas + "]";
	}
	
	

}
