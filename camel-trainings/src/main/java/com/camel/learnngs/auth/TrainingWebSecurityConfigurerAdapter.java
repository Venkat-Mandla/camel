package com.camel.learnngs.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class TrainingWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{

	 @Autowired
	    private AuthenticationEntryPoint authEntryPoint;
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	 
	        // All requests send to the Web Server request must be authenticated
	        http.authorizeRequests().anyRequest().authenticated();
	 
	        // Use AuthenticationEntryPoint to authenticate user/password
	        http.httpBasic().authenticationEntryPoint(authEntryPoint);
	    }
	 
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	 
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        String password = "admin";
	       // String encrytedPassword = this.passwordEncoder().encode(password);
	       // System.out.println("Encoded password of admin=" + encrytedPassword);
	         
	        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //
	        mngConfig = auth.inMemoryAuthentication();
	 
	        mngConfig.withUser("venkat").password(password).roles("ADMIN");
	        mngConfig.withUser("camel").password(password).roles("USER");
	    }
}
