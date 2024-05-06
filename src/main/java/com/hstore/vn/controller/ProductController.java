package com.hstore.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dto.request.ProductRequest;
import com.hstore.vn.dto.request.ProductRequestUpdate;
import com.hstore.vn.dto.response.ProductResponse;
import com.hstore.vn.entity.Product;
import com.hstore.vn.service.ProductService;
import com.hstore.vn.service.convert.ProductConvert;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {

	@Autowired
	public ProductService productService;

	@Autowired
	public ProductConvert productConvert;

	@GetMapping("/all")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<Product> products = productService.getAllProduct();
		List<ProductResponse> productResponses = productConvert.productsConvertToProductsResponse(products);

		return new ResponseEntity<List<ProductResponse>>(productResponses, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
		Product product = productService.getProductById(id);

		ProductResponse productResponse = productConvert.productConvertToProductResponse(product);

		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getProductBySearchWithPageAndLimit(
			@RequestParam(required = true, defaultValue = "") String searchQuery,
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer limit) {

		List<Product> products = productService.getProductsLikeNameForPageWithLimit(searchQuery, pageNumber, limit);
		List<ProductResponse> productResponses = productConvert.productsConvertToProductsResponse(products);

		return new ResponseEntity<List<ProductResponse>>(productResponses, HttpStatus.OK);
	}

	@GetMapping("/all/pages")
	public ResponseEntity<Integer> getNumberOfPageByProducts(
			@RequestParam(required = false, defaultValue = "10") Integer limit) {

		Integer numberOfPage = productService.getNumberOfPagesBySearch("", limit);

		return new ResponseEntity<Integer>(numberOfPage, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductResponse> postProduct(@RequestBody ProductRequest productRequest) {
		Product product = productService.saveProduct(productConvert.productRequestConvertToProduct(productRequest));

		ProductResponse productResponse = productConvert.productConvertToProductResponse(product);

		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);

	}

	@PutMapping
	public ResponseEntity<ProductResponse> putProduct(@RequestBody ProductRequestUpdate productRequestUpdate) {
		Product product = productService
				.saveProduct(productConvert.productRequestUpdateConvertToProduct(productRequestUpdate));

		ProductResponse productResponse = productConvert.productConvertToProductResponse(product);

		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProductById(id);

		return new ResponseEntity<String>("Delete product with id " + id + " success!", HttpStatus.OK);
	}

}
