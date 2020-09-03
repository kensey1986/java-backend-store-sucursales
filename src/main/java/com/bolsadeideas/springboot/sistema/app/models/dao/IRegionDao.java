package com.bolsadeideas.springboot.sistema.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;


import com.bolsadeideas.springboot.sistema.app.entity.Region;

public interface IRegionDao extends JpaRepository<Region, Long>{

	/*
	@Query("from Region")
	public List<Region> findAllRegiones();
	*/
}
