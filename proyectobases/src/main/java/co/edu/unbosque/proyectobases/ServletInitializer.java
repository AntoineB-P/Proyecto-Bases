package co.edu.unbosque.proyectobases;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Clase que permite inicializar la aplicación Spring Boot cuando se despliega
 * en un servidor externo compatible con Servlet.
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Configura la aplicación Spring Boot indicando cuál es su clase principal.
	 *
	 * @param application objeto utilizado para configurar la aplicación.
	 * @return configuración de la aplicación con la clase principal establecida.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProyectobasesApplication.class);
	}

}