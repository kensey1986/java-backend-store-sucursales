package com.bolsadeideas.springboot.sistema.app.models.dao;
import com.bolsadeideas.springboot.sistema.app.entity.Bodega;
import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.entity.Sucursal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IBodegaDao extends JpaRepository<Bodega, Long>{
	
	public Bodega findByIdCompuesto(String idCompuesto);
        
        public List<Bodega> findByCreateAtBetween(Date f1, Date f2);
        
        public List<Bodega> findByFechaActualizacionBetween(Date f1, Date f2);
        
        
}
