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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import javax.persistence.Embeddable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author ken
 *
 */
@Entity
@Table(name = "bodegas")
public class Bodega implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
            
    
    @Column(nullable = false, unique = true)
    private String idCompuesto;
    
    private String nombre;
    private Integer cantidad;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_Actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    
    @Column(name = "precio_compra")
    private Double precioCompra;
    
     @Column(name = "precio_venta")
    private Double precioVenta;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }
    // <-- Relacion entre tablas Inicio -->
    
    @JsonIgnoreProperties(value = {"bodegas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Sucursal sucursal;
    
    @JsonIgnoreProperties(value = {"bodegas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    
    // <-- Relacion entre Tablas Fin -->

    // < -- metodos get y set Inicio-->

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCompuesto() {
        return idCompuesto;
    }

    public void setIdCompuesto(String idCompuesto) {
        this.idCompuesto = idCompuesto;
    }

    

   
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

  
    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    // < -- metodos get y set Fin-->
    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
