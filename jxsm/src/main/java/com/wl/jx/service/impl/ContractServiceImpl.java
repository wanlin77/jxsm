package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.ContractDao;
import com.wl.jx.domain.Contract;
import com.wl.jx.service.ContractService;


@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	ContractDao contractDao;
	
	public List<Contract> find(Contract contract) {
		return contractDao.find(contract);
	}
	
	public com.wl.jx.vo.Contract view(Serializable id) {
		return contractDao.view(id);
	}

	public Contract get(Serializable id) {
		return contractDao.get(id);
	}

	public void insert(Contract contract) {
		contractDao.insert(contract);
	}

	public void update(Contract contract) {
		contractDao.update(contract);
	}

	public void delete(Serializable id) {
		contractDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		contractDao.deleteBatch(ids);;
	}

}
