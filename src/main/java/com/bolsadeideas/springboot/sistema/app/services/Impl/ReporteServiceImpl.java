package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.entity.Reporte;
import com.bolsadeideas.springboot.sistema.app.models.dao.IReporteDao;
import com.bolsadeideas.springboot.sistema.app.services.IReporteService;


@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
	private IReporteDao reporteDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Reporte> findAll() {
		return (List<Reporte>) reporteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Reporte> findAll(Pageable pageable) {
		return reporteDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Reporte findById(Long id) {
		return reporteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Reporte save(Reporte reporte) {
		return reporteDao.save(reporte);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		reporteDao.deleteById(id);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Reporte> buscarPorRangosFecha(Date f1, Date f2) {
			return reporteDao.buscarPorRangosFecha(f1, f2);
	}

	
	 @Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String nombre) {
		
		return reporteDao.findByNombreStartingWithIgnoreCase(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reporte> buscarPorFecha(Date f1) {
		
		return reporteDao.buscarPorFecha(f1);
	}

	@Override
	public List<Reporte> findFirstByOrderByIdDesc() {
	
		return reporteDao.findFirstByOrderByIdDesc();
	}
	 
	

}
