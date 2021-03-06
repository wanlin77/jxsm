package com.wl.jx.controller.cargo.contract;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wl.jx.domain.ContractProduct;
import com.wl.jx.domain.Factory;
import com.wl.jx.service.ContractProductService;
import com.wl.jx.service.FactoryService;
import com.wl.util.Arith;

@Controller
public class ContractProductController {
	@Autowired
	ContractProductService contractProductService;
	
	@Autowired
	FactoryService factoryService;
	
	//转向新增页面
	@RequestMapping("/cargo/contractproduct/tocreate.action")
	public String tocreate(ContractProduct contractProduct, Model model) {
		//传递主表ID,同时作为查询条件
		model.addAttribute("contractId", contractProduct.getContractId());
		
		//准备厂家下拉框
		List<Factory> factoryList = factoryService.combo();
		model.addAttribute("factoryList", factoryList);
		
		//准备列表数据，某个合同下的货物
		List<ContractProduct> dataList = contractProductService.find(contractProduct);
		model.addAttribute("dataList", dataList);
		
		return "/cargo/contractproduct/jContractProductCreate.jsp";
	}
	
	//新增保存
	@RequestMapping("/cargo/contractproduct/insert.action")
	public String insert(ContractProduct contractProduct) {
		contractProduct.setId(UUID.randomUUID().toString());
		
		if(contractProduct.getPrice()!= null && contractProduct.getCnumber()!=null) {
			Arith arith = new Arith();//java精度工具类
			contractProduct.setAmount(arith.mul(contractProduct.getPrice(),contractProduct.getCnumber()));
		}
		
		contractProductService.insert(contractProduct); 
		
		return "redirect:/cargo/contractproduct/tocreate.action?contractId="+contractProduct.getContractId();		//批量新增,同时携带主表ID
	}
	
	//转向修改页面
	@RequestMapping("/cargo/contractproduct/toupdate.action")
	public String toupdate(String id, Model model) {
		//准备厂家下拉框
		List<Factory> factoryList = factoryService.combo();
		model.addAttribute("factoryList", factoryList);
		
		ContractProduct obj = contractProductService.get(id);
		model.addAttribute("obj", obj);
		
		return "/cargo/contractproduct/jContractProductUpdate.jsp";
	}
	
	//修改保存
	@RequestMapping("/cargo/contractproduct/update.action")
	public String update(ContractProduct contractProduct) {
		
		if(contractProduct.getPrice()!= null && contractProduct.getCnumber()!=null) {
			Arith arith = new Arith();//java精度工具类
			contractProduct.setAmount(arith.mul(contractProduct.getPrice(),contractProduct.getCnumber()));
		}
		
		contractProductService.update(contractProduct);
		
		
		return "redirect:/cargo/contractproduct/tocreate.action?contractId="+contractProduct.getContractId();	//修改保存后返回列表（新增）页面
	}
	
	//删除
	@RequestMapping("/cargo/contractproduct/delete.action")
	public String delete(String id, String contractId) {
		contractProductService.delete(id);
		
		return "redirect:/cargo/contractproduct/tocreate.action?contractId="+contractId;
	}
}
