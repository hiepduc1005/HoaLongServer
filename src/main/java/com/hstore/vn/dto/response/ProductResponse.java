package com.hstore.vn.dto.response;

import java.math.BigDecimal;

public class ProductResponse {
	
	public Long id;

	public ProductResponse(Long id, String name, BigDecimal price, String description,
			byte[] imgData, String guid) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imgData = imgData;
		this.uuid = guid;
	}

	public String name;
	
	public BigDecimal price;
	
	public String description;
	
	public byte[] imgData;
	
	public String uuid;

}
