package com.hstore.vn.service;

import java.math.BigDecimal;
import java.util.List;

import com.hstore.vn.entity.Purchase;

public interface PurchaseService {
	
    Purchase savePurchase(Purchase Purchase);
	
	List<Purchase> getNotCompletePurchase();
	
	Purchase updateToCompletePurchase(Long purchaseId);
	
	Purchase getPurchaseById(Long id);
	
	void deletePurchase(Long purchaseId);
	
	Purchase updatePurchase(Purchase purchase);
	
	BigDecimal getTotalsMoneyByPurchase(Long purchaseId);
				
	List<Purchase> getCompletedPurchase();
}
