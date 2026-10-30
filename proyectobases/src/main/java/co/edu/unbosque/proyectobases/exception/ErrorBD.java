package co.edu.unbosque.proyectobases.exception;

import java.sql.SQLException;

public class ErrorBD {

	public static RuntimeException traducir(SQLException e) {
		switch (e.getErrorCode()) {
		case 1:
			return new ConflictoException("Ya existe un registro con esos datos (valor único repetido)");
		case 2291:
			return new DatoInvalidoException("Hace referencia a un registro que no existe");
		case 2292:
			return new ConflictoException("No se puede eliminar: hay otros registros que dependen de este");
		case 2290:
			return new DatoInvalidoException("Algún dato no cumple las reglas permitidas");
		case 1400:
			return new DatoInvalidoException("Falta un dato obligatorio");
		case 12899:
			return new DatoInvalidoException("Algún texto es demasiado largo");
		default:
			return new RuntimeException("Error de base de datos: " + e.getMessage(), e);
		}
	}
}
