package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class EquipoDTO {

	private Long idEquipo;
	private String nombre;
	private LocalDate fechaFundacion;
	private String ciudad;
	private List<String> colores;
	private Long idSede;
	private String nombreEstadio;
	private Long idTecnico;
	private String nombreTecnico;
	
	public EquipoDTO() {
		
	}

	public EquipoDTO(Long idEquipo, String nombre, LocalDate fechaFundacion, String ciudad, List<String> colores,
			Long idSede, String nombreEstadio, Long idTecnico, String nombreTecnico) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.fechaFundacion = fechaFundacion;
		this.ciudad = ciudad;
		this.colores = colores;
		this.idSede = idSede;
		this.nombreEstadio = nombreEstadio;
		this.idTecnico = idTecnico;
		this.nombreTecnico = nombreTecnico;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaFundacion() {
		return fechaFundacion;
	}

	public void setFechaFundacion(LocalDate fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<String> getColores() {
		return colores;
	}

	public void setColores(List<String> colores) {
		this.colores = colores;
	}

	public Long getIdSede() {
		return idSede;
	}

	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	public Long getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Long idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String getNombreTecnico() {
		return nombreTecnico;
	}

	public void setNombreTecnico(String nombreTecnico) {
		this.nombreTecnico = nombreTecnico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, colores, fechaFundacion, idEquipo, idSede, idTecnico, nombre, nombreEstadio,
				nombreTecnico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipoDTO other = (EquipoDTO) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(colores, other.colores)
				&& Objects.equals(fechaFundacion, other.fechaFundacion) && Objects.equals(idEquipo, other.idEquipo)
				&& Objects.equals(idSede, other.idSede) && Objects.equals(idTecnico, other.idTecnico)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nombreEstadio, other.nombreEstadio)
				&& Objects.equals(nombreTecnico, other.nombreTecnico);
	}

	@Override
	public String toString() {
		return "EquipoDTO [idEquipo=" + idEquipo + ", nombre=" + nombre + ", fechaFundacion=" + fechaFundacion
				+ ", ciudad=" + ciudad + ", colores=" + colores + ", idSede=" + idSede + ", nombreEstadio="
				+ nombreEstadio + ", idTecnico=" + idTecnico + ", nombreTecnico=" + nombreTecnico + "]";
	}
	
	
	
	

}
