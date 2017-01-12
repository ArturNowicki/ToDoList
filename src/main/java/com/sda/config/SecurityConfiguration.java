package com.sda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sda.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService = new CustomUserDetailsService();
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  	.antMatchers("/", "/login").permitAll()
	  	.antMatchers("/dashboard").access("hasRole('USER') or hasRole('ADMIN')")
	  	.antMatchers("/item").access("hasRole('USER') or hasRole('ADMIN')")
	  	.antMatchers("/users").access("hasRole('USER') or hasRole('ADMIN')")
	  	.antMatchers("/edit**").access("hasRole('ADMIN')")
	  	.antMatchers("/delete**").access("hasRole('ADMIN')")
	  	.antMatchers("/new**").access("hasRole('ADMIN')")
	  	.and().formLogin().loginPage("/login")
	  	.usernameParameter("login").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/access_denied")
	  	;
	}
}
