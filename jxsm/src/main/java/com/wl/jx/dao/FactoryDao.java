package com.wl.jx.dao;

import java.util.Map;

import com.wl.jx.domain.Factory;


public interface FactoryDao extends BaseDao<Factory> {
	public void changeState(Map<String,Object> map);
}
