package com.hstore.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hstore.vn.entity.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Transactional
	@Query("SELECT p FROM product p WHERE p.guid = :uuid")
	public Product getProductByUuid(@Param("uuid") String uuid);
	
	@Transactional
	@Query(nativeQuery = true ,value ="SELECT * FROM product WHERE UPPER(product_name) "
						+ "LIKE UPPER(CONCAT('%',:query,'%')) OFFSET  :offset LIMIT  :limit")
	public List<Product> getProductsLikeNameWithPaginationLimit(
			@Param("query") String query ,
			@Param("offset") Integer page ,
			@Param("limit") Integer limit
	);
	
	@Transactional
	@Query(nativeQuery = true ,value = "SELECT * FROM product OFFSET :offset LIMIT :limit")
	public List<Product> getAllProductsWithPaginationLimit(
			@Param("offset") Integer page ,
			@Param("limit") Integer limit
	);
	
	@Transactional
	@Query(nativeQuery = true , value = "SELECT COUNT(*)"
			+ " FROM product WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',:query,'%'))")
	public Integer getProductCountBySearch(@Param("query") String query);
	
	
	@Transactional
	@Query("SELECT p FROM product p WHERE p.name = :name")
	public Product getProductByName(@Param("name") String name);
}
