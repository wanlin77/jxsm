package com.wl.jx.controller.cargo.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wl.jx.controller.BaseController;
import com.wl.jx.domain.Contract;
import com.wl.jx.print.ContractPrint;
import com.wl.jx.service.ContractService;

@Controller
public class ContractController extends BaseController {
	@Autowired
	ContractService contractService;
	
	//列表
	@RequestMapping("/cargo/contract/list.action")
	public String list(Contract contract, Model model) {
		List<Contract> dataList = contractService.find(contract);
		model.addAttribute("dataList", dataList);
		
		return "cargo/contract/jContractList.jsp";
	}
	
	//转向新增页面
	@RequestMapping("/cargo/contract/tocreate.action")
	public String tocreate() {
		return "cargo/contract/jContractCreate.jsp"; 
	}
	
	//新增保存
	@RequestMapping("/cargo/contract/insert.action")
	public String insert(Contract contract) {
		contract.setState(0);
		contractService.insert(contract);
		return "redirect:/cargo/contract/list.action";
	}
	
	//转向修改页面
	@RequestMapping("/cargo/contract/toupdate.action")
	public String toupdate(String id, Model model) {
		Contract obj = contractService.get(id);
		model.addAttribute("obj", obj);
		return "cargo/contract/jContractUpdate.jsp"; 
	}
	
	//修改保存
	@RequestMapping("/cargo/contract/update.action")
	public String update(Contract contract) {
		contractService.update(contract);
		return "redirect:/cargo/contract/list.action";
	}
	
	//批量删除
	@RequestMapping("/cargo/contract/deletebatch.action")
	public String delete(String[] id) {
		contractService.delete(id);
		return "redirect:/cargo/contract/list.action";
	}
	
	//查看
	@RequestMapping("/cargo/contract/toview.action")
	public String toview(String id, Model model) {
		com.wl.jx.vo.Contract obj = contractService.view(id);
		model.addAttribute("obj", obj);
		return "cargo/contract/jContractView.jsp";
	}
	
	//批量进行上报
	@RequestMapping("/cargo/contract/start.action")
	public String submit(String id) {
		this.changeState(1, id.split(","));			//对多个id进行解串
		
		return "redirect:/cargo/contract/list.action";
	}
	
	//批量进行取消
	@RequestMapping("/cargo/contract/stop.action")
	public String cancel(String id) {
		this.changeState(0, id.split(","));
		
		return "redirect:/cargo/contract/list.action";
	}
	
	//修改状态   	0草稿1上报
	private void changeState(Integer stateValue,String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", stateValue);					
		map.put("ids", ids);
		
		contractService.changeState(map);
	}
	
	//打印
	@RequestMapping("/cargo/contract/print.action")
	public void print(String[] id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/");
		ContractPrint contractPrint = new ContractPrint();
		com.wl.jx.vo.Contract contract;
		for (int i = 0; i < id.length; i++) {
			contract = contractService.view(id[i]);
			contractPrint.print(contract, path, response);			//这里本打算一次打印多个合同，但是1次request1次response响应。无法实现，可以使选多个合同打印不报错，只答应第一个。这个问题留到以后解决
		}
		
	}
	
	//归档
	@RequestMapping("/cargo/contract/pigeonhole.action")
	public String pigeonhole(String id) {
		contractService.pigeonhole(id.split(","));
		
		
		return "redirect:/cargo/contract/list.action";
	}
	
	
	//历史合同列表
	@RequestMapping("/cargo/contract/historylist.action")
	public String historylist(Contract contract, Model model) {
		List<Contract> dataList = contractService.findForHistory(contract);
		model.addAttribute("dataList", dataList);
		
		return "cargo/contract/jContractHistoryList.jsp";
	}
	
	//还原
	@RequestMapping("/cargo/contract/turnback.action")
	public String turnback(String id) {
		contractService.turnback(id.split(","));
		
		
		return "redirect:/cargo/contract/historylist.action";
	}
}
