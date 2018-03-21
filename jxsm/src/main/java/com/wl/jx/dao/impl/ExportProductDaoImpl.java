package com.wl.jx.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ExportProductDao;
import com.wl.jx.domain.ExportProduct;


@Repository
public class ExportProductDaoImpl extends BaseDaoImpl<ExportProduct> implements ExportProductDao{
	public ExportProductDaoImpl() {
		this.setNs("com.wl.jx.mapper.ExportProductMapper.");			//设置命名空间
	}

	@Override
	public List<ExportProduct> findByExportId(Serializable exportId) {
		return this.getSqlSession().selectList(this.getNs()+"findByExportId", exportId);
	}
	
}
