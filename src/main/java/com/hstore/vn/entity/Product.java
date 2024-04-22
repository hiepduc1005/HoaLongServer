package com.hstore.vn.entity;

import java.math.BigDecimal;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity(name = "product")
@Table(name =  "product")
public class Product{
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Column(name = "product_name")
	public String name;
	
	
	@ManyToOne
	@JoinColumn(name ="fk_category_id")
	public Category category;
	
	@Column(name = "price")
	public BigDecimal price;
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "img_name" , columnDefinition = "LONGBLOB")
	public byte[] imgData;
	
	@Column(name = "guid" , updatable = false , nullable = false)
	public String guid;
	
	@ManyToMany(mappedBy = "products")
	public List<Purchase> purchases;

	
	public Long getId() {
		return id;
	}
	

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
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
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
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
	
	public String getGuid() {
		return guid;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	
}
