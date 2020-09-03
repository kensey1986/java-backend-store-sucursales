package com.bolsadeideas.springboot.sistema.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "servicios")
public class Servicio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
		
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 3, max = 50, message = "el tamaño debe estar entre 3 y 50 caracteres")
	@Column(nullable = false)
	private String nombre;
	
	
	@Column(name = "costo_repuesto")
	private Double costoRepuesto;
	
	/*
	 * @NotEmpty(message = "No puede estar vacio")
	@Size(min = 3, max = 10, message = "el tamaño debe estar entre 3 y 10 caracteres")
	 * 
	 */
	//@NotNull(message="El precio De  es obligatorio")
	@Column(name = "costo_servicio")
	private Double costoServicio;
	
	
	
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
	@NotNull(message = "La CATEGORIA No Puede Ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Categoria categoria;
	*/

	
	
	/*
	 * metodos get y set ************************************************************************
	 */
	public void setId(Long id) {
		this.id = id;
	}

		

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

				
	
	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Long getId() {
		return id;
	}



	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	





	public Double getCostoRepuesto() {
		return costoRepuesto;
	}



	public void setCostoRepuesto(Double costoRepuesto) {
		this.costoRepuesto = costoRepuesto;
	}



	public Double getCostoServicio() {
		return costoServicio;
	}



	public void setCostoServicio(Double costoServicio) {
		this.costoServicio = costoServicio;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
