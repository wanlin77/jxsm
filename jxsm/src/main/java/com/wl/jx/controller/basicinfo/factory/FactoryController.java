package com.wl.jx.controller.basicinfo.factory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wl.jx.controller.BaseController;
import com.wl.jx.domain.Factory;
import com.wl.jx.service.FactoryService;
import com.wl.util.DownloadUtil;
import com.wl.util.file.FileUtil;


@Controller
public class FactoryController extends BaseController {
	@Autowired
	FactoryService factoryService;
	
	//列表
	@RequestMapping("/basicinfo/factory/list.action")
	public String list(Factory factory, Model model){
		List<Factory> dataList = factoryService.find(factory);
		model.addAttribute("dataList", dataList);			//传递结果集到页面
		
		return "/basicinfo/factory/jFactoryList.jsp";			//逻辑名
	}
	
	//转向新增页面
	@RequestMapping("/basicinfo/factory/tocreate.action")
	public String tocreate() {
		return "/basicinfo/factory/jFactoryCreate.jsp";
	}
	
	//新增保存
	@RequestMapping("/basicinfo/factory/insert.action")
	public String insert(Factory factory) {
		factoryService.insert(factory);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//转向修改页面
	@RequestMapping("/basicinfo/factory/toupdate.action")
	public String toupdate(String id, Model model) {
		//准备修改页面
		Factory obj = factoryService.get(id);
		model.addAttribute("obj", obj);						//将对象传递到对象
		
		return "/basicinfo/factory/jFactoryUpdate.jsp";
	}
	
	//修改保存
	@RequestMapping("/basicinfo/factory/update.action")
	public String update(Factory factory) {
		factoryService.update(factory);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//删除单条记录
	@RequestMapping("/basicinfo/factory/delete.action")
	public String delete(String id) {
		factoryService.delete(id);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//删除多条记录
	@RequestMapping("/basicinfo/factory/deletebatch.action")
	public String deletebatch(String[] id) {
		factoryService.delete(id);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//转向查看页面
	@RequestMapping("/basicinfo/factory/toview.action")
	public String toview(String id, Model model) {
		Factory obj = factoryService.get(id);
		model.addAttribute("obj", obj);
		return "/basicinfo/factory/jFactoryView.jsp"; 
	}
	
	//批量进行启用
	@RequestMapping("/basicinfo/factory/start.action")
	public String start(String id) {
		this.changeState(1, id.split(","));			//对多个id进行解串
		
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//批量进行停用
	@RequestMapping("/basicinfo/factory/stop.action")
	public String stop(String id) {
		this.changeState(0, id.split(","));
		
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//修改状态   	0停用1启用
	private void changeState(Integer stateValue,String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", stateValue);					
		map.put("ids", ids);
		
		factoryService.changeState(map);
	}
	
	//打印
	@RequestMapping("/basicinfo/factory/print.action")
	public void print(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		/*
		 * 操作步骤
		 * 1.获取数据
		 * 2.将数据写入到excel文件中
		 */
		
		//设置查询条件
		Factory factory = new Factory();
		factory.setState(1);
		List<Factory> dataList = factoryService.find(factory);
		String[] title = new String[] {"厂家全称","缩写","联系人","电话","手机","传真","备注",};
		
		Workbook wb = new HSSFWorkbook();	//创建工作簿
		Sheet sheet = wb.createSheet();		//创建工作表
		Row nRow;							//创建行对象
		Cell nCell;							//创建单元格对象
		int rowNo = 0;						//行号
		int colNo = 0;						//列号
		
		sheet.setColumnWidth(0, 30*256);	//设置列宽
		
		nRow = sheet.createRow(rowNo);
		nRow.setHeightInPoints(40);
		sheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, 6));	//合并单元格
		rowNo++;
		nCell = nRow.createCell(colNo);
		nCell.setCellValue("生产厂家通讯录");
		nCell.setCellStyle(this.bigTitleStyle(wb));
		
		//写标题
		nRow = sheet.createRow(rowNo++);
		nRow.setHeightInPoints(28);			//设置行高
		for (int i = 0; i < title.length; i++) {
			nCell = nRow.createCell(i);
			nCell.setCellValue(title[i]);
			nCell.setCellStyle(this.titleStyle(wb));
		}
		
		
		//写数据
		for (int j = 0; j < dataList.size(); j++) {
			colNo = 0;									//初始化
			Factory f = dataList.get(j);
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(21);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getFullName());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getFactoryName());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getContractor());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getPhone());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getMobile());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getFax());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getCnote());
			nCell.setCellStyle(this.textStyle(wb));
		}
		
//		String path = request.getSession().getServletContext().getRealPath("/");		//虚拟路径对应的真实物理路径
//		path += "/tmpfile";				//防止tomcat,jdk获取不到路径
//		File file = new File(path);
//		if(!file.exists()) {
//			file.mkdirs();				//创建多级目录
//		}
//		
//		
//		FileUtil fu = new FileUtil();
//		
//		String fileName = path + "/" + fu.newFile(path, "factory.xls");		//产生新文件名，防止冲突
		
		
		
//		OutputStream os = new FileOutputStream(fileName);		//输出流
//		wb.write(os);						//写入到文件中
//		os.flush();							//清空缓存
//		os.close();							//关闭
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();	//生成流对象
		wb.write(byteArrayOutputStream);
		
		DownloadUtil du = new DownloadUtil();
//		du.prototypeDownload(file, returnName, response, delFlag);			//下载临时文件
		du.download(byteArrayOutputStream, response, "生产厂家通讯录.xls");		//弹出下载框，用户就可以直接下载
								
	}
	
	//大标题样式
	private CellStyle bigTitleStyle(Workbook wb) {
		//创建一个单元格样式对象
		CellStyle curStyle = wb.createCellStyle();
		curStyle.setAlignment(CellStyle.ALIGN_CENTER);			//横向居中
		curStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);	//纵向居中
		
		Font curFont = wb.createFont();							//创建字体对象
		curFont.setFontName("华文隶书");							//设置字体
		curFont.setFontHeightInPoints((short) 30);				//设置字体大小
		curStyle.setFont(curFont);								//将字体对象绑定到样式对象上
		
		return curStyle;
	}
	
	//标题样式
	private CellStyle titleStyle(Workbook wb) {
		//创建一个单元格样式对象
		CellStyle curStyle = wb.createCellStyle();
		curStyle.setAlignment(CellStyle.ALIGN_CENTER);			//横向居中
		curStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);	//纵向居中
		
		Font curFont = wb.createFont();							//创建字体对象
		curFont.setFontName("微软雅黑");							//设置字体
		curFont.setFontHeightInPoints((short) 12);				//设置字体大小
		curStyle.setFont(curFont);								//将字体对象绑定到样式对象上
		
		
		//画线
		curStyle.setBorderTop(CellStyle.BORDER_THIN);			//细实线
		curStyle.setBorderBottom(CellStyle.BORDER_THIN);
		curStyle.setBorderLeft(CellStyle.BORDER_THIN);
		curStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		return curStyle;
	}
	
	//文本样式
	private CellStyle textStyle(Workbook wb) {
		//创建一个单元格样式对象
		CellStyle curStyle = wb.createCellStyle();
		curStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);	//纵向居中
		
		Font curFont = wb.createFont();							//创建字体对象
		curStyle.setFont(curFont);		
		
		//画线
		curStyle.setBorderTop(CellStyle.BORDER_THIN);			//细实线
		curStyle.setBorderBottom(CellStyle.BORDER_THIN);
		curStyle.setBorderLeft(CellStyle.BORDER_THIN);
		curStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		return curStyle;
	}
	
	public void print1() throws FileNotFoundException, IOException {
		/*
		 * 操作步骤
		 * 1.获取数据
		 * 2.将数据写入到excel文件中
		 */
		
		//创建工作簿
		Workbook wb = new HSSFWorkbook();
		//创建工作表
		Sheet sheet = wb.createSheet();
		//创建行对象
		Row nRow = sheet.createRow(3);		//起始行为0,第四行
		//创建单元格对象
		Cell ncell = nRow.createCell(1);	//第2列
		
		ncell.setCellValue("黄万霖up");		//设置单元格内容
		
		OutputStream os = new FileOutputStream("d:\\factory.xls");
		wb.write(os);						//写入到文件中
		os.flush();							//清空缓存
		os.close();							//关闭
		
	}
	
	public void print2() throws FileNotFoundException, IOException {
		/*
		 * 操作步骤
		 * 1.获取数据
		 * 2.将数据写入到excel文件中
		 */
		
		//创建工作簿
		Workbook wb = new HSSFWorkbook();
		//创建工作表
		Sheet sheet = wb.createSheet();
		
		//创建行对象
		Row nRow = sheet.createRow(3);				//起始行为0,第四行
		//创建单元格对象
		Cell nCell = nRow.createCell(1);			//第2列
		
		nCell.setCellValue("黄万霖up");				//设置单元格内容
		nCell.setCellStyle(this.titleStyle(wb));	//设置单元格样式
		
		nRow = sheet.createRow(4);
		nCell = nRow.createCell(2);
		
		nCell.setCellValue("I love java!");
		nCell.setCellStyle(this.textStyle(wb));
		
		
		OutputStream os = new FileOutputStream("d:\\factory.xls");		//输出流
		wb.write(os);						//写入到文件中
		os.flush();							//清空缓存
		os.close();							//关闭
		
	}
}
