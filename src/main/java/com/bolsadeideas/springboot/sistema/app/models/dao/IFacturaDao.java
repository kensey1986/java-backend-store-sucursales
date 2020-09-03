package com.bolsadeideas.springboot.sistema.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;
import com.bolsadeideas.springboot.sistema.app.entity.Factura;



public interface IFacturaDao extends JpaRepository<Factura, Long>{

	
	@Query("select f from Factura f where f.createAt between ?1 and ?2")
	public List<Factura> buscarPorRangosFecha(Date f1, Date f2);
	
	@Query("select f from Factura f where f.createAt =?1")
	public List<Factura> buscarPorFecha(Date f1);
	
        
	
        public List<Factura> findFirstByOrderByIdDesc();
       
	@Query("select f from Factura f where f.descripcion like %?1%")
	public List<Cliente> findByDescripcion(String descripcion);
	
	public List<Cliente> findByDescripcionStartingWithIgnoreCase(String descripcion);
	
	
	
	
}
