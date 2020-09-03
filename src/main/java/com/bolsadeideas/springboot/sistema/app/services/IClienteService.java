package com.bolsadeideas.springboot.sistema.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;




public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public List<Cliente> findByNombre(String term);
	
	public List<Cliente> findByDocumento(String term);

}
