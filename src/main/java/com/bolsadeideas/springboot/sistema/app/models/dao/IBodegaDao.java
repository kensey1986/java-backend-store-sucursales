package com.bolsadeideas.springboot.sistema.app.models.dao;
import com.bolsadeideas.springboot.sistema.app.entity.Bodega;
import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.entity.Sucursal;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IBodegaDao extends JpaRepository<Bodega, Long>{
	
	public Bodega findByProductoAndSucursal(Producto producto, Sucursal sucursal);
}
