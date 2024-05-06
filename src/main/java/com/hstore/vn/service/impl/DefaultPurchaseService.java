package com.hstore.vn.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Product;
import com.hstore.vn.entity.Purchase;
import com.hstore.vn.entity.PurchaseStatus;
import com.hstore.vn.exception.DataNotFoundException;
import com.hstore.vn.exception.PurchaseException;
import com.hstore.vn.repository.ProductRepository;
import com.hstore.vn.repository.PurchaseRepository;
import com.hstore.vn.service.PurchaseService;

@Service
public class DefaultPurchaseService implements PurchaseService {

	@Autowired
	public PurchaseRepository purchaseRepository;

	@Autowired
	public ProductRepository productRepository;

	private String getCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.now();
		String date = dateTime.format(formatter);
		return date;
	}

	private String getTodayDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime dateTime = LocalDateTime.now();
		String date = dateTime.format(formatter);
		return date;
	}

	@Override
	public Purchase savePurchase(Purchase purchase) {
		if (purchase == null) {
			throw new PurchaseException("Purchase must not be null");
		}
		purchase.setLocalDateTime(getCurrentDate());
		purchase.setPurchaseStatus(PurchaseStatus.NOT_COMPLETE);
		return purchaseRepository.saveAndFlush(purchase);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Purchase> getNotCompletePurchase() {
		List<Purchase> purchases = purchaseRepository.findAll();

		List<Purchase> res = new ArrayList<Purchase>();

		for (Purchase purchase : purchases) {
			if (purchase.getPurchaseStatus().toString().equalsIgnoreCase(
					PurchaseStatus.NOT_COMPLETE.toString())) {

				res.add(purchase);
			}
		}
		return res;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Purchase updateToCompletePurchase(Long purchaseId) {
		Purchase purchase = purchaseRepository.findById(purchaseId).orElse(null);

		if (purchase == null) {
			throw new PurchaseException("Can not found purchase with id " + purchaseId);
		}

		purchase.setPurchaseStatus(PurchaseStatus.COMPLETE);

		return purchaseRepository.saveAndFlush(purchase);
	}

	@Override
	public Purchase getPurchaseById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Purchase id must be type long");
		}
		
		Purchase purchase = purchaseRepository.findById(id).orElse(null);
		
		if(purchase == null) {
			throw new DataNotFoundException("Cant not found purchase with id : " + id );
		}
		
		return purchase;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deletePurchase(Long purchaseId) {
		// TODO Auto-generated method stub
		if (purchaseId == null) {
			throw new IllegalArgumentException("Purchase id must be type long");
		}

		Purchase purchaseDelete = purchaseRepository.findById(purchaseId).orElse(null);
		purchaseRepository.delete(purchaseDelete);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Purchase updatePurchase(Purchase purchase) {
		if (purchase == null) {
			throw new PurchaseException("Purchase must not be null");
		}

		return purchaseRepository.save(purchase);
	}

	@Override
	public BigDecimal getTotalsMoneyByPurchase(Long purchaseId) {
		Purchase purchase = getPurchaseById(purchaseId);
		BigDecimal res = BigDecimal.ZERO;
		Map<Long, Integer> productsQuantity = purchase.getProductQuantity();

		for (Map.Entry<Long, Integer> entry : productsQuantity.entrySet()) {
			Product product = productRepository.findById(entry.getKey()).orElse(null);
			if (product != null) {
				BigDecimal productPrice = product.getPrice();
				res = res.add(productPrice.multiply(BigDecimal.valueOf(entry.getValue())));
			}

		}

		return res;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Purchase> getCompletedPurchase() {
		List<Purchase> purchases = purchaseRepository.findAll();

		List<Purchase> res = new ArrayList<Purchase>();

		for (Purchase purchase : purchases) {
			if (purchase.getPurchaseStatus().toString().equalsIgnoreCase(
					PurchaseStatus.COMPLETE.toString())) {

				res.add(purchase);
			}
		}
		return res;
	}

	@Override
	public List<Purchase> getAllPurchase() {

		return purchaseRepository.findAll();
	}

	@Override
	public List<Purchase> getTodayPurchase() {
		return purchaseRepository.getPurchaseByDate(getTodayDate());
	}

}
