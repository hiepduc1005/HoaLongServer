package com.hstore.vn.dto.request;

import java.math.BigDecimal;

public class ProductRequestUpdate {
	public Long id;
	public String name;
	public BigDecimal price;
	public String description;
	public byte[] imgData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImgData() {
		return imgData;
	}

	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}

	public ProductRequestUpdate(String name, BigDecimal price, String description,
			byte[] imgData) {

		this.name = name;
		this.price = price;
		this.description = description;
		this.imgData = imgData;
	}

	public ProductRequestUpdate() {

	}

}
