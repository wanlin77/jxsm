package com.wl.jx.dao.impl;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ContractProductDao;
import com.wl.jx.domain.ContractProduct;


@Repository
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao{
	public ContractProductDaoImpl() {
		this.setNs("com.wl.jx.mapper.ContractProductMapper.");			//设置命名空间
	}
}
