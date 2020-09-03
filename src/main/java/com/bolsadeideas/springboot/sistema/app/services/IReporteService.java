package com.bolsadeideas.springboot.sistema.app.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.entity.Reporte;




public interface IReporteService {

	public List<Reporte> findAll();
	
	public Page<Reporte> findAll(Pageable pageable);
	
	public Reporte findById(Long id);
	
	public Reporte save(Reporte reporte);
	
	public void delete(Long id);
	
	
	
	public List<Producto> findByNombre(String descripcion);
	
	public List<Reporte> buscarPorRangosFecha(Date f1, Date f2);
	
	public List<Reporte> buscarPorFecha(Date f1);
	
	public List<Reporte> findFirstByOrderByIdDesc();

}
