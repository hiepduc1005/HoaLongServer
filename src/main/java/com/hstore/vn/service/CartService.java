package com.hstore.vn.service;

import java.util.Map;


public interface CartService {
	
	 Map<Long,Integer> convertCartCookieToMap(String cookieCart);
	
	 String convertMapToCartCookie(Map<Long,Integer> cartMap);
	
}
