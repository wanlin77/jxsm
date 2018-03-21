package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.ExportDao;
import com.wl.jx.dao.PackingListDao;
import com.wl.jx.domain.Export;
import com.wl.jx.domain.PackingList;
import com.wl.jx.service.PackingListService;
import com.wl.util.UtilFuns;


@Service
public class PackingListServiceImpl implements PackingListService {
	@Autowired
	PackingListDao packingListDao;
	@Autowired
	ExportDao exportDao;
	
	public List<PackingList> find(PackingList packingList) {
		return packingListDao.find(packingList);
	}

	public PackingList get(Serializable id) {
		return packingListDao.get(id);
	}

	public void insert(PackingList packingList) {
		String[] divstr = packingList.getExportIds().split(",");
		
		String[] _tmp = this.splitExport(divstr);
		
		packingList.setExportIds(_tmp[0]);
		packingList.setExportNos(_tmp[1]);
		
		packingListDao.insert(packingList);
	}

	public void update(PackingList packingList) {
		String[] divstr = packingList.getExportIds().split(",");
		
		String[] _tmp = this.splitExport(divstr);
		
		packingList.setExportIds(_tmp[0]);
		packingList.setExportNos(_tmp[1]);
		
		packingListDao.update(packingList);
	}

	public void delete(Serializable id) {
		packingListDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		packingListDao.deleteBatch(ids);
	}
	
	//新增和修改页面用
	public String getDivData(Serializable[] exportIds){
		
		StringBuffer sBuf = new StringBuffer();
		for (int i = 0; i < exportIds.length; i++) {
			Export export = exportDao.get(exportIds[i]);
			sBuf.append("<input type=\"checkbox\" name=\"exportIds\" value=\"").append(export.getId()).append("|").append(export.getCustomerContract()).append("\" checked class=\"input\"/>");
			sBuf.append(export.getCustomerContract());
		}
		
		return sBuf.toString();
	}
	
	//查看用
	public String getDivDataView(Serializable[] exportIds){
		
		StringBuffer sBuf = new StringBuffer();
		for (int i = 0; i < exportIds.length; i++) {
			Export export = exportDao.get(exportIds[i]);
			sBuf.append(export.getCustomerContract());
		}
		
		return sBuf.toString();
	}
	
	//手工拆分exportIds和exportNos的组合
	private String[] splitExport(String[] divstr){
		String[] _tmp1 = new String[]{"",""};
		String[] _tmp2;
		for (int i = 0; i < divstr.length; i++) {
			_tmp2 = divstr[i].split("\\|");				// |是正则表达式的特殊字符，转义
			
			_tmp1[0] += _tmp2[0] + ",";
			_tmp1[1] += _tmp2[1] + ",";
			
			
		}
		
		_tmp1[0] = UtilFuns.delLastChar(_tmp1[0]);
		_tmp1[1] = UtilFuns.delLastChar(_tmp1[1]);
		
		return _tmp1;
	}
	
}
