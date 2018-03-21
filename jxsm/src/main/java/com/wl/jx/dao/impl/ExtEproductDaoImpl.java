package com.wl.jx.dao.impl;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ExtEproductDao;
import com.wl.jx.domain.ExtEproduct;


@Repository
public class ExtEproductDaoImpl extends BaseDaoImpl<ExtEproduct> implements ExtEproductDao{
	public ExtEproductDaoImpl() {
		this.setNs("com.wl.jx.mapper.ExtEproductMapper.");			//设置命名空间
	}
	
}
