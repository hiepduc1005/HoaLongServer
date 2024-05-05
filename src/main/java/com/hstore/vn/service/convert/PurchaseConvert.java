package com.hstore.vn.service.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dto.request.PurchaseRequest;
import com.hstore.vn.dto.response.PurchaseResponse;
import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.service.ProductService;

@Service
public class PurchaseConvert {
	
	@Autowired
	public ProductConvert productConvert;
	
	@Autowired
	public ProductService productService;
	
	public String mapProductQuantityToProductQuantity(Map<Long,Integer> productMap) {
	    StringBuilder res = new StringBuilder();

	    for (Map.Entry<Long,Integer> entry : productMap.entrySet()) {
	        Product product = productService.getProductById(entry.getKey());
	        if (product != null) {
	            String productName = product.getName();
	            Integer productQuantity = entry.getValue();
	            res.append(productName).append(" x ").append(productQuantity).append(",");
	        } 
	    }

	    if (res.length() > 0) {
	        res.deleteCharAt(res.length()-1);
	    }

	    return res.toString();
	}
	
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
						mapProductQuantityToProductQuantity(purchase.getProductQuantity()) 
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
