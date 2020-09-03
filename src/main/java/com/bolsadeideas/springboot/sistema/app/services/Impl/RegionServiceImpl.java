package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.sistema.app.entity.Region;
import com.bolsadeideas.springboot.sistema.app.models.dao.IRegionDao;
import com.bolsadeideas.springboot.sistema.app.services.IRegionService;

@Service
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Region> findAll() {
		return (List<Region>) regionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Region> findAll(Pageable pageable) {
		return regionDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Region findById(Long id) {
		return regionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Region save(Region Region) {
		return regionDao.save(Region);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		regionDao.deleteById(id);
	}

}
