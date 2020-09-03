package com.bolsadeideas.springboot.sistema.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.sistema.app.entity.Categoria;






public interface ICategoriaDao extends JpaRepository<Categoria, Long>{

	/*
	@Query("from Region")
	public List<Region> findAllRegiones();
	*/
}
