package com.hstore.vn.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;

@Entity(name = "purchase")
@Table(name = "purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "phone_number")
	private String phoneNum;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "purchases_products", joinColumns = @JoinColumn(name = "purchase_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;

	@Enumerated(EnumType.STRING)
	@Column(name = "purchase_status", columnDefinition = "numeric(38,2)")
	private PurchaseStatus purchaseStatus;

	@Column(name = "address")
	private String address;

	@Column(name = "customer_request")
	private String customerRequest;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "date")
	private String date;

	@ElementCollection
	@Fetch(FetchMode.JOIN)
	@CollectionTable(name = "purchase_product_quantity_mapping", joinColumns = {
			@JoinColumn(name = "purchase_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "product_id")
	@Column(name = "quantity")
	private Map<Long, Integer> productQuantity;

	public Map<Long, Integer> getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Map<Long, Integer> productQuantity) {
		this.productQuantity = productQuantity;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public PurchaseStatus getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getLocalDateTime() {
		return date;
	}

	public void setLocalDateTime(String date) {
		this.date = date;
	}

}
