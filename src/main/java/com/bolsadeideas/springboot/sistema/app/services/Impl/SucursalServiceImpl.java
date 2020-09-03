package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.sistema.app.entity.Sucursal;
import com.bolsadeideas.springboot.sistema.app.models.dao.ISucursalDao;
import com.bolsadeideas.springboot.sistema.app.services.ISucursalService;

@Service
public class SucursalServiceImpl implements ISucursalService {

	@Autowired
	private ISucursalDao sucursalDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> findAll() {
		return (List<Sucursal>) sucursalDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Sucursal> findAll(Pageable pageable) {
		return sucursalDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Sucursal findById(Long id) {
		return sucursalDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Sucursal save(Sucursal Sucursal) {
		return sucursalDao.save(Sucursal);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		sucursalDao.deleteById(id);
	}

}
