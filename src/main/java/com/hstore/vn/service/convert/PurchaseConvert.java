package com.hstore.vn.service.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dto.request.PurchaseRequest;
import com.hstore.vn.dto.response.PurchaseResponse;
import com.hstore.vn.entity.Purchase;

@Service
public class PurchaseConvert {
	
	@Autowired
	public ProductConvert productConvert;
	
	public PurchaseResponse purchaseConvertToPurchaseResponse(Purchase purchase) {
		
			return	new PurchaseResponse(
						purchase.getId(),
						purchase.getUserName(),
						purchase.getPhoneNum(),			
						purchase.getPurchaseStatus(),
						purchase.getAddress(),
						purchase.getCustomerRequest(),
						purchase.getTotalPrice(),
						purchase.getLocalDateTime(),
						purchase.getProductQuantity()
			);
	}
	
	public List<PurchaseResponse> purchasesConvertToPurchasesResponse(List<Purchase> purchases) {
		List<PurchaseResponse> purchaseResponses = new ArrayList<PurchaseResponse>();
		
		for(Purchase purchase : purchases) {
			purchaseResponses.add(purchaseConvertToPurchaseResponse(purchase));
		}
		
		return purchaseResponses;
	}
	
	public Purchase purchaseResquestConvertToPurchase(PurchaseRequest purchaseRequest) {
		Purchase purchase = new Purchase();
		purchase.setUserName(purchaseRequest.getUserName());
		purchase.setAddress(purchaseRequest.address);
		purchase.setCustomerRequest(purchaseRequest.getCustomerRequest());
		purchase.setProductQuantity(purchaseRequest.getProductQuantity());
		purchase.setPhoneNum(purchaseRequest.getPhoneNum());
		purchase.setTotalPrice(purchaseRequest.getTotalPrice());

		return purchase;
	}

}
