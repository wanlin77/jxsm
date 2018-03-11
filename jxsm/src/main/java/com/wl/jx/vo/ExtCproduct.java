package com.wl.jx.vo;

import com.wl.jx.domain.Factory;

public class ExtCproduct {
	private String extCproductId;
	private String productNo;
	private String productDesc;
	private String productImage;
	private Integer cnumber;
	private String packingUnit;
	private Double price;
	private Double amount;
	
	private Factory factory;
	
	
	public String getExtCproductId() {
		return extCproductId;
	}

	public void setExtCproductId(String extCproductId) {
		this.extCproductId = extCproductId;
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
	
	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
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
	
}
