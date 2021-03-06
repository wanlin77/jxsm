package com.wl.jx.service;

import java.io.Serializable;
import java.util.List;

import com.wl.jx.domain.ExtEproduct;

public interface ExtEproductService {
	public List<ExtEproduct> find(ExtEproduct extEproduct);
	public ExtEproduct get(Serializable id);
	public void insert(ExtEproduct extEproduct);
	public void update(ExtEproduct extEproduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}

