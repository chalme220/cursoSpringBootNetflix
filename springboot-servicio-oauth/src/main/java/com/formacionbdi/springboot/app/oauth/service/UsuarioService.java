package com.formacionbdi.springboot.app.oauth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.commons.models.entity.Usuario;
import com.formacionbdi.springboot.app.oauth.client.UsuarioFeignClient;

import brave.Tracer;
import feign.FeignException;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private UsuarioFeignClient client;

	// consumir rest por Feing
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			log.info("El usuario '" + username + "' en el sistema");
			Usuario usuario = client.findByUsername(username);
			log.error("Aqui ");

			List<GrantedAuthority> authorities = usuario.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getNombre()))
					// mostrar el nombre
					.peek(authority -> log.info("Role: " + authority.getAuthority()))// string con el nombre del rol
					.collect(Collectors.toList());
			log.info("Usuario autenticado: " + username);

			return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
					authorities);// comprueba si el usuario esta habilitado

		} catch (FeignException e) {
			log.error("Error en el login, no existe el usuario '" + username + "' en el sistema");
			
			tracer.currentSpan().tag("error.mensaje", "Error en el login, no existe el usuario '" + username + "' en el sistema");
			
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario '" + username + "' en el sistema");
		}
	}

	@Override
	public Usuario findByUsername(String username) {

		return client.findByUsername(username);
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		return client.update(usuario, id);
	}

}
