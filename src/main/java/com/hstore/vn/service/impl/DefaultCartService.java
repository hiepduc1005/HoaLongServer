package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Product;
import com.hstore.vn.service.CartService;
import com.hstore.vn.service.ProductService;

@Service
public class DefaultCartService implements CartService{
	
	@Autowired
	public ProductService productService;
	
	@Override
	public Map<Long, Integer> convertCartCookieToMap(String cookieCart){
		
	 Map<Long,Integer> cartMap = new HashMap<Long, Integer>();
		
		if(!cookieCart.isEmpty()) {
			
			String[] keyValuePairs = cookieCart.split("/");
			
			for(String pair : keyValuePairs) {
				String[] keyValue = pair.split(":");
				
				if(keyValue.length ==2) {
					
					Long productId = Long.parseLong(keyValue[0]);
					Integer quantity = Integer.parseInt(keyValue[1]);
					
					cartMap.put(productId,quantity);
				}
			}
		}
		
		return cartMap;
	}

	@Override
	public String convertMapToCartCookie(Map<Long, Integer> cartMap) {
        StringBuilder newCartCookie = new StringBuilder();
		
		for(Map.Entry<Long,Integer> entry : cartMap.entrySet()) {
			newCartCookie.append(entry.getKey()).append(":").append(entry.getValue()).append("/");
		}
		
		if(newCartCookie.length() > 0) {
			newCartCookie.deleteCharAt(newCartCookie.length()-1);
		}
		
		return newCartCookie.toString();
		
	}

	@Override
	public BigDecimal getTotalPriceInCart(Map<Long, Integer> cartMap) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		if(!cartMap.isEmpty()) {
			
			for(Map.Entry<Long,Integer> entry : cartMap.entrySet()) {
				Product product = productService.getProductById(entry.getKey());
				if(product != null) {
					BigDecimal productPrice = product.getPrice();
					BigDecimal quantity = BigDecimal.valueOf(entry.getValue());
					BigDecimal total = productPrice.multiply(quantity);
					totalPrice = totalPrice.add(total);
				}
		   }
		
		}
				
		return totalPrice;
	}
	


}
