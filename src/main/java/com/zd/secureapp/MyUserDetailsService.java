package com.zd.secureapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service 
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		TestUser user = repo.findByUsername(username);
		
		if(user == null)
		{
			/*
			TestUser user1 = new TestUser();
			user1.setId(103);
			user1.setPassword("web");
			user1.setUsername("Spiderman");
			repo.save(user1);
			
			
			TestUser user2 = new TestUser();
			user2.setId(104);
			user2.setPassword("fly");
			user2.setUsername("Superman");
			repo.save(user2);
			*/
			throw new UsernameNotFoundException("User 404");
		}
		
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		
		return new UserPrincipal(user);
	}
}
