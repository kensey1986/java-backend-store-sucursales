package com.bolsadeideas.springboot.sistema.app.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.bolsadeideas.springboot.sistema.app.entity.Bodega;


 

public interface IBodegaService {

	public List<Bodega> findAll();
	
	public Page<Bodega> findAll(Pageable pageable);
	
	public Bodega findById(Long id);
	
	public Bodega save(Bodega Bodega);
	
	public void delete(Long id);
	

}
