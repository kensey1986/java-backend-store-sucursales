package com.bolsadeideas.springboot.sistema.app.models.dao;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bolsadeideas.springboot.sistema.app.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long>{
	
	@Query("select p from Producto p where (p.nombre || p.codigo) like %?1%")
	public List<Producto> findByNombre(String term);
	
	
	public List<Producto> findByCreateAtBetween(Date f1, Date f2);
	
	
	//public List<Producto> findByNombreContainingIgnoreCase(String term);
	
	//public List<Producto> findByNombreStartingWithIgnoreCase(String term);
}
