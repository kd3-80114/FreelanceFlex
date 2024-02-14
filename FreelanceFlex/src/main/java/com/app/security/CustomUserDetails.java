package com.app.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entities.SignIn;

public class CustomUserDetails implements UserDetails {
	private SignIn loginUserDetails;

	public CustomUserDetails(SignIn user) 
	{
		this.loginUserDetails = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// class : SimpleGrantedAuthority --> GrantedAuthority
		return List.of(new 
				SimpleGrantedAuthority(loginUserDetails.getUserRole().toString()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return loginUserDetails.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return loginUserDetails.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
