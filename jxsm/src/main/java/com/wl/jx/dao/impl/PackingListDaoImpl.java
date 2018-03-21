package com.wl.jx.dao.impl;

import org.springframework.stereotype.Repository;

import com.wl.jx.dao.PackingListDao;
import com.wl.jx.domain.PackingList;


@Repository
public class PackingListDaoImpl extends BaseDaoImpl<PackingList> implements PackingListDao{
	public PackingListDaoImpl() {
		this.setNs("com.wl.jx.mapper.PackingListMapper.");			//设置命名空间
	}
}
