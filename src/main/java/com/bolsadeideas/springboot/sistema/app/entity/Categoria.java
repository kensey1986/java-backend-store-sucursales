package com.bolsadeideas.springboot.sistema.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;







@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 1, max = 4, message = "el tamaño debe estar entre 1 y 4 caracteres")
	@Column(nullable = false, unique = false)
	private String codigo;
	
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 3, max = 20, message = "el tamaño debe estar entre 3 y 20 caracteres")
	@Column(nullable = false)
	private String nombre;
	
	
	@Size( max = 150, message = "el tamaño maximo  es de 150 caracteres")
	private String descripcion;
	
		
		
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	/*
	@OneToMany(mappedBy = "categoria")
	private List<Producto> producto ;*/
	
	/*
	 * metodos get y set
	 */

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
