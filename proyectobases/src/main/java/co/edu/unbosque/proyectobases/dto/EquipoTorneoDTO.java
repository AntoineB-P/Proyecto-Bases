package co.edu.unbosque.proyectobases.dto;

import java.util.Objects;

public class EquipoTorneoDTO {
	
	private Long idEquipo;
	private String nombreEquipo;
	private Long idTorneo;
	private String nombreTorneo;
	
	public EquipoTorneoDTO() {
	
	}

	public EquipoTorneoDTO(Long idEquipo, String nombreEquipo, Long idTorneo, String nombreTorneo) {
		super();
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
		this.idTorneo = idTorneo;
		this.nombreTorneo = nombreTorneo;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public Long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getNombreTorneo() {
		return nombreTorneo;
	}

	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEquipo, idTorneo, nombreEquipo, nombreTorneo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipoTorneoDTO other = (EquipoTorneoDTO) obj;
		return Objects.equals(idEquipo, other.idEquipo) && Objects.equals(idTorneo, other.idTorneo)
				&& Objects.equals(nombreEquipo, other.nombreEquipo) && Objects.equals(nombreTorneo, other.nombreTorneo);
	}

	@Override
	public String toString() {
		return "EquipoTorneoDTO [idEquipo=" + idEquipo + ", nombreEquipo=" + nombreEquipo + ", idTorneo=" + idTorneo
				+ ", nombreTorneo=" + nombreTorneo + "]";
	}
	
	
}
