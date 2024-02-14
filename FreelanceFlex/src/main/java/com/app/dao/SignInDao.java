package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.SignIn;

public interface SignInDao extends JpaRepository<SignIn,Long> {

	SignIn findByEmail(String userEmail);
	
}
