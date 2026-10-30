package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class Resultado {
	
	private Long idPartido;
	private Long idResultado;
	private Integer golesLocal;
	private Integer golesVisitante;
	
	public Resultado() {
		
	}

	public Resultado(Long idPartido, Long idResultado, Integer golesLocal, Integer golesVisitante) {
		super();
		this.idPartido = idPartido;
		this.idResultado = idResultado;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
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
		Resultado other = (Resultado) obj;
		return Objects.equals(golesLocal, other.golesLocal) && Objects.equals(golesVisitante, other.golesVisitante)
				&& Objects.equals(idPartido, other.idPartido) && Objects.equals(idResultado, other.idResultado);
	}

	@Override
	public String toString() {
		return "Resultado [idPartido=" + idPartido + ", idResultado=" + idResultado + ", golesLocal=" + golesLocal
				+ ", golesVisitante=" + golesVisitante + "]";
	}

	
}
