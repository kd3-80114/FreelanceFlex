package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.SignInDao;
import com.app.entities.SignIn;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	// dep : dao layer
	@Autowired
	private SignInDao signInDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		SignIn user = signInDao.findByEmail(email);
				System.out.println("In customer User Detail Service "+user.toString());
		return new CustomUserDetails(user);
	}

}
