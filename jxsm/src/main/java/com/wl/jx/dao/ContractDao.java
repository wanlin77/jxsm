package com.wl.jx.dao;

import java.io.Serializable;
import com.wl.jx.domain.Contract;


public interface ContractDao extends BaseDao<Contract> {
	public com.wl.jx.vo.Contract view(Serializable id);
}
