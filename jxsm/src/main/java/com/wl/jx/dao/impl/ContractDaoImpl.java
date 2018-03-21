package com.wl.jx.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ContractDao;
import com.wl.jx.domain.Contract;
import com.wl.jx.vo.OutProduct;

@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao{
	public ContractDaoImpl() {
		this.setNs("com.wl.jx.mapper.ContractMapper.");			//设置命名空间
	}

	public com.wl.jx.vo.Contract view(Serializable id) {
		return this.getSqlSession().selectOne(this.getNs() + "view", id);
	}
	
	public void changeState(Map<String, Object> map) {
		this.getSqlSession().update((this.getNs() + "changeState"), map);
	}

	public List<OutProduct> findOutProduct(Serializable inputDate) {
		return this.getSqlSession().selectList(this.getNs() + "findOutProduct", inputDate);
	}

	public List<String> getExtName(Serializable contractProductId) {
		return this.getSqlSession().selectList(this.getNs()+"getExtName", contractProductId);
	}

	public List<Contract> findForHistory(Contract contract) {
		return this.getSqlSession().selectList(this.getNs()+"findForHistory", contract);
	}
}
