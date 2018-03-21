package com.wl.jx.controller.cargo.export;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wl.jx.controller.BaseController;
import com.wl.jx.domain.Contract;
import com.wl.jx.domain.ContractProduct;
import com.wl.jx.domain.Export;
import com.wl.jx.domain.ExportProduct;
import com.wl.jx.domain.ExtCproduct;
import com.wl.jx.domain.ExtEproduct;
import com.wl.jx.service.ContractProductService;
import com.wl.jx.service.ContractService;
import com.wl.jx.service.ExportProductService;
import com.wl.jx.service.ExportService;
import com.wl.jx.service.ExtCproductService;
import com.wl.jx.service.ExtEproductService;

@Controller
public class ExportController extends BaseController {
	@Autowired
	ExportService exportService;
	@Autowired
	ContractService contractService;
	@Autowired
	ContractProductService contractProductService;
	@Autowired
	ExportProductService exportProductService;
	@Autowired
	ExtCproductService extCproductService;
	@Autowired
	ExtEproductService extEproductService;
	
	//列表
	@RequestMapping("/cargo/export/list.action")
	public String list(Export export, Model model) {
		List<Export> dataList = exportService.find(export);
		model.addAttribute("dataList", dataList);
		
		return "/cargo/export/jExportList.jsp";
	}
	
	//购销合同查询
	@RequestMapping("/cargo/export/contractList.action")
	public String listForExport(Contract contract, Model model) {
		contract.setState(1);
		List<Contract> dataList = contractService.find(contract);
		model.addAttribute("dataList", dataList);
		
		return "cargo/export/jContractList.jsp";
	}
	
	//新增保存
	@RequestMapping("/cargo/export/insert1.action")
	public String insert1(String id){
		/*
		 * 操作步骤
		 * 1.获取合同信息，从页面传递多个id
		 * 2.创建报运对象
		 * 3.将合同中的货物信息进行“搬家”
		 * 4.将合同中的附件信息进行“搬家”
		 * 5.保存报运
		 */
		
		String exportId = UUID.randomUUID().toString();
		Export export = new Export();
		export.setId(UUID.randomUUID().toString());
		//1.获取合同信息，从页面传递多个id
		
		export.setContractIds(id);		//合同ID集合
		String[] contractId = id.split(",");
		String _contractNos = "";
		for (int i = 0; i < contractId.length; i++) {
			Contract contract = contractService.get(contractId[i]);		//获得合同对象
			_contractNos += contract.getContractNo() + " ";
		}
		export.setCustomerContract(_contractNos);		
		
		exportService.insert(export);
		
		//3.将合同中的货物信息进行“搬家”
		ContractProduct findcontractProduct = new ContractProduct();
		for (int i = 0; i < contractId.length; i++) {
			findcontractProduct.setContractId(contractId[i]);		//设置查询条件
			List<ContractProduct> cpList = contractProductService.find(findcontractProduct);	//某一个合同下的信息
			for (ContractProduct cp : cpList) {
				String exportProductId = UUID.randomUUID().toString();		//货物ID
				ExportProduct ep = new ExportProduct();
				
				ep.setExportId(exportId);	//报运ID
				
				ep.setId(exportProductId);
				ep.setAmount(cp.getAmount());
				ep.setBoxNum(cp.getBoxNum());
				ep.setCnumber(cp.getCnumber());
				ep.setFactoryId(cp.getFactoryId());
				ep.setFactoryName(cp.getFactoryName());
				ep.setPackingUnit(cp.getPackingUnit());
				ep.setPrice(cp.getPrice());
				ep.setProductDesc(cp.getProductDesc());
				ep.setProductImage(cp.getProductImage());
				ep.setProductNo(cp.getProductNo());
				
				exportProductService.insert(ep);	//保存报运货物信息
				
				//4.将合同中的附件信息进行“搬家”
				ExtCproduct findextCproduct = new ExtCproduct();		//查询条件
				findextCproduct.setContractProductId(cp.getId());		//货物ID
				
				List<ExtCproduct> extcpList = extCproductService.find(findextCproduct);
				for (ExtCproduct extcp : extcpList) {
					ExtEproduct extep = new ExtEproduct();
					
					extep.setExportProductId(exportProductId);		//设置外键
					
					extep.setId(UUID.randomUUID().toString());
					extep.setAmount(extcp.getAmount());
					extep.setCnumber(extcp.getCnumber());
					extep.setFactoryId(extcp.getFactoryId());
					extep.setFactoryName(extcp.getFactoryName());
					extep.setPackingUnit(extcp.getPackingUnit());
					extep.setPrice(extcp.getPrice());
					extep.setProductDesc(extcp.getProductDesc());
					extep.setProductImage(extcp.getProductImage());
					extep.setProductNo(extcp.getProductNo());
					
					extEproductService.insert(extep);
				}
				
			}
		}
		
		return "redirect:/cargo/export/list.action";
	}
	
	@RequestMapping("/cargo/export/insert.action")
	public String insert(String id){
		/*
		 * 操作步骤
		 * 1.获取合同信息，从页面传递多个id
		 * 2.创建报运对象
		 * 3.将合同中的货物信息进行“搬家”
		 * 4.将合同中的附件信息进行“搬家”
		 * 5.保存报运
		 */
		String exportId = UUID.randomUUID().toString();
		Export export = new Export();
		export.setId(exportId);
		//1.获取合同信息，从页面传递多个id
		
		export.setContractIds(id);		//合同ID集合
		String[] contractId = id.split(",");
		String _contractNos = "";
		for (int i = 0; i < contractId.length; i++) {
			Contract contract = contractService.get(contractId[i]);		//获得合同对象
			_contractNos += contract.getContractNo() + " ";
		}
		export.setCustomerContract(_contractNos);
		export.setState(0);								//0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
		
		exportService.insert(export);
		
		//3.将合同中的货物信息进行“搬家”
		ContractProduct findcontractProduct = new ContractProduct();
		for (int i = 0; i < contractId.length; i++) {
			findcontractProduct.setContractId(contractId[i]);		//设置查询条件
			List<ContractProduct> cpList = contractProductService.find(findcontractProduct);	//某一个合同下的信息
			for (ContractProduct cp : cpList) {
				String exportProductId = UUID.randomUUID().toString();		//货物ID
				ExportProduct ep = new ExportProduct();
				
				BeanUtils.copyProperties(cp, ep);	//拷贝信息
				
				//设置不同的内容
				ep.setExportId(exportId);	//报运ID
				ep.setId(exportProductId);
				
				exportProductService.insert(ep);	//保存报运货物信息
				
				//4.将合同中的附件信息进行“搬家”
				ExtCproduct findextCproduct = new ExtCproduct();		//查询条件
				findextCproduct.setContractProductId(cp.getId());		//货物ID
				
				List<ExtCproduct> extcpList = extCproductService.find(findextCproduct);
				for (ExtCproduct extcp : extcpList) {
					ExtEproduct extep = new ExtEproduct();
					
					BeanUtils.copyProperties(extcp, extep);	//拷贝信息
					
					extep.setExportProductId(exportProductId);		//设置外键
					extep.setId(UUID.randomUUID().toString());
					
					extEproductService.insert(extep);
				}
				
			}
		}
		
		return "redirect:/cargo/export/list.action";
	}
	
	//转向修改页面
	@RequestMapping("/cargo/export/toupdate.action")
	public String toupdate(String id, Model model) {
		Export obj = exportService.get(id);
		model.addAttribute("obj", obj);
		
		//准备货物信息
		String htmlString = exportService.getHTMLString(id);
		model.addAttribute("mRecordData", htmlString);
		
		return "/cargo/export/jExportUpdate.jsp";
	}
	
	//保存修改
	@RequestMapping("/cargo/export/update.action")
	public String update(HttpServletRequest request) {
		String[] id = request.getParameterValues("mr_id");
		String[] changed = request.getParameterValues("mr_changed");
		String[] orderNo = request.getParameterValues("mr_orderNo");
		String[] cnumber = request.getParameterValues("mr_cnumber");
		String[] grossWeight = request.getParameterValues("mr_grossWeight");
		String[] netWeight = request.getParameterValues("mr_netWeight");
		String[] sizeLength = request.getParameterValues("mr_sizeLength");
		String[] sizeWidth = request.getParameterValues("mr_sizeWidth");
		String[] sizeHeight = request.getParameterValues("mr_sizeHeight");
		String[] exPrice = request.getParameterValues("mr_exPrice");
		String[] tax = request.getParameterValues("mr_tax");
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("changed", changed);
		map.put("orderNo", orderNo);
		map.put("cnumber", cnumber);
		map.put("grossWeight", grossWeight);
		map.put("netWeight", netWeight);
		map.put("sizeLength", sizeLength);
		map.put("sizeWidth", sizeWidth);
		map.put("sizeHeight", sizeHeight);
		map.put("exPrice", exPrice);
		map.put("tax", tax);
		
		
		exportService.update(map);
		
		return "redirect:/cargo/export/list.action";
	}
	
}
