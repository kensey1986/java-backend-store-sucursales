package com.bolsadeideas.springboot.sistema.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.entity.Reporte;



public interface IReporteDao extends JpaRepository<Reporte, Long>{

	
	@Query("select f from Factura f where f.createAt between ?1 and ?2")
	public List<Reporte> buscarPorRangosFecha(Date f1, Date f2);
	
	@Query("select f from Factura f where f.createAt =?1")
	public List<Reporte> buscarPorFecha(Date f1);
	
        
	
        public List<Reporte> findFirstByOrderByIdDesc();
       
	@Query("select p from Reporte p where p.nombre like %?1%")
	public List<Producto> findByNombre(String nombre);
	
	public List<Producto> findByNombreStartingWithIgnoreCase(String nombre);
	
	
	
	
}
