package com.formaciondbi.springboot.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.formacionbdi.springboot.app.commons.models.entity.Rol;
import com.formacionbdi.springboot.app.commons.models.entity.Usuario;


@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	//sobreescrita no exigida por el implement es opcional solo si necesitamos los id en nuestro json
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Rol.class);
	}
	
}
