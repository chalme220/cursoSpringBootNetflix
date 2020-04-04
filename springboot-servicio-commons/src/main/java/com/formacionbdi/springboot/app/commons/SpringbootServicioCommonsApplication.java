package com.formacionbdi.springboot.app.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//sobreescribimos la configuracion
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootServicioCommonsApplication {

	/* no se un proyecto
	 * necesitamos tener configurado generando el jar para agregarlo como dependencia en item y producto
	 * configurar java_home y path  C:\Program Files\Java\jdk1.8.0_181\bin
	 * generamos el jar con mvnw.cmd install
	 * si nos vamos a target lo encontraremos como snapshot y lo utilizaremos como dependencia en los otros proyectos
	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioCommonsApplication.class, args);
	}
	 */
	
	
}
