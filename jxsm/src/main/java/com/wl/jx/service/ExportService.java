package com.wl.jx.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wl.jx.domain.Export;

public interface ExportService {
	public List<Export> find(Export export);
	public Export get(Serializable id);
	public void insert(Export export);
	public void update(Map<String, Object> map);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	
	public String getHTMLString(String exportId);
}

