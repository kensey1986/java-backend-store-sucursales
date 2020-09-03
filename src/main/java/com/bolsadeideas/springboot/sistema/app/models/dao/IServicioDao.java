package com.bolsadeideas.springboot.sistema.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.sistema.app.entity.Servicio;






public interface IServicioDao extends JpaRepository<Servicio, Long>{
	
	@Query("select s from Servicio s where s.nombre like %?1%")
	public List<Servicio> findByNombre(String term);
	
	public List<Servicio> findByNombreContainingIgnoreCase(String term);
	
	public List<Servicio> findByNombreStartingWithIgnoreCase(String term);
}
