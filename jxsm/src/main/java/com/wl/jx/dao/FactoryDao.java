package com.wl.jx.dao;

import java.util.List;
import java.util.Map;

import com.wl.jx.domain.Factory;


public interface FactoryDao extends BaseDao<Factory> {
	public List<Factory> combo();
	public void changeState(Map<String,Object> map);
}
