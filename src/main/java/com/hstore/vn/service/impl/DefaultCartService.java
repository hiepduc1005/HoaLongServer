package com.hstore.vn.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hstore.vn.service.CartService;

@Service
public class DefaultCartService implements CartService{
	
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
	


}
