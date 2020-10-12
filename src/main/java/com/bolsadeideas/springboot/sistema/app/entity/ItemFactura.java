package com.bolsadeideas.springboot.sistema.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;

@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(name = "precio_vendido")
    private Double precioVendido;

    @Column(name = "precio_comprado")
    private Double precioComprado;
    
    @Column(name = "des_porcentaje")
    private Double desPorcentaje;
    
    @Column(name = "des_dinero")
    private Double desDinero;

    private Double importe;

    /*
    public Double getImporte() {
        return cantidad.doubleValue() * producto.getPrecio();
    }

    public Double getGanancia() {
        return cantidad.doubleValue() * (producto.getPrecio() - producto.getPrecioCompra());
    }
     */
    // < -- metodos get y set Inicio-->

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
    

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double getPrecioVendido() {
        return precioVendido;
    }

    public void setPrecioVendido(Double precioVendido) {
        this.precioVendido = precioVendido;
    }

    public Double getPrecioComprado() {
        return precioComprado;
    }

    public void setPrecioComprado(Double precioComprado) {
        this.precioComprado = precioComprado;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getDesPorcentaje() {
        return desPorcentaje;
    }

    public void setDesPorcentaje(Double desPorcentaje) {
        this.desPorcentaje = desPorcentaje;
    }

    public Double getDesDinero() {
        return desDinero;
    }

    public void setDesDinero(Double desDinero) {
        this.desDinero = desDinero;
    }

    
    // < -- metodos get y set Fin-->
    private static final long serialVersionUID = 1L;
}
