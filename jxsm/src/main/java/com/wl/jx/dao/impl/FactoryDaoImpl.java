package com.wl.jx.dao.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.FactoryDao;
import com.wl.jx.domain.Factory;


@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao{
	public FactoryDaoImpl() {
		this.setNs("com.wl.jx.mapper.FactoryMapper.");			//设置命名空间
	}

	public void changeState(Map<String, Object> map) {
		this.getSqlSession().update((this.getNs() + "changeState"), map);
	}
}
