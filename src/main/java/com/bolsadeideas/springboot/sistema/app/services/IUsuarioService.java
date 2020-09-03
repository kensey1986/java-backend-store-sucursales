package com.bolsadeideas.springboot.sistema.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.bolsadeideas.springboot.sistema.app.entity.Role;
import com.bolsadeideas.springboot.sistema.app.entity.Usuario;

public interface IUsuarioService {

	
	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
	
	
	public List<Role> findAllRoles();
	
	
	public Usuario findByUsername(String username);
}
