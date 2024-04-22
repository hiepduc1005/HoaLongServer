package com.hstore.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hstore.vn.entity.Contact;

import jakarta.transaction.Transactional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	@Transactional
	@Query("SELECT c FROM contact c WHERE c.phoneNum = :phoneNum")
	public List<Contact> getContactsByUserPhoneNum(@Param("phoneNum") String phoneNum);
}
