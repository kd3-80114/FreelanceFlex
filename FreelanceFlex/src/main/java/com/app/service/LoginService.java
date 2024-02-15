package com.app.service;

import com.app.dto.LoginCredDTO;
import com.app.dto.LoginResponseDTO;
import com.app.entities.User;

public interface LoginService {
	public User verifyUser(LoginCredDTO userToVerify);
}
