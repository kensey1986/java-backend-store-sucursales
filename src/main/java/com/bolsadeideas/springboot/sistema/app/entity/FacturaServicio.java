package com.bolsadeideas.springboot.sistema.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "factura_servicios")
public class FacturaServicio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 3, max = 20, message = "el tamaño debe estar entre 3 y 20 caracteres")
	@Column(nullable = false)*/
	private String descripcion;

	private String observacion;
	
	private Double descuento;
	
	@Column(name = "total_ganancia")
	private Double totalGanancia;
	
	
	
	//@NotNull(message = "No Hay Usuario valido")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario usuario;	
	
	//@Temporal(TemporalType.DATE)
	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@JsonIgnoreProperties(value={"facturas", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@JsonIgnoreProperties(value={ "hibernateLazyInitializer", "handler" }, allowSetters=true)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_servicio_id")
	private List<ItemFacturaServicio> itemsServicio;

	public FacturaServicio() {
		itemsServicio = new ArrayList<>();
	}
	
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	
	/**** operaciones ***/
	public Double getTotal() {
		Double total = 0.00;
		for (ItemFacturaServicio item : itemsServicio) {
			total += item.getImporte();
		}
		if(descuento!=null) {
			total = total-descuento;
					
		}
		return total;
	}
	
	public Double getTotalGanancia() {
		Double total = 0.00;
		for (ItemFacturaServicio item : itemsServicio) {
			total += item.getGanancia();
		}
		if(descuento!=null) {
			total = total-descuento;
		}
		return total;
	}
	
	
	/*
	 * metodos get y set
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	

	
	public List<ItemFacturaServicio> getItemsServicio() {
		return itemsServicio;
	}


	public void setItemsServicio(List<ItemFacturaServicio> itemsServicio) {
		this.itemsServicio = itemsServicio;
	}


	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public void setTotalGanancia(Double totalGanancia) {
		this.totalGanancia = totalGanancia;
	}




	private static final long serialVersionUID = 1L;
}
