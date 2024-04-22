package com.hstore.vn.service;

import com.hstore.vn.entity.User;

public interface UserService {
	
	User getUserByEmail(String email);
}
