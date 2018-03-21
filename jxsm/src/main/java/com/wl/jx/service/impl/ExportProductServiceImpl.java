package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.ExportProductDao;
import com.wl.jx.dao.ExtCproductDao;
import com.wl.jx.domain.ExportProduct;
import com.wl.jx.service.ExportProductService;


@Service
public class ExportProductServiceImpl implements ExportProductService {
	@Autowired
	ExportProductDao exportProductDao;
	@Autowired
	ExtCproductDao extCproductDao;
	
	public List<ExportProduct> find(ExportProduct exportProduct) {
		return exportProductDao.find(exportProduct);
	}

	public ExportProduct get(Serializable id) {
		return exportProductDao.get(id);
	}

	public void insert(ExportProduct exportProduct) {
		exportProductDao.insert(exportProduct);
	}

	public void update(ExportProduct exportProduct) {
		exportProductDao.update(exportProduct);
	}

	public void delete(Serializable id) {
		exportProductDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		exportProductDao.deleteBatch(ids);;
	}

}
