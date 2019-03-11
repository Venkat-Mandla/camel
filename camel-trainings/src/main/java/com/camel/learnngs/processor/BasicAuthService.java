package com.camel.learnngs.processor;

import javax.security.auth.Subject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder; 
public class BasicAuthService implements Processor { 
	public void process(Exchange exchange) throws Exception { 
		// get the username and password from the HTTP header // http://en.wikipedia.org/wiki/Basic_access_authentication 
		String userpass = new String(Base64.decodeBase64(exchange.getIn().getHeader("Authorization", String.class))); 
		String[] tokens = userpass.split(":"); 
		// create an Authentication object 
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(tokens[0], tokens[1]); 
		// wrap it in a Subject 
		Subject subject = new Subject(); 
		subject.getPrincipals().add(authToken); 
		// place the Subject in the In message 
		exchange.getIn().setHeader(Exchange.AUTHENTICATION, subject); 
		// you could also do this if useThreadSecurityContext is set to true 
		SecurityContextHolder.getContext().setAuthentication(authToken);
		} 
}