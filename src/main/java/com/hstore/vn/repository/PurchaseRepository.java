package com.hstore.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hstore.vn.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
