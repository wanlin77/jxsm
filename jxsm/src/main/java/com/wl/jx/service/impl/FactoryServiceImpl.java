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

	/* (non-Javadoc)
	 * @see cn.itcast.jk.service.FactoryService#get(java.io.Serializable)
	 */
	public Factory get(Serializable id) {
		// TODO Auto-generated method stub
		return factoryDao.get(id);
	}

	/* (non-Javadoc)
	 * @see cn.itcast.jk.service.FactoryService#insert(cn.itcast.jk.domain.Factory)
	 */
	public void insert(Factory factory) {
		// TODO Auto-generated method stub
		factoryDao.insert(factory);
	}

	/* (non-Javadoc)
	 * @see cn.itcast.jk.service.FactoryService#update(cn.itcast.jk.domain.Factory)
	 */
	public void update(Factory factory) {
		// TODO Auto-generated method stub
		factoryDao.update(factory);
	}

	/* (non-Javadoc)
	 * @see cn.itcast.jk.service.FactoryService#delete(java.io.Serializable)
	 */
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		factoryDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see cn.itcast.jk.service.FactoryService#delete(java.io.Serializable[])
	 */
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		factoryDao.deleteBatch(ids);;
	}

	@Override
	public void changeState(Map<String, Object> map) {
		// TODO Auto-generated method stub
		factoryDao.changeState(map);
	}

}
