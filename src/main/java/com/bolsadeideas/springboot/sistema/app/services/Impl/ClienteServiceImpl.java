package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;

import com.bolsadeideas.springboot.sistema.app.models.dao.IClienteDao;

import com.bolsadeideas.springboot.sistema.app.services.IClienteService;



@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByNombre(String term) {
		
		return clienteDao.findByNombreStartingWithIgnoreCase(term);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByDocumento(String term) {
		return clienteDao.findByDocumentoStartingWithIgnoreCase(term);
	}

}
