package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.ContractDao;
import com.wl.jx.dao.ContractProductDao;
import com.wl.jx.dao.ExtCproductDao;
import com.wl.jx.domain.Contract;
import com.wl.jx.service.ContractService;
import com.wl.jx.vo.OutProduct;


@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	ContractDao contractDao;
	@Autowired
	ContractProductDao contractProductDao;
	@Autowired
	ExtCproductDao extCpruductDao;
	
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
		
		for (Serializable contractId : ids) {
			extCpruductDao.deleteByContractId(contractId);
		}
		
		for (Serializable contractId : ids) {
			contractProductDao.deleteByContractId(contractId);
		}
		
		contractDao.deleteBatch(ids);
	}
	
	public void changeState(Map<String, Object> map) {
		contractDao.changeState(map);
	}

	public List<OutProduct> findOutProduct(Serializable inputDate) {
		return contractDao.findOutProduct(inputDate);
	}

	public List<String> getExtName(Serializable contractProductId) {
		return contractDao.getExtName(contractProductId);
	}

}
