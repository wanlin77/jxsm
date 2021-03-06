package com.wl.jx.vo;

import java.util.Date;
import java.util.List;

import com.wl.jx.vo.ContractProduct;

public class Contract {
	private String id;
	private String contractNo;
	private String offeror;
	private String printStyle;
	private String customName;
	private Date signingDate;
	private String inputBy;
	private String checkBy;
	private String inspector;
	private Date shipTime;
	private Date deliveryPeriod;
	private String importNum;
	private String tradeTerms;
	private String crequest;
	private String remark;
	
	private List<ContractProduct> contractProducts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getOfferor() {
		return offeror;
	}

	public void setOfferor(String offeror) {
		this.offeror = offeror;
	}

	public String getPrintStyle() {
		return printStyle;
	}

	public void setPrintStyle(String printStyle) {
		this.printStyle = printStyle;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public Date getSigningDate() {
		return signingDate;
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}

	public String getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	public Date getDeliveryPeriod() {
		return deliveryPeriod;
	}

	public void setDeliveryPeriod(Date deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}

	public String getImportNum() {
		return importNum;
	}

	public void setImportNum(String importNum) {
		this.importNum = importNum;
	}

	public String getTradeTerms() {
		return tradeTerms;
	}

	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}

	public String getCrequest() {
		return crequest;
	}

	public void setCrequest(String crequest) {
		this.crequest = crequest;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ContractProduct> getContractProducts() {
		return contractProducts;
	}

	public void setContractProducts(List<ContractProduct> contractProducts) {
		this.contractProducts = contractProducts;
	}
	
}
