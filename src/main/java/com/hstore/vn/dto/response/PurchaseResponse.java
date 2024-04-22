package com.hstore.vn.dto.response;

import java.math.BigDecimal;
import java.util.Map;

import com.hstore.vn.entity.PurchaseStatus;

public class PurchaseResponse {
	
	public Long id;
	public String userName;
	public String phoneNum;
	public PurchaseStatus purchaseStatus;
	public String address;
	public String customerRequest;
	public BigDecimal totalPrice;
	public String date;
	public Map<Long,Integer> productQuantity;
    
	public PurchaseResponse(Long id, String userName, String phoneNum,
			PurchaseStatus purchaseStatus, String address, String customerRequest, BigDecimal totalPrice,
			String date, Map<Long, Integer> productQuantity) {
		
		this.id = id;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.purchaseStatus = purchaseStatus;
		this.address = address;
		this.customerRequest = customerRequest;
		this.totalPrice = totalPrice;
		this.date = date;
		this.productQuantity = productQuantity;
	}
    
    
	
}
