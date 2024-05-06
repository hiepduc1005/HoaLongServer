package com.hstore.vn.dto.request;

import java.math.BigDecimal;
import java.util.Map;

public class PurchaseRequestUpdate {
	private Long id;
	private String address;
	private String customerRequest;
	private BigDecimal totalPrice;
	private Map<Long,Integer> productQuantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
