package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.sistema.app.entity.Factura;
import com.bolsadeideas.springboot.sistema.app.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.sistema.app.services.IFacturaService;

@Service
public class FacturaServiceImpl implements IFacturaService {

    @Autowired
    private IFacturaDao facturaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Factura> findAll() {
        return (List<Factura>) facturaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Factura> findAll(Pageable pageable) {
        return facturaDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findById(Long id) {
        return facturaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Factura save(Factura factura) {
        return facturaDao.save(factura);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        facturaDao.deleteById(id);
    }

    @Override
    public List<Factura> findFirstByOrderByIdDesc() {
        return facturaDao.findFirstByOrderByIdDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura> findByCreateAtBetween(Date f1, Date f2) {
        return facturaDao.findByCreateAtBetween(f1, f2);
    }

}
