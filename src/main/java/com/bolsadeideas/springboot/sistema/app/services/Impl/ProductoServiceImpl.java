package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.sistema.app.entity.Categoria;
import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.models.dao.ICategoriaDao;
import com.bolsadeideas.springboot.sistema.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.sistema.app.services.IProductoService;



@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		return productoDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAllCategorias() {
		return  categoriaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		System.out.println("llegando clas impl " + term);
		return productoDao.findByNombre(term);
	}
                //return productoDao.findByNombreStartingWithIgnoreCase(term);
}
