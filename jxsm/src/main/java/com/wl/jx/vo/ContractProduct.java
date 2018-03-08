package com.wl.jx.vo;

import java.util.List;

import com.wl.jx.domain.Factory;

public class ContractProduct {
	private String contractProductId;
	private String contractId;
	private String productNo;
	private String productDesc;
	private Integer cnumber;
	private String packingUnit;
	private Double price;
	private Double amount;
	
	private Factory factory;
	private List<ExtCproduct> extCproducts;
	
	public String getContractProductId() {
		return contractProductId;
	}
	public void setContractProductId(String contractProductId) {
		this.contractProductId = contractProductId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Integer getCnumber() {
		return cnumber;
	}
	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	public List<ExtCproduct> getExtCproducts() {
		return extCproducts;
	}
	public void setExtCproducts(List<ExtCproduct> extCproducts) {
		this.extCproducts = extCproducts;
	}
	
}
