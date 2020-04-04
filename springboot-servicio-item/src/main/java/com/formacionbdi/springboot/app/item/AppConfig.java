package com.formacionbdi.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//RestTemplateConfig //comentado para Ribbon y con Feign
@Configuration
public class AppConfig {

	//cliente http
	
	//guardamos el objeto con este nombre de bean
	@Bean("clienteRest")
	@LoadBalanced //mejor balanceo con Ribbon y RestTemplate
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
	
	//spring-cloud-starter-openfeign en vez de RestTemplate
	
	//Ribbon funcionara como balanceador de carga entre los dos proyectos utilizara el algoritomo Round Robin
	//en el properties:      servicio-productos.ribbon.listOfServers=localhost:8001,localhost:9001
}
