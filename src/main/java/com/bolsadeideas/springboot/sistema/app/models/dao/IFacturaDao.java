package com.bolsadeideas.springboot.sistema.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;
import com.bolsadeideas.springboot.sistema.app.entity.Factura;
import com.bolsadeideas.springboot.sistema.app.entity.Sucursal;



public interface IFacturaDao extends JpaRepository<Factura, Long>{
        
        public List<Factura> findByCreateAtBetween(Date f1, Date f2);
        
	
        public List<Factura> findFirstByOrderByIdDesc();
        
        // public List<Factura> findFirstByOrderByIdDescAndSucursal(Sucursal sucursal);
	
}
