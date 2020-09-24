package com.bolsadeideas.springboot.sistema.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotEmpty;
// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;


//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 3, max = 50, message = "el tamaño debe estar entre 3 y 50 caracteres")
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 1, max = 5, message = "el tamaño debe estar entre 1 y 5 caracteres")
    @Column(nullable = false, unique = true)
    private String codigo;

    private Double precio;

    /*
	 * @NotEmpty(message = "No puede estar vacio")
	@Size(min = 3, max = 10, message = "el tamaño debe estar entre 3 y 10 caracteres")
	 * 
     */
    @Column(name = "precio_compra")
    private Double precioCompra;

    private Integer cantidad;

    @Size(max = 150, message = "el tamaño maximo  es de 150 caracteres")
    private String descripcion;

    private String foto;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @JsonIgnoreProperties(value = {"producto", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Reporte> reportes;

    public Producto() {
        this.reportes = new ArrayList<Reporte>();
    }

    // < -- metodos get y set Inicio-->
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    // < -- metodos get y set Fin-->
    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
