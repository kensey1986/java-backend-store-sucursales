package com.bolsadeideas.springboot.sistema.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;
import com.bolsadeideas.springboot.sistema.app.entity.FacturaServicio;



public interface IFacturaServicioDao extends JpaRepository<FacturaServicio, Long>{

	
	@Query("select f from FacturaServicio f where f.createAt between ?1 and ?2")
	public List<FacturaServicio> buscarPorRangosFecha(Date f1, Date f2);
	
	@Query("select f from FacturaServicio f where f.createAt =?1")
	public List<FacturaServicio> buscarPorFecha(Date f1);
	
	

	@Query("select f from FacturaServicio f where f.descripcion like %?1%")
	public List<Cliente> findByDescripcion(String descripcion);
	
	public List<Cliente> findByDescripcionStartingWithIgnoreCase(String descripcion);
	
	
	
	
}
