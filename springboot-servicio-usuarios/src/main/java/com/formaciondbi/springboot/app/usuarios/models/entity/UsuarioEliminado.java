package com.formaciondbi.springboot.app.usuarios.models.entity;

//para recomendado por hibernate y serializarlo o convertirlo a bytes
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class UsuarioEliminado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;
	@Column(length = 60)
	private String password;

	private Boolean enabled;

	private String nombre;
	private String apellido;

	@Column(unique = true, length = 100)
	private String email;

	//relacion de cardinalidad y se creara por debajo una tabla intermedia que contendra la llave foranea de cada una. pero se podra customizar
	//el tipo de fetch por defecto es EAGER que trae todo pero con LIZY en la consulta solo trae al usuario.

	//podemos indicar el nombre de la tabla intermedia con jointable
	//uniqueConstraints decimos que no puede haber un mismo usuario con roles repetidos y sea unico
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuarios_roles", 
	joinColumns = @JoinColumn(name="usuario_id"), 
	inverseJoinColumns = @JoinColumn(name="rol_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"usuario_id", "rol_id"})})
	private List<RolEliminado> roles;
	
	
	//consulta mediante un proxy por el fetch.lazy para que no levante toda la bd y no haya sobrecarga.
	public List<RolEliminado> getRoles() {
		return roles;
	}

	public void setRoles(List<RolEliminado> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}