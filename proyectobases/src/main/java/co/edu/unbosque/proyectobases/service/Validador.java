package co.edu.unbosque.proyectobases.service;

import java.time.LocalDate;

import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;

public class Validador {

	/** Texto obligatorio, sin espacios sobrantes y con largo máximo. */
	public static String texto(String valor, String campo, int max) {
		if (valor == null || valor.isBlank()) {
			throw new DatoInvalidoException("El campo " + campo + " es obligatorio");
		}
		String limpio = valor.trim();
		if (limpio.length() > max) {
			throw new DatoInvalidoException("El campo " + campo + " no puede pasar de " + max + " caracteres");
		}
		return limpio;
	}

	public static void obligatorio(Object valor, String campo) {
		if (valor == null) {
			throw new DatoInvalidoException("El campo " + campo + " es obligatorio");
		}
	}

	public static void rango(Integer valor, String campo, int min, int max) {
		obligatorio(valor, campo);
		if (valor < min || valor > max) {
			throw new DatoInvalidoException("El campo " + campo + " debe estar entre " + min + " y " + max);
		}
	}

	public static void fechaNoFutura(LocalDate fecha, String campo) {
		obligatorio(fecha, campo);
		if (fecha.isAfter(LocalDate.now())) {
			throw new DatoInvalidoException("El campo " + campo + " no puede ser una fecha futura");
		}
	}

	/** Revisa que el valor sea una de las opciones y devuelve la forma exacta de la opción. */
	public static String opcion(String valor, String campo, String... validas) {
		if (valor != null) {
			for (String v : validas) {
				if (v.equalsIgnoreCase(valor.trim())) {
					return v;
				}
			}
		}
		throw new DatoInvalidoException("El campo " + campo + " debe ser uno de: " + String.join(", ", validas));
	}
}