package com.wl.jx.controller.cargo.contract;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wl.jx.domain.ExtCproduct;
import com.wl.jx.domain.Factory;
import com.wl.jx.service.ExtCproductService;
import com.wl.jx.service.FactoryService;
import com.wl.util.Arith;

@Controller
public class ExtCproductController {
	@Autowired
	ExtCproductService extCproductService;
	
	@Autowired
	FactoryService factoryService;
	
	//转向新增页面
	@RequestMapping("/cargo/extcproduct/tocreate.action")
	public String tocreate(ExtCproduct extCproduct, Model model) {
		//传递主表ID,同时作为查询条件
		model.addAttribute("contractProductId", extCproduct.getContractProductId());
		
		//准备厂家下拉框
		List<Factory> factoryList = factoryService.combo();
		model.addAttribute("factoryList", factoryList);
		
		//准备列表数据，某个合同下的货物
		List<ExtCproduct> dataList = extCproductService.find(extCproduct);
		model.addAttribute("dataList", dataList);
		
		return "/cargo/extcproduct/jExtCproductCreate.jsp";
	}
	
	//新增保存
	@RequestMapping("/cargo/extcproduct/insert.action")
	public String insert(ExtCproduct extCproduct) {
		extCproduct.setId(UUID.randomUUID().toString());
		
		if(extCproduct.getPrice()!= null && extCproduct.getCnumber()!=null) {
			Arith arith = new Arith();//java精度工具类
			extCproduct.setAmount(arith.mul(extCproduct.getPrice(),extCproduct.getCnumber()));
		}
		
		extCproductService.insert(extCproduct); 
		
		return "redirect:/cargo/extcproduct/tocreate.action?contractProductId="+extCproduct.getContractProductId();		//批量新增,同时携带主表ID
	}
	
	//转向修改页面
	@RequestMapping("/cargo/extcproduct/toupdate.action")
	public String toupdate(String id, Model model) {
		//准备厂家下拉框
		List<Factory> factoryList = factoryService.combo();
		model.addAttribute("factoryList", factoryList);
		
		ExtCproduct obj = extCproductService.get(id);
		model.addAttribute("obj", obj);
		
		return "/cargo/extcproduct/jExtCproductUpdate.jsp";
	}
	
	//修改保存
	@RequestMapping("/cargo/extcproduct/update.action")
	public String update(ExtCproduct extCproduct) {
		
		if(extCproduct.getPrice()!= null && extCproduct.getCnumber()!=null) {
			Arith arith = new Arith();//java精度工具类
			extCproduct.setAmount(arith.mul(extCproduct.getPrice(),extCproduct.getCnumber()));
		}
		
		extCproductService.update(extCproduct);
		
		
		return "redirect:/cargo/extcproduct/tocreate.action?contractProductId="+extCproduct.getContractProductId();	//修改保存后返回列表（新增）页面
	}
	
	//删除
	@RequestMapping("/cargo/extcproduct/delete.action")
	public String delete(String id, String contractProductId) {
		extCproductService.delete(id);
		
		return "redirect:/cargo/extcproduct/tocreate.action?contractProductId="+contractProductId;
	}
}
