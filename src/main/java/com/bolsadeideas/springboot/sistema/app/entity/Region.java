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
@Table(name = "regiones")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 3, max = 20, message = "el tamaño debe estar entre 3 y 20 caracteres")
    @Column(nullable = false, unique = true)
    private String nombre;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    // < -- metodos get y set Fin-->
    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
