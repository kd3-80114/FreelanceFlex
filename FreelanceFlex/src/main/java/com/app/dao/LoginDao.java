package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.SignIn;

public interface LoginDao extends JpaRepository<SignIn, Long> {
	public SignIn findByEmailAndPassword(String email,String password);
}
