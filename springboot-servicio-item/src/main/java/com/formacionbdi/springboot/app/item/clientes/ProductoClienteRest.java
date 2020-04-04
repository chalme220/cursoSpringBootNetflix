package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

//@FeignClient(name = "servicio-producto", url="localhost:8001")
//con riboon y modificamos el application.properties y eliminamos la url e indicamos
//el nombre del servicio en la clase principal del proyecto
//servicio-productos.ribbon.listOfServers=localhost:8001,localhost:9001 (en Properties)
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	//indicamos la ruta para consumir el servicio destino
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	//iguales que en ProductoController con mucho menos codigo que con restTemplate
	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto body);
		
	@PutMapping("/editar/{id}")
	public Producto update(@RequestBody Producto body, @PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void delete( @PathVariable Long id);
}
