package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.sistema.app.entity.Categoria;
import com.bolsadeideas.springboot.sistema.app.entity.Servicio;
import com.bolsadeideas.springboot.sistema.app.models.dao.ICategoriaDao;
import com.bolsadeideas.springboot.sistema.app.models.dao.IServicioDao;
import com.bolsadeideas.springboot.sistema.app.services.IServicioService;



@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IServicioDao servicioDao;
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> findAll() {
		return (List<Servicio>) servicioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Servicio> findAll(Pageable pageable) {
		return servicioDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Servicio findById(Long id) {
		return servicioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Servicio save(Servicio servicio) {
		return servicioDao.save(servicio);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		servicioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAllCategorias() {
		return  categoriaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> findByNombre(String term) {
		
		return servicioDao.findByNombreStartingWithIgnoreCase(term);
	}

}
