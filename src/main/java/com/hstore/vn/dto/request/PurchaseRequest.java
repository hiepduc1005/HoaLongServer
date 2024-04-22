package com.hstore.vn.dto.request;

import java.math.BigDecimal;
import java.util.Map;


public class PurchaseRequest {
	public String userName;
	public String phoneNum;
	public String address;
	public String customerRequest;
	public BigDecimal totalPrice;
	public Map<Long,Integer> productQuantity;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerRequest() {
		return customerRequest;
	}
	public void setCustomerRequest(String customerRequest) {
		this.customerRequest = customerRequest;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Map<Long, Integer> getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Map<Long, Integer> productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
}
