package com.wl.jx.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ExtCproductDao;
import com.wl.jx.domain.ExtCproduct;


@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao{
	public ExtCproductDaoImpl() {
		this.setNs("com.wl.jx.mapper.ExtCproductMapper.");			//设置命名空间
	}
	
	//根据合同号删除附件
	public void deleteByContractId(Serializable contractId) {
		this.getSqlSession().delete(this.getNs() + "deleteByContractId", contractId);
	}


	//根据货物ID删除附件
	public void deleteByContractProductId(Serializable contractProductId) {
		this.getSqlSession().delete(this.getNs() + "deleteByContractProductId", contractProductId);
	}
}
