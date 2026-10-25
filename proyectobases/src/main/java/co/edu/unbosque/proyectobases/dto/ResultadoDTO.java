package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class ResultadoDTO {
	
	private Long idResultado;
	private Long idPartido;
	private Integer golesLocal;
	private Integer golesVisitante;
	
	public ResultadoDTO() {
		
	}

	public ResultadoDTO(Long idResultado, Long idPartido, Integer golesLocal, Integer golesVisitante) {
		super();
		this.idResultado = idResultado;
		this.idPartido = idPartido;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(golesLocal, golesVisitante, idPartido, idResultado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoDTO other = (ResultadoDTO) obj;
		return Objects.equals(golesLocal, other.golesLocal) && Objects.equals(golesVisitante, other.golesVisitante)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(idResultado, other.idResultado);
	}

	@Override
	public String toString() {
		return "ResultadoDTO [idResultado=" + idResultado + ", idPartido=" + idPartido + ", golesLocal=" + golesLocal
				+ ", golesVisitante=" + golesVisitante + "]";
	}

}
