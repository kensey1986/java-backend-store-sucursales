package com.bolsadeideas.springboot.sistema.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bolsadeideas.springboot.sistema.app.entity.Cliente;




public interface IClienteDao extends JpaRepository<Cliente, Long>{

	/*
	@Query("from Region")
	public List<Region> findAllRegiones();
	*/
	@Query("select c from Cliente c where c.nombre like %?1%")
	public List<Cliente> findByNombre(String term);
	
	public List<Cliente> findByNombreContainingIgnoreCase(String term);
	
	public List<Cliente> findByNombreStartingWithIgnoreCase(String term);
	
	@Query("select c from Cliente c where c.documento like %?1%")
	public List<Cliente> findByDocumento(String term);
	
	public List<Cliente> findByDocumentoStartingWithIgnoreCase(String term);
	
	
}
