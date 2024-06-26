package com.hstore.vn.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dto.request.CartProductAddRequest;
import com.hstore.vn.dto.request.CartProductDeleteRequest;
import com.hstore.vn.dto.request.CartUpdateRequest;
import com.hstore.vn.dto.response.TotalPriceResponse;
import com.hstore.vn.entity.Product;
import com.hstore.vn.service.CartService;
import com.hstore.vn.service.ProductService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

	@Autowired
	public CartService cartService;
	
	@Autowired
	public ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<Map<Long, Integer>> getAllProductsInCart(
			@CookieValue(name = "cart", defaultValue = "") String cartCookie

	) {

		Map<Long, Integer> cartMap = cartService.convertCartCookieToMap(cartCookie);
		return new ResponseEntity<Map<Long, Integer>>(cartMap, HttpStatus.OK);

	}

	@GetMapping("/total-price")
	public ResponseEntity<TotalPriceResponse> getTotalPriceInCart(
			@CookieValue(name = "cart", defaultValue = "") String cartCookie) {

		Map<Long, Integer> cartMap = cartService.convertCartCookieToMap(cartCookie);
		BigDecimal totalPrice = cartService.getTotalPriceInCart(cartMap);

		return new ResponseEntity<TotalPriceResponse>(new TotalPriceResponse(totalPrice), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addProductToCart(
			@RequestBody CartProductAddRequest cartProductRequest,
			HttpServletResponse response,
			@CookieValue(name = "cart", defaultValue = "") String cartCookie) {

		Long productId = cartProductRequest.getProductId();
		Integer quantity = cartProductRequest.getQuantity();

		if (quantity <= 0) {
			return new ResponseEntity<String>("Số lượng không hợp lệ!", HttpStatus.BAD_REQUEST);
		}

		Map<Long, Integer> cartMap = cartService.convertCartCookieToMap(cartCookie);

		cartMap.put(productId, quantity);

		String newCartCookie = cartService.convertMapToCartCookie(cartMap);

		Cookie cookie = new Cookie("cart", newCartCookie);
		cookie.setMaxAge(60 * 60 * 24 * 30);	  
	    cookie.setHttpOnly(true);
	    cookie.setPath("/");
	    cookie.setDomain("hoalong.netlify.app");
	    String cookieHeader = String.format("%s=%s; Max-Age=%d; %s; Path=%s; %s=%s; %s",
	            cookie.getName(), 
	            cookie.getValue(),
	            cookie.getMaxAge(),
	            "HttpOnly",
	            cookie.getPath(),
	        
	            "SameSite",
	            "None",
	            "Secure"
	        );
	    response.addHeader("Set-Cookie", cookieHeader);
	

		return new ResponseEntity<String>("Product add to cart successfully!", HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Map<Long, Integer>> deleteProductInCart(
			@RequestBody CartProductDeleteRequest cartProductDeleteRequest,
			HttpServletResponse response,
			@CookieValue(name = "cart", defaultValue = "") String cookieCart) {

		Long productId = cartProductDeleteRequest.getProductId();

		Map<Long, Integer> cartMap = cartService.convertCartCookieToMap(cookieCart);

		cartMap.remove(productId);

		String newCartCookie = cartService.convertMapToCartCookie(cartMap);

		  Cookie cookie = new Cookie("cart", newCartCookie);
	    cookie.setMaxAge(60 * 60 * 24 * 30); // 1 week
	    cookie.setHttpOnly(true);
	    cookie.setPath("/");
	    cookie.setDomain("hoalong.netlify.app");
	    String cookieHeader = String.format("%s=%s; Max-Age=%d; %s; Path=%s; %s=%s; %s",
	            cookie.getName(), 
	            cookie.getValue(),
	            cookie.getMaxAge(),
	            "HttpOnly",
	            cookie.getPath(),
	          
	            "SameSite",
	            "None",
	            "Secure"
	        );
	    response.addHeader("Set-Cookie", cookieHeader);


		return new ResponseEntity<Map<Long, Integer>>(cartMap, HttpStatus.OK);

	}
	
	@PostMapping("/delete/all")
	public ResponseEntity<Map<Long, Integer>> deleteAllProductInCart(
			HttpServletResponse response,
			@CookieValue(name = "cart", defaultValue = "") String cookieCart) {

	      

		Cookie cookie = new Cookie("cart", "");
	    cookie.setMaxAge(0);
	    cookie.setHttpOnly(true);
	    cookie.setPath("/");
	    cookie.setDomain("hoalong.netlify.app");
	    String cookieHeader = String.format("%s=%s; Max-Age=%d; %s; Path=%s; %s=%s; %s",
	            cookie.getName(), 
	            cookie.getValue(),
	            cookie.getMaxAge(),
	            "HttpOnly",
	            cookie.getPath(),
	          
	            "SameSite",
	            "None",
	            "Secure"	
	        );
	    response.addHeader("Set-Cookie", cookieHeader);

	    Map<Long, Integer> cartMap = new HashMap<>();

		return new ResponseEntity<Map<Long, Integer>>(cartMap, HttpStatus.OK);

	}
	
	@PostMapping("/update_quantity")
	public ResponseEntity<String> updateProductQuantity(
			@RequestBody CartUpdateRequest cartUpdateRequest,
			HttpServletResponse response,
			@CookieValue(name = "cart", defaultValue = "") String cookieCart){
		
		Map<Long, Integer> cartMap = cartService.convertCartCookieToMap(cookieCart);
		
		Long productId = cartUpdateRequest.getProductId();
		Integer productQuantity = cartUpdateRequest.getQuantity();
		
		Product product = productService.getProductById(productId);
		
		if(product == null) {
			return new ResponseEntity<String>("Not found product", HttpStatus.BAD_REQUEST);
		}
		
		cartMap.put(productId, productQuantity);
		
		String newCartCookie = cartService.convertMapToCartCookie(cartMap);
		
		Cookie cookie = new Cookie("cart", newCartCookie);
		cookie.setMaxAge(60 * 60 * 24 * 30);	  
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setDomain("hoalong.netlify.app");
		String cookieHeader = String.format("%s=%s; Max-Age=%d; %s; Path=%s; %s=%s; %s",
				cookie.getName(), 
				cookie.getValue(),
				cookie.getMaxAge(),
				"HttpOnly",
				cookie.getPath(),
				
				"SameSite",
				"None",
				"Secure"
				);
		response.addHeader("Set-Cookie", cookieHeader);
		
		return new ResponseEntity<String>("Update product in cart success!", HttpStatus.OK);
	}
		

}
