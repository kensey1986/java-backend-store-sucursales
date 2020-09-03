package com.bolsadeideas.springboot.sistema.app.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.sistema.app.entity.Role;
import com.bolsadeideas.springboot.sistema.app.entity.Usuario;



public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);
	
	
	
	
	
	@Query("from Role")
	public List<Role> findAllRoles();
	

	

}
