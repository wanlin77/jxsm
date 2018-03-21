package com.wl.jx.controller.run.stat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wl.common.springdao.SqlDao;
import com.wl.util.file.FileUtil;

public class SysStatController {
	@Autowired
	SqlDao sqlDao;
	
	//生产厂家销售情况统计饼形图
	@RequestMapping("/run/stat/factorySale.action")
	public String factorySale(HttpServletRequest request) throws IOException{
		/*
		 * 操作步骤：
		 * 1、获得数据，使用jdbcTemplate查出来
		 * 2、拼串形成xml文本文件
		 * 3、撞向到指定的页面index.xml
		 */
		
		String sql = "SELECT f.FACTORY_NAME,cp.num FROM (select factory_id,COUNT(*) AS num from contract_product_c GROUP BY FACTORY_ID) cp LEFT JOIN (SELECT FACTORY_ID,FACTORY_NAME FROM factory_c) f ON cp.factory_id=f.factory_id";
		List<String> dList = sqlDao.executeSQL(sql);		//构造结果，一维数组
		
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sBuf.append("<pie>\n");
		for(int i=0; i<dList.size();){				//在循环内部，等量循环，循环体内，变量取和结果集列数相同
			sBuf.append("<slice title=\"").append(dList.get(i++)).append("\">").append(dList.get(i++)).append("</slice>\n");
		}
		sBuf.append("</pie>\n");
		
		
		FileUtil fu = new FileUtil();
		String path = request.getSession().getServletContext().getRealPath("/");
		fu.newTxt(path+"run/stat/factorysale/", "data.xml", sBuf.toString(), "utf-8");
		
		return "run/stat/jStat.jsp?forward=factorysale";	//通过统一的jsp跳转，转向指定目录下的index.html
	}
}
