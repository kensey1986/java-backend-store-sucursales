package com.bolsadeideas.springboot.sistema.app.models.dao;

import com.bolsadeideas.springboot.sistema.app.entity.Bodega;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.sistema.app.entity.Reporte;

public interface IReporteDao extends JpaRepository<Reporte, Long> {

    public List<Reporte> findByCreateAtBetween(Date f1, Date f2);
    

}
