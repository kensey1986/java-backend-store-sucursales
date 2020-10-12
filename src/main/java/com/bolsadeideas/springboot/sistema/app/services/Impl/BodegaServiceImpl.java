package com.bolsadeideas.springboot.sistema.app.services.Impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.sistema.app.entity.Bodega;
import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.entity.Sucursal;
import com.bolsadeideas.springboot.sistema.app.models.dao.IBodegaDao;
import com.bolsadeideas.springboot.sistema.app.services.IBodegaService;

@Service
public class BodegaServiceImpl implements IBodegaService {

  
    @Autowired
    private IBodegaDao bodegaDao;


    @Override
    @Transactional(readOnly = true)
    public List<Bodega> findAll() {
        return (List<Bodega>) bodegaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Bodega> findAll(Pageable pageable) {
        return bodegaDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Bodega findById(Long id) {
        return bodegaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Bodega save(Bodega bodega) {
        return bodegaDao.save(bodega);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bodegaDao.deleteById(id);
    }

  
    //return productoDao.findByNombreStartingWithIgnoreCase(term);

    @Override
    @Transactional(readOnly = true)
    public Bodega findByIdCompuesto(String idCompuesto) {
            return bodegaDao.findByIdCompuesto(idCompuesto);
    }    

    @Override
    @Transactional(readOnly = true)
    public List<Bodega> findByCreateAtBetween(Date f1, Date f2) {
       return bodegaDao.findByCreateAtBetween(f1, f2);
    }

    @Override
    public List<Bodega> findByFechaActualizacionBetween(Date f1, Date f2) {
       return bodegaDao.findByFechaActualizacionBetween(f1, f2);
    }

   

   
}
