package com.wl.jx.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wl.jx.domain.Contract;
import com.wl.jx.vo.OutProduct;

public interface ContractService {
	public List<Contract> find(Contract contract);
	public com.wl.jx.vo.Contract view(Serializable id);
	public Contract get(Serializable id);
	public void insert(Contract contract);
	public void update(Contract contract);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	
	public void changeState(Map<String,Object> map);
	public List<OutProduct> findOutProduct(Serializable inputDate);
	public List<String> getExtName(Serializable contractProductId);
}

