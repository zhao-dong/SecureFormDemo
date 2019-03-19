package com.zd.secureapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

public class CustomDigestAuthenticationEntryPoint extends DigestAuthenticationEntryPoint {

	 @Override
	    public void commence( HttpServletRequest request, HttpServletResponse response,
	                          AuthenticationException authException )
	        throws IOException, ServletException
	    {
	        HttpServletResponse httpResponse = ( HttpServletResponse ) response;
	        String authHeader = httpResponse.getHeader( "WWW-Authenticate" );
	        if( authHeader != null )
	        {
	            httpResponse.sendError( HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase() );
	        }
	        else
	        {
	            super.commence( request, httpResponse, authException );
	        }
	    }
}
