package com.hstore.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hstore.vn.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
