package com.formaciondbi.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.formacionbdi.springboot.app.commons.models.entity.Usuario;


//import com.formaciondbi.springboot.app.usuarios.models.entity.Usuario;


//heredamos de crudRepositoyry o extendemos de Pagin... que a suvez hereda de crudrepository pero tienen mas funcionalidades
//puede paginar y ordenar
//nos crea el api con dicho path
@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{

	//metodo personalizado y sobreescrito con RestResource
	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);
	
	//http://localhost:8090/api/usuarios/usuarios/search/findByUsername?username=admin
	//	public Usuario findByUsername(String username);
	
	//public Usuario findByUsernameAndEmail(String username, String email);
	//public Usuario findByUsernameOrEmail(String username, String email);
	
	//query por JPA luego se puede modificarla haciendola nativa.
	//OJO: en vez el nombre de la tabla se pone el nombre de la Entity
	@Query("select u from Usuario u where u.username=?1")
	public Usuario obtenerPorUserName(String username);
	
	//http://localhost:8090/api/usuarios/usuarios (get da una lista y post da un nuevo usuario, con put lo modifica)
	//http://localhost:8090/api/usuarios/usuarios/2 (get da el detalle y con delete lo elimina)
}
