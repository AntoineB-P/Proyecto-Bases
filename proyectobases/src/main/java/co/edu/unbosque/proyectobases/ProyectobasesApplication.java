package co.edu.unbosque.proyectobases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación ProyectoBases.
 * 
 * Configura e inicia la aplicación utilizando Spring Boot.
 * La anotación @SpringBootApplication habilita la configuración automática,
 * el escaneo de componentes y la configuración de Spring.
 */
@SpringBootApplication
public class ProyectobasesApplication {

	/**
	 * Método principal de ejecución de la aplicación.
	 * 
	 * Inicia el contexto de Spring y pone en funcionamiento
	 * la aplicación ProyectoBases.
	 *
	 * @param args argumentos recibidos desde la línea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProyectobasesApplication.class, args);
	}

}