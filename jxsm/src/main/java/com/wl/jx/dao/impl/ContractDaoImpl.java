package com.wl.jx.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.ContractDao;
import com.wl.jx.domain.Contract;


@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao{
	public ContractDaoImpl() {
		this.setNs("com.wl.jx.mapper.ContractMapper.");			//设置命名空间
	}

	public com.wl.jx.vo.Contract view(Serializable id) {
		return this.getSqlSession().selectOne(this.getNs() + "view", id);
	}
}
