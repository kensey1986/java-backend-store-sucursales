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
// import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

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

    @Column(name = "numero_factura")
    private Integer numeroFactura;

    @Column(name = "total_ganancia")
    private Double totalGanancia;

    @Column(name = "total_factura")
    private Double totalFactura;
    
    // private String usuario;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
     // <-- inicio relacion entre tablas -->
    
 
    
    @JsonIgnoreProperties(value = {"facturas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
  

    @JsonIgnoreProperties(value = {"facturas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    
    @JsonIgnoreProperties(value = {"facturas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Sucursal sucursal;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> items;

    public Factura() {
        items = new ArrayList<>();
    }
    
     // <-- inicio relacion entre tablas -->
    

    /**
     * ** operaciones **
     *
     * public Double getTotal() { Double total = 0.00; for (ItemFactura item :
     * items) { total += item.getImporte(); } if (descuento != null) { total =
     * total - descuento;
     *
     * }
     * return total; }
     *
     * public Double getTotalGanancia() { Double total = 0.00; for (ItemFactura
     * item : items) { total += item.getGanancia(); } if (descuento != null) {
     * total = total - descuento; } return total; }
     */
    // < -- metodos get y set Inicio-->
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

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
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

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }

    public Double getTotalGanancia() {
        return totalGanancia;
    }

    public void setTotalGanancia(Double totalGanancia) {
        this.totalGanancia = totalGanancia;
    }

    public Double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
  
    
    
    // < -- metodos get y set Fin-->
    private static final long serialVersionUID = 1L;
}
