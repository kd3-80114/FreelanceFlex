package com.app.service;

import com.app.dto.LoginCredDTO;
import com.app.dto.LoginResponseDTO;

public interface LoginService {
	public  LoginResponseDTO verifyUser(LoginCredDTO userToVerify);
}
