package com.bolsadeideas.springboot.sistema.app.models.dao;
import com.bolsadeideas.springboot.sistema.app.entity.Bodega;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IBodegaDao extends JpaRepository<Bodega, Long>{
	
	
}
