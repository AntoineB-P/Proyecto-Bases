package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class Participacion {
	
	private Long idParticipacion;
	private Long idJugador;
	private Long idPartido;
	private String titular; // "S" o "N"
	private Integer minutosJugados;
	
	public Participacion(){
		
	}

	public Participacion(Long idParticipacion, Long idJugador, Long idPartido, String titular, Integer minutosJugados) {
		super();
		this.idParticipacion = idParticipacion;
		this.idJugador = idJugador;
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

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
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
		return Objects.hash(idJugador, idParticipacion, idPartido, minutosJugados, titular);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participacion other = (Participacion) obj;
		return Objects.equals(idJugador, other.idJugador) && Objects.equals(idParticipacion, other.idParticipacion)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(minutosJugados, other.minutosJugados)
				&& Objects.equals(titular, other.titular);
	}

	@Override
	public String toString() {
		return "Participacion [idParticipacion=" + idParticipacion + ", idJugador=" + idJugador + ", idPartido="
				+ idPartido + ", titular=" + titular + ", minutosJugados=" + minutosJugados + "]";
	}
	
	

}
