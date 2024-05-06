package com.hstore.vn.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Product;
import com.hstore.vn.exception.DataNotFoundException;
import com.hstore.vn.exception.ProductException;
import com.hstore.vn.repository.ProductRepository;
import com.hstore.vn.service.ProductService;

@Service
public class DefaultProductService implements ProductService{
	
	@Autowired
	public ProductRepository productRepository;

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Product saveProduct(Product product) {
		if(product == null) {
			throw new ProductException("product must not be null!");
		}
		product.setGuid(UUID.randomUUID().toString());
		return productRepository.saveAndFlush(product);
	}

	@Override
	public Product getProductByGuid(String guid) {
		
		if(guid == null || guid.isEmpty()) {
			throw new IllegalArgumentException("Product uuid must be a string and not null!");
		}
		
		return productRepository.getProductByUuid(guid);
	}
	
	@Override
	public List<Product> getProductsByCategoryName(String categoryName) {
		
		return null;
	}

	@Override
	public List<Product> getProductsByCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Product id must be a integer and not null!");
		}
		
		Product product = productRepository.findById(id).orElse(null); 
		
		if(product == null) {
			throw new DataNotFoundException("Cant not found product with id " + id);
		}
		
		return product; 
	}

	@Override
	public List<Product> getProductsLikeNameForPageWithLimit(String querySearch, Integer page,
			Integer paginationLimit) {
		
		Integer offset = (page-1)*paginationLimit;
		
		if(querySearch.isEmpty()) {
			return getAllProductWithPaginationLimit(offset, paginationLimit);
		}
		
		return productRepository.getProductsLikeNameWithPaginationLimit(querySearch, offset, paginationLimit);
	}

	@Override
	public List<Product> getProductsByCategoryForPageWithLimit(Long categoryId, Integer page,
			Integer paginationLimit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfPagesBySearch(String searchQuery , Integer limit) {
		Integer productCount = Integer.valueOf(0);
		if(searchQuery.isEmpty()) {
			productCount = productRepository.findAll().size();
		}
		else productCount = productRepository.getProductCountBySearch(searchQuery);
		
		return (productCount % limit == 0) ? (productCount/limit) : (productCount/limit) +1;
	}

	@Override
	public Integer getNumberOfPagesByCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		if(product == null) {
			throw new ProductException("product must not be null!");
		}
		
		return productRepository.save(product);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteProduct(String uuid) {
		// TODO Auto-generated method stub
		Product product = productRepository.getProductByUuid(uuid);
		productRepository.delete(product);
	}

	@Override
	public Product getProductByName(String name) {
		
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Product name must not be null");
		}

		return productRepository.getProductByName(name);
	}

	@Override
	public List<Product> getAllProductWithPaginationLimit(Integer page, Integer limit) {
		
		return productRepository.getAllProductsWithPaginationLimit(page, limit);
	}

	@Override
	public Integer getTotalPageByLimit(Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductById(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Product id must be type long");
		}
		productRepository.deleteById(id);
	}

}
