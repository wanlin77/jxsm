package com.wl.jx.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wl.jx.domain.Factory;

public interface FactoryService {
	public List<Factory> find(Factory factory);
	public Factory get(Serializable id);
	public void insert(Factory factory);
	public void update(Factory factory);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	
	public void changeState(Map<String,Object> map);
}

