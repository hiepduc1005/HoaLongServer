package com.hstore.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dto.request.LoginRequest;
import com.hstore.vn.dto.response.AuthResponse;
import com.hstore.vn.security.JWTGenerator;
import com.hstore.vn.service.EmailValidator;

import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	public JWTGenerator jwtGenerator;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> postLogin(@RequestBody LoginRequest loginRequest) throws AuthException{
		String email = loginRequest.getEmail();
		if(!EmailValidator.isValidEmail(email)) {
			throw new AuthException("Invalid email");
		}
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()
						)
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGenerator.gennerateToken(authentication);
		
		
		return new ResponseEntity<AuthResponse>(new AuthResponse(token),HttpStatus.OK);
	}

}
