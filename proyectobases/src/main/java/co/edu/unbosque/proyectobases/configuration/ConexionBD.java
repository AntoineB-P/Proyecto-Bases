package co.edu.unbosque.proyectobases.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/FREEPDB1";
	private static final String USUARIO = "LIGA";
	private static final String CLAVE = "CAMBIAR_CLAVE";

	public static Connection obtener() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, CLAVE);
	}

}
