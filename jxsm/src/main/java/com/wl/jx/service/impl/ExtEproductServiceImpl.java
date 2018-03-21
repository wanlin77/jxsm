package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.ExtEproductDao;
import com.wl.jx.domain.ExtEproduct;
import com.wl.jx.service.ExtEproductService;


@Service
public class ExtEproductServiceImpl implements ExtEproductService {
	@Autowired
	ExtEproductDao extEproductDao;
	
	public List<ExtEproduct> find(ExtEproduct extEproduct) {
		return extEproductDao.find(extEproduct);
	}

	public ExtEproduct get(Serializable id) {
		return extEproductDao.get(id);
	}

	public void insert(ExtEproduct extEproduct) {
		extEproductDao.insert(extEproduct);
	}

	public void update(ExtEproduct extEproduct) {
		extEproductDao.update(extEproduct);
	}

	public void delete(Serializable id) {
		extEproductDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		extEproductDao.deleteBatch(ids);;
	}

}
