package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

public class JugadorDTO {

	private Long idJugador;
	private String nombres;
	private String apellidos;
	private String nacionalidad;
	private LocalDate fechaNacimiento;
	private Integer edad; // derivado
	private String posicion;
	private Integer tirosPuerta; // solo ATACANTE
	private Integer pases; // solo MEDIOCAMPISTA
	private Integer entradasOportunas; // solo DEFENSA
	private Integer porteriaImbatida; // solo PORTERO

	public JugadorDTO() {

	}

	

	public JugadorDTO(Long idJugador, String nombres, String apellidos, String nacionalidad, LocalDate fechaNacimiento,
			Integer edad, String posicion, Integer tirosPuerta, Integer pases, Integer entradasOportunas,
			Integer porteriaImbatida) {
		super();
		this.idJugador = idJugador;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.posicion = posicion;
		this.tirosPuerta = tirosPuerta;
		this.pases = pases;
		this.entradasOportunas = entradasOportunas;
		this.porteriaImbatida = porteriaImbatida;
	}



	public Long getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	
	public Integer getTirosPuerta() {
		return tirosPuerta;
	}



	public void setTirosPuerta(Integer tirosPuerta) {
		this.tirosPuerta = tirosPuerta;
	}



	public Integer getPases() {
		return pases;
	}



	public void setPases(Integer pases) {
		this.pases = pases;
	}



	public Integer getEntradasOportunas() {
		return entradasOportunas;
	}



	public void setEntradasOportunas(Integer entradasOportunas) {
		this.entradasOportunas = entradasOportunas;
	}



	public Integer getPorteriaImbatida() {
		return porteriaImbatida;
	}



	public void setPorteriaImbatida(Integer porteriaImbatida) {
		this.porteriaImbatida = porteriaImbatida;
	}



	@Override
	public int hashCode() {
		return Objects.hash(apellidos, edad, entradasOportunas, fechaNacimiento, idJugador, nacionalidad, nombres,
				pases, porteriaImbatida, posicion, tirosPuerta);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JugadorDTO other = (JugadorDTO) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(edad, other.edad)
				&& Objects.equals(entradasOportunas, other.entradasOportunas)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(idJugador, other.idJugador)
				&& Objects.equals(nacionalidad, other.nacionalidad) && Objects.equals(nombres, other.nombres)
				&& Objects.equals(pases, other.pases) && Objects.equals(porteriaImbatida, other.porteriaImbatida)
				&& Objects.equals(posicion, other.posicion) && Objects.equals(tirosPuerta, other.tirosPuerta);
	}



	@Override
	public String toString() {
		return "JugadorDTO [idJugador=" + idJugador + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad
				+ ", posicion=" + posicion + ", tirosPuerta=" + tirosPuerta + ", pases=" + pases
				+ ", entradasOportunas=" + entradasOportunas + ", porteriaImbatida=" + porteriaImbatida + "]";
	}



}
