package com.zd.secureapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	
	/*
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public AuthenticationProvider addProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // ctrl + shift + o
		
		//If use BCrypt or MD5
		//provider.setPasswordEncoder(new BCryptPasswordEncoder());
				
		return provider;
	}
	
	
	public String BcryptEncoder(String password)
	{
		String BCpwd = BCrypt.hashpw(password, BCrypt.gensalt());
		return BCpwd;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		.authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll();
		
		
	}
	
	*/
	
	
	
	
	/* 
	
	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		
		List<UserDetails> users = new ArrayList<>();
		users.add(User.withDefaultPasswordEncoder().username("dong").password("dong").roles("USER").build());
		return new  InMemoryUserDetailsManager(users);
		
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/securityNone")
          .permitAll()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic()
          .authenticationEntryPoint(authenticationEntryPoint);

        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    }
	
	*/
	
	
	
	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
	

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	          .inMemoryAuthentication()
	          .withUser("user1")
	          .password(passwordEncoder().encode("user1Pass"))
	          .authorities("ROLE_USER");
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	          .authorizeRequests()
	          .antMatchers("/securityNone")
	          .permitAll()
	          .anyRequest()
	          .authenticated()
	          .and()
	          .httpBasic()
	          .authenticationEntryPoint(authenticationEntryPoint);

	        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
