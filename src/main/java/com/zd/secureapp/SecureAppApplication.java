package com.zd.secureapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@SpringBootApplication
public class SecureAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAppApplication.class, args);
	}
	
	  @Bean
	  DigestAuthenticationFilter digestFilter( DigestAuthenticationEntryPoint digestAuthenticationEntryPoint,
	                                           UserCache digestUserCache, UserDetailsService userDetailsService )
	  {
	      DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
	      filter.setAuthenticationEntryPoint( digestAuthenticationEntryPoint );
	      filter.setUserDetailsService( userDetailsService );
	      filter.setUserCache( digestUserCache );
	      return filter;
	  }

	  @Bean
	  UserCache digestUserCache() throws Exception
	  {
	      return new SpringCacheBasedUserCache( new ConcurrentMapCache( "digestUserCache" ) );
	  }

	  
	  @Bean
	  DigestAuthenticationEntryPoint digestAuthenticationEntry()
	  {
	      DigestAuthenticationEntryPoint digestAuthenticationEntry = new CustomDigestAuthenticationEntryPoint();
	      digestAuthenticationEntry.setRealmName( "XXX.COM" );
	      digestAuthenticationEntry.setKey( "XXX" );
	      digestAuthenticationEntry.setNonceValiditySeconds( 60 );
	      return digestAuthenticationEntry;
	  } 
}
