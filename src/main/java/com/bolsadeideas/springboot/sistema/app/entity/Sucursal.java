package com.bolsadeideas.springboot.sistema.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.persistence.OneToMany;

import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name = "sucursales")
public class Sucursal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 3, max = 20, message = "el tamaño debe estar entre 3 y 20 caracteres")
    @Column(nullable = false, unique = true)
    private String nombre;

    private String nit;

    private String direccion;

    private String telefono;

    private String celular1;

    private String celular2;

    private String propietario;

    private String sede;

    private String regimen;

    private String facebook;

    private String instagram;

    private String geoposicion;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    
    
    // <-- relacion entre tablas Inicio-->
    
    @JsonIgnoreProperties(value = {"sucursal", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Sucursal() {
        this.facturas = new ArrayList<Factura>();
    }
    
    // <-- relacion entre tablas Fin-->
    //<-- metodos get y set Inicio-->

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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getGeoposicion() {
        return geoposicion;
    }

    public void setGeoposicion(String geoposicion) {
        this.geoposicion = geoposicion;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
    
    
    
    
    //<-- metodos get y set Fin-->
    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
