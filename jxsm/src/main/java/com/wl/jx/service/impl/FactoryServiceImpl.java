package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.FactoryDao;
import com.wl.jx.domain.Factory;
import com.wl.jx.service.FactoryService;


@Service
public class FactoryServiceImpl implements FactoryService {
	@Autowired
	FactoryDao factoryDao;
	
	public List<Factory> find(Factory factory) {
		return factoryDao.find(factory);
	}

	public Factory get(Serializable id) {
		return factoryDao.get(id);
	}

	public void insert(Factory factory) {
		factoryDao.insert(factory);
	}

	public void update(Factory factory) {
		// TODO Auto-generated method stub
		factoryDao.update(factory);
	}

	public void delete(Serializable id) {
		factoryDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		factoryDao.deleteBatch(ids);;
	}

	public void changeState(Map<String, Object> map) {
		factoryDao.changeState(map);
	}

	public List<Factory> combo() {
		return factoryDao.combo();
	}

}
