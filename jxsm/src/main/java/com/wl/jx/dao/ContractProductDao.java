package com.wl.jx.dao;

import java.io.Serializable;

import com.wl.jx.domain.ContractProduct;


public interface ContractProductDao extends BaseDao<ContractProduct> {
	void deleteByContractId(Serializable contractId);	//根据合同号级联删除货物及附件
}
