package com.hstore.vn.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.User;
import com.hstore.vn.repository.UserRepoistory;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	public UserRepoistory userRepoistory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepoistory.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Can not found user with email : " + username);
		}
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString()))
				);
	}

}
