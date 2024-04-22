package com.hstore.vn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.hstore.vn.entity.Role;
import com.hstore.vn.entity.User;
import com.hstore.vn.repository.UserRepoistory;



@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	public boolean isConfigured;
	
	@Autowired
	public UserRepoistory userRepoistory;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(isConfigured) {
			return;
		}
             
        createUserIfNotFound(Role.ROLE_ADMIN,"admin@gmail.com","admin");
        createUserIfNotFound(Role.ROLE_MANAGER,"manager@gmail.com" ,"manager");
        createUserIfNotFound(Role.ROLE_USER,"test@gmail.com", "test");      
        
		

		isConfigured = true;
	}
	
	
	private void createUserIfNotFound(Role roleDto , String email , String password) {
		User userDto = userRepoistory.findByEmail(email);
		passwordEncoder = new BCryptPasswordEncoder();
		if(userDto == null) {
			userDto = new User();
			userDto.setEmail(email);
			userDto.setFirstName("Admin");
			userDto.setLastName("Admin");
			userDto.setPassword(passwordEncoder.encode(password));

			userDto.setRole(roleDto);

			userRepoistory.save(userDto);
		}
		
	}
	
	

	
	
	

}
