package com.formacionbdi.springboot.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringbootServicioEurekaServerApplication {
	//añadimos en el properties    org.glassfish.jaxb
	//lanzamos el proyecto en el puerto 8761
	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioEurekaServerApplication.class, args);
	}

	//eureka trae consigo ya configurado Ribbon por lo que podemos quitarlo de los otros proyectos
	//tanto en el application como en el pom
	
	//HISTRIX (logica Circuit breaker) gestiona errores en nuestro microservicios y añade logica de timeouts, errores y lo gestionaria
	//llamando a otra instancia evitando errores en cascada en ITEM
	
	//Zull se dedicara al acceso al resto como una puerta de enlace
	//tiene un enrutamiento dinamico que estan registrado en Eureka y Ribbon
	//ademas tiene filtros como de seguridad de ruta a los servicios
	//y balanceamiento de carga, monitorizacion de metricas etc.
}
