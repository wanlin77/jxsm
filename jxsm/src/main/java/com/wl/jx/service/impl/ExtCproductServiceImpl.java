package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.ExtCproductDao;
import com.wl.jx.domain.ExtCproduct;
import com.wl.jx.service.ExtCproductService;


@Service
public class ExtCproductServiceImpl implements ExtCproductService {
	@Autowired
	ExtCproductDao extCproductDao;
	
	public List<ExtCproduct> find(ExtCproduct extCproduct) {
		return extCproductDao.find(extCproduct);
	}

	public ExtCproduct get(Serializable id) {
		return extCproductDao.get(id);
	}

	public void insert(ExtCproduct extCproduct) {
		extCproductDao.insert(extCproduct);
	}

	public void update(ExtCproduct extCproduct) {
		extCproductDao.update(extCproduct);
	}

	public void delete(Serializable id) {
		extCproductDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		extCproductDao.deleteBatch(ids);;
	}

}
