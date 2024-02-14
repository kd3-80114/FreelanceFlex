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
import com.app.security.JwtUtils;
import com.app.service.LoginServiceImpl;


@RestController
@RequestMapping("/login")
public class LoginController {
@Autowired
private JwtUtils utils;

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
	// => auth success
	return ResponseEntity
			.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));

	//old implt by us
//	LoginResponseDTO finalResponse=loginService.verifyUser(userCredentials);
//	if(finalResponse!=null)
//	{
//	return ResponseEntity.status(HttpStatus.OK).body(finalResponse);
//	}
//	else
//	{
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(finalResponse);
//	}
}
}



