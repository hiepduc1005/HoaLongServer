package com.hstore.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hstore.vn.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
	
	
	@Query("SELECT p FROM purchase p WHERE SUBSTRING(p.date, 1, 10) = :date")
	public List<Purchase> getPurchaseByDate(@Param("date") String date);
}
