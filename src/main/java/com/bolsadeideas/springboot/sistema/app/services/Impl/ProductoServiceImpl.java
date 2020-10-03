package com.bolsadeideas.springboot.sistema.app.services.Impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.sistema.app.services.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Override
    public List<Producto> findByCreateAtBetween(Date f1, Date f2) {
        // TODO Auto-generated method stub
        return productoDao.findByCreateAtBetween(f1, f2);
    }

    @Autowired
    private IProductoDao productoDao;


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
    public List<Producto> findByNombre(String term) {
      
        return productoDao.findByNombre(term);
    }
    //return productoDao.findByNombreStartingWithIgnoreCase(term);
}
