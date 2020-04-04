package com.formacionbdi.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

//import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

//REPOSITORY / DAO
public interface ProductoDao extends CrudRepository<Producto, Long> {
	//en tiempo de ejecucion
	
	
}
