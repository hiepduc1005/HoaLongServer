package com.hstore.vn.service.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hstore.vn.dto.request.ProductRequest;
import com.hstore.vn.dto.request.ProductRequestUpdate;
import com.hstore.vn.dto.response.ProductResponse;
import com.hstore.vn.entity.Product;

@Service
public class ProductConvert {
	
	public ProductResponse productConvertToProductResponse(Product product) {
		
		if(product == null) {
			return null;
		}
		
		
		return new ProductResponse(
						product.getId(),
						product.getName(),
						product.getPrice(),
						product.getDescription(),
						product.getImgData(),
						product.getGuid());
	}
	
	
	public List<ProductResponse> productsConvertToProductsResponse(List<Product> products) {
		List<ProductResponse> productResponses = new ArrayList<ProductResponse>();
		
		for(Product product : products) {
			productResponses.add(productConvertToProductResponse(product));
		}
		
		return productResponses;
    }
	
	public Product productRequestConvertToProduct(ProductRequest productRequest) {
		Product product = new Product();
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		product.setDescription(productRequest.getDescription());
		product.setImgData(productRequest.getImgData());
		
		return product;
	}
	
	public List<Product> productsRequestConvertToProducts(List<ProductRequest> productsRequest) {
        List<Product> products = new ArrayList<Product>();
		
		for(ProductRequest productRequest : productsRequest) {
			products.add(productRequestConvertToProduct(productRequest));
		}
		
		return products;
	}
	
	public Product productRequestUpdateConvertToProduct(ProductRequestUpdate productRequestUpdate) {
		Product product = new Product();
		product.setId(productRequestUpdate.getId());
		product.setName(productRequestUpdate.getName());
		product.setPrice(productRequestUpdate.getPrice());
		product.setDescription(productRequestUpdate.getDescription());
		product.setImgData(productRequestUpdate.getImgData());
		
		return product;
	}
	
	public List<Product> productsRequestUpdateConvertToProducts(List<ProductRequestUpdate> productsRequestUpdates) {
        List<Product> products = new ArrayList<Product>();
		
		for(ProductRequestUpdate productRequestUpdate : productsRequestUpdates) {
			products.add(productRequestUpdateConvertToProduct(productRequestUpdate));
		}
		
		return products;
	}
	
	
}
