package com.wl.jx.dao.impl;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ExportDao;
import com.wl.jx.domain.Export;


@Repository
public class ExportDaoImpl extends BaseDaoImpl<Export> implements ExportDao{
	public ExportDaoImpl() {
		this.setNs("com.wl.jx.mapper.ExportMapper.");			//设置命名空间
	}

}
