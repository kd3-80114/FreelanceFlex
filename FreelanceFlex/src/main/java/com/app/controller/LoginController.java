package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginCredDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.SigninResponse;
import com.app.entities.SignIn;
import com.app.entities.User;
import com.app.security.JwtUtils;
import com.app.service.LoginService;
import com.app.service.LoginServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
@Autowired
private JwtUtils utils;
@Autowired 
private LoginService loginService;
@Autowired 
private AuthenticationManager mgr;

@CrossOrigin(origins = "*")
@PostMapping("/user")
public ResponseEntity<?> signIn(@RequestBody @Valid LoginCredDTO userCredentials)
{
	
	System.out.println("in sign in ");
	Authentication verifiedAuth = mgr
			.authenticate(new UsernamePasswordAuthenticationToken
					(userCredentials.getEmail(), userCredentials.getPassword()));
	System.out.println(verifiedAuth.getClass());// Custom user details
	
	
	User user=loginService.verifyUser(userCredentials);
	
	SigninResponse signInResponse = new SigninResponse (utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!");

	LoginResponseDTO finalResponse=new LoginResponseDTO();
	finalResponse.setSigninResponse(signInResponse);
	finalResponse.setUser(user);
	return ResponseEntity.status(HttpStatus.OK).body(finalResponse);
	//added something
}
}



