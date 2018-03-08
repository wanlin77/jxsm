package com.wl.jx.dao.impl;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ExtCproductDao;
import com.wl.jx.domain.ExtCproduct;


@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao{
	public ExtCproductDaoImpl() {
		this.setNs("com.wl.jx.mapper.ExtCproductMapper.");			//设置命名空间
	}
}
