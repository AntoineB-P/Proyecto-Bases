package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class ArbitrajeDTO {

	private Long cedulaArbitro;
	private String nombreArbitro;
	private Long idPartido;
	private String rol;
	
	public ArbitrajeDTO() {
		
	}

	public ArbitrajeDTO(Long cedulaArbitro, String nombreArbitro, Long idPartido, String rol) {
		super();
		this.cedulaArbitro = cedulaArbitro;
		this.nombreArbitro = nombreArbitro;
		this.idPartido = idPartido;
		this.rol = rol;
	}

	public Long getCedulaArbitro() {
		return cedulaArbitro;
	}

	public void setCedulaArbitro(Long cedulaArbitro) {
		this.cedulaArbitro = cedulaArbitro;
	}

	public String getNombreArbitro() {
		return nombreArbitro;
	}

	public void setNombreArbitro(String nombreArbitro) {
		this.nombreArbitro = nombreArbitro;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cedulaArbitro, idPartido, nombreArbitro, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArbitrajeDTO other = (ArbitrajeDTO) obj;
		return Objects.equals(cedulaArbitro, other.cedulaArbitro) && Objects.equals(idPartido, other.idPartido)
				&& Objects.equals(nombreArbitro, other.nombreArbitro) && Objects.equals(rol, other.rol);
	}

	@Override
	public String toString() {
		return "ArbitrajeDTO [cedulaArbitro=" + cedulaArbitro + ", nombreArbitro=" + nombreArbitro + ", idPartido="
				+ idPartido + ", rol=" + rol + "]";
	}
	
	
}
