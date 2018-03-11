package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.ContractProductDao;
import com.wl.jx.dao.ExtCproductDao;
import com.wl.jx.domain.ContractProduct;
import com.wl.jx.service.ContractProductService;


@Service
public class ContractProductServiceImpl implements ContractProductService {
	@Autowired
	ContractProductDao contractProductDao;
	@Autowired
	ExtCproductDao extCproductDao;
	
	public List<ContractProduct> find(ContractProduct contractProduct) {
		return contractProductDao.find(contractProduct);
	}

	public ContractProduct get(Serializable id) {
		return contractProductDao.get(id);
	}

	public void insert(ContractProduct contractProduct) {
		contractProductDao.insert(contractProduct);
	}

	public void update(ContractProduct contractProduct) {
		contractProductDao.update(contractProduct);
	}

	public void delete(Serializable id) {
		extCproductDao.deleteByContractProductId(id);
		contractProductDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		contractProductDao.deleteBatch(ids);;
	}

}
