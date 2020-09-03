package com.bolsadeideas.springboot.sistema.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.bolsadeideas.springboot.sistema.app.entity.Sucursal;



public interface ISucursalService {

	public List<Sucursal> findAll();
	
	public Page<Sucursal> findAll(Pageable pageable);
	
	public Sucursal findById(Long id);
	
	public Sucursal save(Sucursal Sucursal);
	
	public void delete(Long id);
	
	

}
