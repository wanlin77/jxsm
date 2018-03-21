package com.wl.jx.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.jx.dao.ExportDao;
import com.wl.jx.dao.ExportProductDao;
import com.wl.jx.domain.Export;
import com.wl.jx.domain.ExportProduct;
import com.wl.jx.service.ExportService;
import com.wl.util.UtilFuns;


@Service
public class ExportServiceImpl implements ExportService {
	@Autowired
	ExportDao exportDao;
	@Autowired
	ExportProductDao exportProductDao;
	
	public List<Export> find(Export export) {
		return exportDao.find(export);
	}

	public Export get(Serializable id) {
		return exportDao.get(id);
	}

	public void insert(Export export) {
		exportDao.insert(export);
	}

	public void update(Map<String, Object> map) {
		
		String[] id = (String[]) map.get("id");
		String[] changed = (String[]) map.get("changed");
		String[] orderNo = (String[]) map.get("orderNo");
		String[] cnumber = (String[]) map.get("cnumber");
		String[] grossWeight = (String[]) map.get("grossWeight");
		String[] netWeight = (String[]) map.get("netWeight");
		String[] sizeLength = (String[]) map.get("sizeLength");
		String[] sizeWidth = (String[]) map.get("sizeWidth");
		String[] sizeHeight = (String[]) map.get("sizeHeight");
		String[] exPrice = (String[]) map.get("exPrice");
		String[] tax = (String[]) map.get("tax");
		
		//批量提交
		ExportProduct ep;
		
		for(int i=0; i<orderNo.length; i++){
			if(UtilFuns.isEmpty(id[i]))	{
				ep = new ExportProduct();
			} else{
				ep = exportProductDao.get(id[i]);
			}
			if(UtilFuns.isNotEmpty(cnumber[i])){
				ep.setCnumber(Integer.parseInt(cnumber[i]));
			}
			if(UtilFuns.isNotEmpty(grossWeight[i])){
				ep.setGrossWeight(Double.parseDouble(grossWeight[i]));
			}
			if(UtilFuns.isNotEmpty(netWeight[i])){
				ep.setNetWeight(Double.parseDouble(netWeight[i]));
			}
			if(UtilFuns.isNotEmpty(sizeLength[i])){
				ep.setSizeLength(Double.parseDouble(sizeLength[i]));
			}
			if(UtilFuns.isNotEmpty(sizeWidth[i])){
				ep.setSizeWidth(Double.parseDouble(sizeWidth[i]));
			}
			if(UtilFuns.isNotEmpty(sizeHeight[i])){
				ep.setSizeHeight(Double.parseDouble(sizeHeight[i]));
			}
			if(UtilFuns.isNotEmpty(exPrice[i])){
				ep.setExPrice(Double.parseDouble(exPrice[i]));
			}
			if(UtilFuns.isNotEmpty(tax[i])){
				ep.setTax(Double.parseDouble(tax[i]));
			}
			
			exportProductDao.update(ep);
		}
		
	}

	public void delete(Serializable id) {
		exportDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		exportDao.deleteBatch(ids);;
	}
	
	//拼接HTML片段  addTRRecord("mRecordTable", id, productNo, cnumber, grossWeight, netWeight, sizeLength, sizeWidth, sizeHeight, exPrice, tax)
	public String getHTMLString(String exportId) {
		List<ExportProduct> epList = exportProductDao.findByExportId(exportId);
		StringBuffer sBuf = new StringBuffer();
		
		for (ExportProduct ep : epList) {
			
			sBuf.append("addTRRecord(\"mRecordTable\", \"").append(ep.getId()).append("\", \"").append(ep.getProductNo()).append("\", \"")
			.append(UtilFuns.convertNull(ep.getCnumber())).append("\", \"").append(UtilFuns.convertNull(ep.getGrossWeight())).append("\", \"").
			append(UtilFuns.convertNull(ep.getNetWeight())).append("\", \"").append(UtilFuns.convertNull(ep.getSizeLength())).append("\", \"").
			append(UtilFuns.convertNull(ep.getSizeWidth())).append("\", \"").append(UtilFuns.convertNull(ep.getSizeHeight())).append("\", \"").
			append(UtilFuns.convertNull(ep.getExPrice())).append("\", \"").append(UtilFuns.convertNull(ep.getTax())).append("\");");
		}
		
		return sBuf.toString();
	}

}
