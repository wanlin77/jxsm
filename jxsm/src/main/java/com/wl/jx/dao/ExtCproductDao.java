package com.wl.jx.dao;

import java.io.Serializable;

import com.wl.jx.domain.ExtCproduct;


public interface ExtCproductDao extends BaseDao<ExtCproduct> {
	void deleteByContractId(Serializable contractId);			//根据合同号级联删除货物
	void deleteByContractProductId(Serializable contractProductId);	//根据货物ID删除附件
}
