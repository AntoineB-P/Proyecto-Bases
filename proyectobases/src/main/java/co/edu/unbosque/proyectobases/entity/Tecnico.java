package co.edu.unbosque.proyectobases.entity;

import java.time.LocalDate;

/**
 * Clase que representa la entidad Técnico dentro del sistema.
 * Contiene la información personal de un técnico.
 */
public class Tecnico {

	/** Identificador único del técnico. */
	private Long idTecnico;

	/** Nombres del técnico. */
	private String nombres;

	/** Apellidos del técnico. */
	private String apellidos;

	/** Nacionalidad del técnico. */
	private String nacionalidad;

	/** Fecha de nacimiento del técnico. */
	private LocalDate fechaNacimiento;

}