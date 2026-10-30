package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class PosicionDTO {
	
	private Long idEquipo;
	private String equipo;
	private Integer partidosJugados;
	private Integer ganados;
	private Integer empatados;
	private Integer perdidos;
	private Integer golesFavor;
	private Integer golesContra;
	private Integer diferencia;
	private Integer puntos;
	
	public PosicionDTO() {
		
	}

	public PosicionDTO(Long idEquipo, String equipo, Integer partidosJugados, Integer ganados, Integer empatados,
			Integer perdidos, Integer golesFavor, Integer golesContra, Integer diferencia, Integer puntos) {
		super();
		this.idEquipo = idEquipo;
		this.equipo = equipo;
		this.partidosJugados = partidosJugados;
		this.ganados = ganados;
		this.empatados = empatados;
		this.perdidos = perdidos;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
		this.diferencia = diferencia;
		this.puntos = puntos;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public Integer getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(Integer partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public Integer getGanados() {
		return ganados;
	}

	public void setGanados(Integer ganados) {
		this.ganados = ganados;
	}

	public Integer getEmpatados() {
		return empatados;
	}

	public void setEmpatados(Integer empatados) {
		this.empatados = empatados;
	}

	public Integer getPerdidos() {
		return perdidos;
	}

	public void setPerdidos(Integer perdidos) {
		this.perdidos = perdidos;
	}

	public Integer getGolesFavor() {
		return golesFavor;
	}

	public void setGolesFavor(Integer golesFavor) {
		this.golesFavor = golesFavor;
	}

	public Integer getGolesContra() {
		return golesContra;
	}

	public void setGolesContra(Integer golesContra) {
		this.golesContra = golesContra;
	}

	public Integer getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(Integer diferencia) {
		this.diferencia = diferencia;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diferencia, empatados, equipo, ganados, golesContra, golesFavor, idEquipo, partidosJugados,
				perdidos, puntos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosicionDTO other = (PosicionDTO) obj;
		return Objects.equals(diferencia, other.diferencia) && Objects.equals(empatados, other.empatados)
				&& Objects.equals(equipo, other.equipo) && Objects.equals(ganados, other.ganados)
				&& Objects.equals(golesContra, other.golesContra) && Objects.equals(golesFavor, other.golesFavor)
				&& Objects.equals(idEquipo, other.idEquipo) && Objects.equals(partidosJugados, other.partidosJugados)
				&& Objects.equals(perdidos, other.perdidos) && Objects.equals(puntos, other.puntos);
	}

	@Override
	public String toString() {
		return "PosicionDTO [idEquipo=" + idEquipo + ", equipo=" + equipo + ", partidosJugados=" + partidosJugados
				+ ", ganados=" + ganados + ", empatados=" + empatados + ", perdidos=" + perdidos + ", golesFavor="
				+ golesFavor + ", golesContra=" + golesContra + ", diferencia=" + diferencia + ", puntos=" + puntos
				+ "]";
	}
	

}
