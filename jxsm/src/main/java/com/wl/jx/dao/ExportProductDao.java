package com.wl.jx.dao;

import java.io.Serializable;
import java.util.List;

import com.wl.jx.domain.ExportProduct;


public interface ExportProductDao extends BaseDao<ExportProduct> {
	List<ExportProduct> findByExportId(Serializable exportId);
}
