package com.wl.jx.service;

import java.io.Serializable;
import java.util.List;

import com.wl.jx.domain.PackingList;

public interface PackingListService {
	public List<PackingList> find(PackingList packingList);
	public PackingList get(Serializable id);
	public void insert(PackingList packingList);
	public void update(PackingList packingList);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	
	public String getDivData(Serializable[] exportIds);
	public String getDivDataView(Serializable[] exportIds);
}

