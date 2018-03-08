package com.wl.jx.service;

import java.io.Serializable;
import java.util.List;

import com.wl.jx.domain.Contract;

public interface ContractService {
	public List<Contract> find(Contract contract);
	public com.wl.jx.vo.Contract view(Serializable id);
	public Contract get(Serializable id);
	public void insert(Contract contract);
	public void update(Contract contract);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}

