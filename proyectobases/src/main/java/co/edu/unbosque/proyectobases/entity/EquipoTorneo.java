package co.edu.unbosque.proyectobases.entity;

import java.util.Objects;

public class EquipoTorneo {
	
	private Long idEquipo;
	private Long idTorneo;
	
	public EquipoTorneo(){
		
	}

	public EquipoTorneo(Long idEquipo, Long idTorneo) {
		super();
		this.idEquipo = idEquipo;
		this.idTorneo = idTorneo;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEquipo, idTorneo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipoTorneo other = (EquipoTorneo) obj;
		return Objects.equals(idEquipo, other.idEquipo) && Objects.equals(idTorneo, other.idTorneo);
	}

	@Override
	public String toString() {
		return "EquipoTorneo [idEquipo=" + idEquipo + ", idTorneo=" + idTorneo + "]";
	}
	
	

}
