package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

public class HistorialEquipoDTO {

	private Long idPartido;
	private LocalDate fecha;
	private Integer jornada;
	private String rival;
	private String condicion;   // LOCAL o VISITANTE
	private Integer golesFavor;
	private Integer golesContra;
	private String resultado;   // G, E o P
	
	public HistorialEquipoDTO() {
		
	}

	public HistorialEquipoDTO(Long idPartido, LocalDate fecha, Integer jornada, String rival, String condicion,
			Integer golesFavor, Integer golesContra, String resultado) {
		super();
		this.idPartido = idPartido;
		this.fecha = fecha;
		this.jornada = jornada;
		this.rival = rival;
		this.condicion = condicion;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
		this.resultado = resultado;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Integer getJornada() {
		return jornada;
	}

	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	public String getRival() {
		return rival;
	}

	public void setRival(String rival) {
		this.rival = rival;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
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

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(condicion, fecha, golesContra, golesFavor, idPartido, jornada, resultado, rival);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistorialEquipoDTO other = (HistorialEquipoDTO) obj;
		return Objects.equals(condicion, other.condicion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(golesContra, other.golesContra) && Objects.equals(golesFavor, other.golesFavor)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(jornada, other.jornada)
				&& Objects.equals(resultado, other.resultado) && Objects.equals(rival, other.rival);
	}

	@Override
	public String toString() {
		return "HistorialEquipoDTO [idPartido=" + idPartido + ", fecha=" + fecha + ", jornada=" + jornada + ", rival="
				+ rival + ", condicion=" + condicion + ", golesFavor=" + golesFavor + ", golesContra=" + golesContra
				+ ", resultado=" + resultado + "]";
	}
	
	
	
}
