package com.wl.jx.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wl.jx.domain.Contract;
import com.wl.jx.vo.OutProduct;


public interface ContractDao extends BaseDao<Contract> {
	public com.wl.jx.vo.Contract view(Serializable id);
	public void changeState(Map<String,Object> map);
	public List<OutProduct> findOutProduct(Serializable inputDate);
	public List<String> getExtName(Serializable contractProductId);
	public List<Contract> findForHistory(Contract contract);
}
