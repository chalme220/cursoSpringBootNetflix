package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


//se comunicara con el otro proyecto a traves de RestTemplate
//spring-cloud-starter-openfeign en vez de RestTemplate
//RibbonClient en singular porque solo tenemos un cliente (Clase)
@EnableEurekaClient
//@RibbonClient(name = "servicio-productos")
@EnableCircuitBreaker //Hystrix
@EnableFeignClients
@SpringBootApplication
//añadido para evitar el error de autoconfiguración, si necesitamos configurar la BD la comentamos
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemApplication.class, args);
	}

	//añadimos en el pom el eureka discovery
	//añadir el servidor eureka en el application 
}
