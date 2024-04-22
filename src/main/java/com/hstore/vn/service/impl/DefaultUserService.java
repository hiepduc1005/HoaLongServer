package com.hstore.vn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.User;
import com.hstore.vn.repository.UserRepoistory;
import com.hstore.vn.service.UserService;

@Service
public class DefaultUserService implements UserService{
	
	@Autowired
	public UserRepoistory userRepoistory; 

	@Override
	public User getUserByEmail(String email) {
		return userRepoistory.findByEmail(email);
	}

}
