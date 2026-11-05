package co.edu.unbosque.proyectobases.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase DTO que representa la información de un jugador de fútbol.
 * Almacena sus datos personales, nacionalidad, fecha de nacimiento,
 * edad, posición de juego y estadísticas específicas según su posición
 * dentro del equipo.
 */
public class JugadorDTO {

	/** Identificador único del jugador. */
	private Long idJugador;

	/** Nombres del jugador. */
	private String nombres;

	/** Apellidos del jugador. */
	private String apellidos;

	/** Nacionalidad del jugador. */
	private String nacionalidad;

	/** Fecha de nacimiento del jugador. */
	private LocalDate fechaNacimiento;

	/** Edad del jugador, calculada a partir de su fecha de nacimiento. */
	private Integer edad;

	/** Posición que desempeña el jugador dentro del equipo. */
	private String posicion;

	/** Cantidad de tiros a puerta realizados por el jugador; aplica solo para ATACANTE. */
	private Integer tirosPuerta;

	/** Cantidad de pases realizados por el jugador; aplica solo para MEDIOCAMPISTA. */
	private Integer pases;

	/** Cantidad de entradas oportunas realizadas por el jugador; aplica solo para DEFENSA. */
	private Integer entradasOportunas;

	/** Cantidad de porterías imbatidas; aplica solo para PORTERO. */
	private Integer porteriaImbatida;

	/**
	 * Constructor vacío de la clase JugadorDTO.
	 */
	public JugadorDTO() {

	}



	/**
	 * Constructor que inicializa todos los atributos del jugador,
	 * incluyendo sus datos personales, posición y estadísticas.
	 *
	 * @param idJugador identificador único del jugador.
	 * @param nombres nombres del jugador.
	 * @param apellidos apellidos del jugador.
	 * @param nacionalidad nacionalidad del jugador.
	 * @param fechaNacimiento fecha de nacimiento del jugador.
	 * @param edad edad del jugador.
	 * @param posicion posición que desempeña dentro del equipo.
	 * @param tirosPuerta cantidad de tiros a puerta realizados.
	 * @param pases cantidad de pases realizados.
	 * @param entradasOportunas cantidad de entradas oportunas realizadas.
	 * @param porteriaImbatida cantidad de porterías imbatidas.
	 */
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



	/**
	 * Obtiene el identificador del jugador.
	 *
	 * @return identificador único del jugador.
	 */
	public Long getIdJugador() {
		return idJugador;
	}

	/**
	 * Establece el identificador del jugador.
	 *
	 * @param idJugador identificador único del jugador.
	 */
	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	/**
	 * Obtiene los nombres del jugador.
	 *
	 * @return nombres del jugador.
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * Establece los nombres del jugador.
	 *
	 * @param nombres nombres del jugador.
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Obtiene los apellidos del jugador.
	 *
	 * @return apellidos del jugador.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece los apellidos del jugador.
	 *
	 * @param apellidos apellidos del jugador.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Obtiene la nacionalidad del jugador.
	 *
	 * @return nacionalidad del jugador.
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * Establece la nacionalidad del jugador.
	 *
	 * @param nacionalidad nacionalidad del jugador.
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * Obtiene la fecha de nacimiento del jugador.
	 *
	 * @return fecha de nacimiento del jugador.
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento del jugador.
	 *
	 * @param fechaNacimiento fecha de nacimiento del jugador.
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Obtiene la edad del jugador.
	 *
	 * @return edad del jugador.
	 */
	public Integer getEdad() {
		return edad;
	}

	/**
	 * Establece la edad del jugador.
	 *
	 * @param edad edad del jugador.
	 */
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	/**
	 * Obtiene la posición del jugador dentro del equipo.
	 *
	 * @return posición que desempeña el jugador.
	 */
	public String getPosicion() {
		return posicion;
	}

	/**
	 * Establece la posición del jugador dentro del equipo.
	 *
	 * @param posicion posición que desempeña el jugador.
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}


	/**
	 * Obtiene la cantidad de tiros a puerta realizados por el jugador.
	 *
	 * @return cantidad de tiros a puerta.
	 */
	public Integer getTirosPuerta() {
		return tirosPuerta;
	}



	/**
	 * Establece la cantidad de tiros a puerta del jugador.
	 *
	 * @param tirosPuerta cantidad de tiros a puerta realizados.
	 */
	public void setTirosPuerta(Integer tirosPuerta) {
		this.tirosPuerta = tirosPuerta;
	}



	/**
	 * Obtiene la cantidad de pases realizados por el jugador.
	 *
	 * @return cantidad de pases.
	 */
	public Integer getPases() {
		return pases;
	}



	/**
	 * Establece la cantidad de pases realizados por el jugador.
	 *
	 * @param pases cantidad de pases realizados.
	 */
	public void setPases(Integer pases) {
		this.pases = pases;
	}



	/**
	 * Obtiene la cantidad de entradas oportunas realizadas por el jugador.
	 *
	 * @return cantidad de entradas oportunas.
	 */
	public Integer getEntradasOportunas() {
		return entradasOportunas;
	}



	/**
	 * Establece la cantidad de entradas oportunas del jugador.
	 *
	 * @param entradasOportunas cantidad de entradas oportunas realizadas.
	 */
	public void setEntradasOportunas(Integer entradasOportunas) {
		this.entradasOportunas = entradasOportunas;
	}



	/**
	 * Obtiene la cantidad de porterías imbatidas por el jugador.
	 *
	 * @return cantidad de porterías imbatidas.
	 */
	public Integer getPorteriaImbatida() {
		return porteriaImbatida;
	}



	/**
	 * Establece la cantidad de porterías imbatidas del jugador.
	 *
	 * @param porteriaImbatida cantidad de porterías imbatidas.
	 */
	public void setPorteriaImbatida(Integer porteriaImbatida) {
		this.porteriaImbatida = porteriaImbatida;
	}



	/**
	 * Calcula el código hash del objeto utilizando todos sus atributos.
	 *
	 * @return código hash generado a partir de los atributos de la clase.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, edad, entradasOportunas, fechaNacimiento, idJugador, nacionalidad, nombres,
				pases, porteriaImbatida, posicion, tirosPuerta);
	}



	/**
	 * Compara este objeto con otro para determinar si son iguales.
	 * La comparación tiene en cuenta todos los atributos del jugador.
	 *
	 * @param obj objeto con el que se desea realizar la comparación.
	 * @return true si ambos objetos pertenecen a la misma clase y tienen
	 *         los mismos valores en sus atributos; false en caso contrario.
	 */
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



	/**
	 * Genera una representación en cadena de texto del jugador,
	 * incluyendo los valores de todos sus atributos.
	 *
	 * @return representación textual del jugador.
	 */
	@Override
	public String toString() {
		return "JugadorDTO [idJugador=" + idJugador + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad
				+ ", posicion=" + posicion + ", tirosPuerta=" + tirosPuerta + ", pases=" + pases
				+ ", entradasOportunas=" + entradasOportunas + ", porteriaImbatida=" + porteriaImbatida + "]";
	}



}