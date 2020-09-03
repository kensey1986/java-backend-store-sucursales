package com.bolsadeideas.springboot.sistema.app.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;
import com.bolsadeideas.springboot.sistema.app.entity.Factura;




public interface IFacturaService {

	public List<Factura> findAll();
	
	public Page<Factura> findAll(Pageable pageable);
	
	public Factura findById(Long id);
	
	public Factura save(Factura factura);
	
	public void delete(Long id);
	
	
	
	public List<Cliente> findByDescripcion(String descripcion);
	
	public List<Factura> buscarPorRangosFecha(Date f1, Date f2);
	
	public List<Factura> buscarPorFecha(Date f1);
	
	public List<Factura> findFirstByOrderByIdDesc();

}
