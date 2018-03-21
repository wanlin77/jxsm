package com.wl.jx.service;

import java.io.Serializable;
import java.util.List;

import com.wl.jx.domain.ExportProduct;


public interface ExportProductService {
	public List<ExportProduct> find(ExportProduct exprotProduct);
	public ExportProduct get(Serializable id);
	public void insert(ExportProduct exprotProduct);
	public void update(ExportProduct exprotProduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	
}

