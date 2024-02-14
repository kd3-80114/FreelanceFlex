package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@EnableWebSecurity//to enable spring sec frmwork support
@Configuration //to tell SC , this is config class containing @Bean methods
@EnableGlobalMethodSecurity(prePostEnabled = true)
//To enable method level authorization support : pre n post authorization
public class SecurityConfig {
	//dep : pwd encoder
	@Autowired
	private PasswordEncoder enc;
	//dep : custom jwt auth filter
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	//dep : custom auth entry point
	@Autowired 
	private CustomAuthenticationEntryPoint authEntry;	 
	
	
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception
	{
		//URL based authorization rules
		http.cors()
        .and()
        .csrf().disable() // Disable CSRF token generation and verification
        .exceptionHandling().authenticationEntryPoint(authEntry)
        .and()
        .authorizeRequests()
            .antMatchers("/freelancer/signUp", "/buyer/signUp", "/login/user",
                    "/v*/api-doc*/**", "/swagger-ui/**").permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()// Allow pre-flight requests for JS clients
//            .antMatchers("/freelancer/**","/buyer/viewProfile").hasRole("FREELANCER")
//            .antMatchers("/buyer/**","/freelancer/viewProfile").hasRole("BUYER")
//            .antMatchers("/freelancer/viewProfile","/freelancer/viewReview/**","/freelancer/viewOrders/**",
//            		"/buyer/viewProfile","/buyer/viewReview/**","/buyer/viewOrders/**",
//            		"/admin/**").hasRole("ADMIN")
//            .antMatchers("/buyer/**","/buyer/viewProfile","/freelancer/**","/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add custom HttpFirewall
	
		return http.build();
		
	}
	//configure AuthMgr as a spring bean
	@Bean
	public AuthenticationManager authenticationManager
	(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	
	 @Bean
	    public HttpFirewall httpFirewall() {
	        return new DefaultHttpFirewall();
	    }
}
