package com.bolsadeideas.springboot.sistema.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.sistema.app.entity.Categoria;




public interface ICategoriaService {

	public List<Categoria> findAll();
	
	public Page<Categoria> findAll(Pageable pageable);
	
	public Categoria findById(Long id);
	
	public Categoria save(Categoria categoria);
	
	public void delete(Long id);
	
	//public List<Producto> findAllProductos(Long id);
	

}
