package com.formacionbdi.springboot.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//habilitamos eureka y zuul
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SpringbootServicioZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioZuulServerApplication.class, args);
	}

	//Filtros de zuul que manejan el ciclo de vida
	//pre -> antes de que sea enrutado la ruta antes de su enrutamiento y pasa datos
	//post -> se ejectura despues del request haya sido enrutado y manipula la respuesta las cabeceras
	//route -> para enrutar el request y comunica con el servicio
	
	//usa Ribbon, Hystrix, y un cliente Http
	
}
