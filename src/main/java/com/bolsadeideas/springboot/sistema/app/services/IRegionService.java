package com.bolsadeideas.springboot.sistema.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.bolsadeideas.springboot.sistema.app.entity.Region;



public interface IRegionService {

	public List<Region> findAll();
	
	public Page<Region> findAll(Pageable pageable);
	
	public Region findById(Long id);
	
	public Region save(Region Region);
	
	public void delete(Long id);
	
	

}
