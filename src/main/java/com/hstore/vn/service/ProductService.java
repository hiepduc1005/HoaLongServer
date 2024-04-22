package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.Product;

public interface ProductService {
    Product saveProduct(Product product);
	
	Product getProductByGuid(String guid);
	
	List<Product> getProductsByCategoryName(String categoryName);
	
	List<Product> getProductsByCategoryId(Long id);
	
	Product getProductById(Long id);
	
	List<Product> getProductsLikeNameForPageWithLimit(String querySearch, Integer page,Integer paginationLimit);
	
	List<Product> getProductsByCategoryForPageWithLimit(Long categoryId, Integer page, Integer paginationLimit );
	
	Integer getNumberOfPagesBySearch(String searchQuery , Integer limit);
	
	Integer getNumberOfPagesByCategoryId(Long id);
	
	List<Product> getAllProduct();
	
	Product updateProduct(Product product);
	
	void deleteProduct(String uuid);
	
	void deleteProductById(Long id);
	
	public Product getProductByName(String name);
	
	List<Product> getAllProductWithPaginationLimit(Integer page , Integer limit);
	
	Integer getTotalPageByLimit(Integer limit);
	
 
}
