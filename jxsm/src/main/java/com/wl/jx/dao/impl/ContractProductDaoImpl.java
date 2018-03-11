package com.wl.jx.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ContractProductDao;
import com.wl.jx.domain.ContractProduct;


@Repository
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao{
	public ContractProductDaoImpl() {
		this.setNs("com.wl.jx.mapper.ContractProductMapper.");			//设置命名空间
	}
	
	//根据合同号删除货物
	public void deleteByContractId(Serializable contractId) {
		this.getSqlSession().delete(this.getNs() + "deleteByContractId", contractId);
	}
}
