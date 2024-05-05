package com.hstore.vn.service;

import java.math.BigDecimal;
import java.util.Map;


public interface CartService {
	
	 Map<Long,Integer> convertCartCookieToMap(String cookieCart);
	
	 String convertMapToCartCookie(Map<Long,Integer> cartMap);
	 
	 BigDecimal getTotalPriceInCart(Map<Long,Integer> cartMap);
	
}
