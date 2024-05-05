package com.hstore.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hstore.vn.entity.User;

@Repository
public interface UserRepoistory extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
