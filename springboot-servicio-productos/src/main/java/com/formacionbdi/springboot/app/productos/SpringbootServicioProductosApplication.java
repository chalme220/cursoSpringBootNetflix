package com.formacionbdi.springboot.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
//añadido despues de commons -> indicando que busque esa clase entity y demases packages de ese proyecto(commons) u otros
@EntityScan({"com.formacionbdi.springboot.app.commons.models.entity"})
public class SpringbootServicioProductosApplication {
	//para modificar el puerto de despliegue en Run as:
	//y Argumentes -> Vm Argumentes -> -Dserver.port=9001
	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductosApplication.class, args);
	}
	//añadimos en el pom el eureka discovery con el spring-cloud-dependencies
	
	//automatizamos los puertos en el properties (antes    server.port=8001)
	//server.port=${PORT:0}
	
	//y la instancia en eureka (con lo que cada vez que ejecutemos creara una instancia distinta)
	//eureka.instance.instance-id="{spring.application.name}:${eureka.instance.instance_id:${random.value}}
	//con esto no me preocupo por los puertos ocupados ya que son gestionados por eureka y puedo
	//ejecutar tantas veces como quiera la misma instancia/app
}
