package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;
import com.bolsadeideas.springboot.sistema.app.entity.FacturaServicio;
import com.bolsadeideas.springboot.sistema.app.models.dao.IFacturaServicioDao;
import com.bolsadeideas.springboot.sistema.app.services.IFacturaServicioService;


@Service
public class FacturaServicioServiceImpl implements IFacturaServicioService {

	@Autowired
	private IFacturaServicioDao facturaServicioDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<FacturaServicio> findAll() {
		return (List<FacturaServicio>) facturaServicioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FacturaServicio> findAll(Pageable pageable) {
		return facturaServicioDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public FacturaServicio findById(Long id) {
		return facturaServicioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public FacturaServicio save(FacturaServicio facturaServicio) {
		return facturaServicioDao.save(facturaServicio);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		facturaServicioDao.deleteById(id);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<FacturaServicio> buscarPorRangosFecha(Date f1, Date f2) {
			return facturaServicioDao.buscarPorRangosFecha(f1, f2);
	}

	
	 @Override
	@Transactional(readOnly = true)
	public List<Cliente> findByDescripcion(String descripcion) {
			return facturaServicioDao.findByDescripcionStartingWithIgnoreCase(descripcion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FacturaServicio> buscarPorFecha(Date f1) {
			return facturaServicioDao.buscarPorFecha(f1);
	}
	 
	

	

}
