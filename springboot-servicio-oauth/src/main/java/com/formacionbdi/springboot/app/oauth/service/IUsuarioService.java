package com.formacionbdi.springboot.app.oauth.service;

import com.formacionbdi.springboot.app.commons.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public Usuario update(Usuario usuario,  Long id);
}
