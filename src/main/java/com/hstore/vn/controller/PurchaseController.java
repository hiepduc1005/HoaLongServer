package com.hstore.vn.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dto.request.PurchaseRequest;
import com.hstore.vn.dto.request.PurchaseRequestUpdate;
import com.hstore.vn.dto.response.PurchaseResponse;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.service.PurchaseService;
import com.hstore.vn.service.convert.PurchaseConvert;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {
	
	@Autowired
	public PurchaseService purchaseService;
	
	@Autowired
	public PurchaseConvert purchaseConvert;
	
	@GetMapping("/{id}")
	public ResponseEntity<PurchaseResponse> getPurchaseById(@PathVariable Long id){
		Purchase purchase = purchaseService.getPurchaseById(id);
		
		PurchaseResponse purchaseResponse = purchaseConvert.purchaseConvertToPurchaseResponse(purchase);
		
		return new ResponseEntity<PurchaseResponse>(purchaseResponse,HttpStatus.OK);
	}
	
	@GetMapping("/complete")
	public ResponseEntity<List<PurchaseResponse>> getCompletedPurchase(){
		List<Purchase> purchases = purchaseService.getCompletedPurchase();
		
		List<PurchaseResponse> purchaseResponses = purchaseConvert.purchasesConvertToPurchasesResponse(purchases);
		
		return new ResponseEntity<List<PurchaseResponse>>(purchaseResponses,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PurchaseResponse>> getAllPurchase(){
		List<Purchase> purchases = purchaseService.getAllPurchase();
		
		List<PurchaseResponse> purchaseResponses = purchaseConvert.purchasesConvertToPurchasesResponse(purchases);
		
		return new ResponseEntity<List<PurchaseResponse>>(purchaseResponses,HttpStatus.OK);
	}
	
	@GetMapping("/not-complete")
	public ResponseEntity<List<PurchaseResponse>> getNotCompletedPurchase(){
		List<Purchase> purchases = purchaseService.getNotCompletePurchase();
		
		List<PurchaseResponse> purchaseResponses = purchaseConvert.purchasesConvertToPurchasesResponse(purchases);
		
		return new ResponseEntity<List<PurchaseResponse>>(purchaseResponses,HttpStatus.OK);
	}
	
	@GetMapping("/price/{id}")
	public ResponseEntity<BigDecimal> getTotalPriceByPurchase(@PathVariable Long id){
		BigDecimal totalPrice = purchaseService.getTotalsMoneyByPurchase(id);
		
		return new ResponseEntity<BigDecimal>(totalPrice,HttpStatus.OK);
	}
	
	@GetMapping("/today")
	public ResponseEntity<List<PurchaseResponse>> getTodayPurchase(){
		List<Purchase> purchases = purchaseService.getTodayPurchase();
		
		List<PurchaseResponse> purchaseResponses = purchaseConvert.purchasesConvertToPurchasesResponse(purchases);
		
		return new ResponseEntity<List<PurchaseResponse>>(purchaseResponses,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PurchaseResponse> postPurchase(@RequestBody PurchaseRequest purchaseRequest){
		Purchase purchase = purchaseService.savePurchase(purchaseConvert.purchaseResquestConvertToPurchase(purchaseRequest));
		
		PurchaseResponse purchaseResponse = purchaseConvert.purchaseConvertToPurchaseResponse(purchase);
		
		return new ResponseEntity<PurchaseResponse>(purchaseResponse,HttpStatus.OK);
	}
	
	@PostMapping("/update-complete/{id}")
	public ResponseEntity<PurchaseResponse> updatePurchaseToComplete(@PathVariable Long id){
		Purchase purchase = purchaseService.updateToCompletePurchase(id);
		
		PurchaseResponse purchaseResponse = purchaseConvert.purchaseConvertToPurchaseResponse(purchase);
		
		return new ResponseEntity<PurchaseResponse>(purchaseResponse,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<PurchaseResponse> updatePurchase(@RequestBody PurchaseRequestUpdate purchaseRequestUpdate){
		Purchase purchase = purchaseService.updatePurchase(purchaseConvert.purchaseRequestUpdateConvertToPurchase(purchaseRequestUpdate));
		
		PurchaseResponse purchaseResponse = purchaseConvert.purchaseConvertToPurchaseResponse(purchase);
		
		return new ResponseEntity<PurchaseResponse>(purchaseResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePurchase(@PathVariable Long id){
		purchaseService.deletePurchase(id);
		
		return new ResponseEntity<String>("Delete purchase id " + id + " success!" ,HttpStatus.OK);
	}
	
}
