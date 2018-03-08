package com.wl.jx.service;

import java.io.Serializable;
import java.util.List;

import com.wl.jx.domain.ContractProduct;

public interface ContractProductService {
	public List<ContractProduct> find(ContractProduct contractProduct);
	public ContractProduct get(Serializable id);
	public void insert(ContractProduct contractProduct);
	public void update(ContractProduct contractProduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}

