package com.bolsadeideas.springboot.sistema.app.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;
import com.bolsadeideas.springboot.sistema.app.entity.Factura;
import com.bolsadeideas.springboot.sistema.app.entity.FacturaServicio;




public interface IFacturaServicioService {

	public List<FacturaServicio> findAll();
	
	public Page<FacturaServicio> findAll(Pageable pageable);
	
	public FacturaServicio findById(Long id);
	
	public FacturaServicio save(FacturaServicio facturaServicio);
	
	public void delete(Long id);
	
	
	
	public List<Cliente> findByDescripcion(String descripcion);
	
	public List<FacturaServicio> buscarPorRangosFecha(Date f1, Date f2);
	
	public List<FacturaServicio> buscarPorFecha(Date f1);
	
	

}
