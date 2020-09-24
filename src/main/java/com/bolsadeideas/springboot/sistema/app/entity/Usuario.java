package com.bolsadeideas.springboot.sistema.app.entity;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author ken
 *
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio, ni contener letras o Simbolos")
    @Size(min = 8, max = 10, message = "el tamaño debe estar entre 8 y 10 caracteres")
    @Column(nullable = false, unique = true)
    private String documento;

    @Column(unique = true, length = 20)
    private String username;

    //@NotEmpty(message = "No puede estar vacio")
    //@Size(min = 8, max = 60, message = "el tamaño debe estar entre 8 y 20 caracteres")
    @Column(nullable = false)
    private String password;

    private Boolean enabled;

    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 3, max = 20, message = "el tamaño debe estar entre 3 y 20 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 3, max = 20, message = "el tamaño debe estar entre 3 y 20 caracteres")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 3, max = 50, message = "el tamaño debe estar entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String direccion;

    @Size(min = 7, max = 7, message = "el tamaño debe estar entre 7 y 7 caracteres")
    private String telefono;

    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 10, max = 10, message = "el tamaño debe estar entre 10 y 10 caracteres")
    @Column(nullable = false, unique = true)
    private String celular1;

    @Size(min = 10, max = 10, message = "el tamaño debe estar entre 10 y 10 caracteres")
    private String celular2;

    @Column(unique = true)
    private String email;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Fecha de nacimiento No puede ser Vacia")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String foto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    
    // <-- inicio relacion entre tablas -->
     /*
    @JsonIgnoreProperties(value = {"usuario", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Usuario() {
        this.facturas = facturas;
    }
  */
   
    
    
    
    @NotNull(message = "El Sucursal No Puede Ser invalido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sucursal sucursal;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<Role> roles;

   
    @NotNull(message = "El Barrio No Puede Ser invalido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;
    
    

    // <-- fin relacion entre tablas -->
    // < -- metodos get y set Inicio-->
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }


    // < -- metodos get y set Fin-->
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
