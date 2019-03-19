package com.zd.secureapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.stereotype.Component;


@EnableWebSecurity
@Configuration 
@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public WebSecurityConfig() {
		// TODO Auto-generated constructor stub
	}

	public WebSecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
		// TODO Auto-generated constructor stub
	}

	
	@Autowired
	DigestAuthenticationFilter digestFilter;

	@Autowired
	DigestAuthenticationEntryPoint digestEntryPoint;

	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{        

	    http.addFilter(digestFilter)              // register digest entry point
	    .exceptionHandling().authenticationEntryPoint(digestEntryPoint)     // on exception ask for digest authentication
	    .and()
	    .authorizeRequests()
	    .anyRequest().authenticated()
	    .and().csrf().disable();

	    http.httpBasic().disable();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new PasswordEncoder() {
	        @Override
	        public String encode(CharSequence rawPassword) {
	            return rawPassword.toString();
	        }
	        @Override
	        public boolean matches(CharSequence rawPassword, String encodedPassword) {
	            return rawPassword.toString().equals(encodedPassword);
	        }
	    };
}
}
