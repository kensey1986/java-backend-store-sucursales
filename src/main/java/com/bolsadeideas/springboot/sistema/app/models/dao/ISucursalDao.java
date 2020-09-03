package com.bolsadeideas.springboot.sistema.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;



import com.bolsadeideas.springboot.sistema.app.entity.Sucursal;

public interface ISucursalDao extends JpaRepository<Sucursal, Long>{

	/*
	@Query("from Region")
	public List<Region> findAllRegiones();
	*/
}
