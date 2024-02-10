package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginCredDTO;
import com.app.dto.LoginResponseDTO;
import com.app.entities.SignIn;
import com.app.service.LoginServiceImpl;


@RestController
@RequestMapping("/login")
public class LoginController {
@Autowired
private LoginServiceImpl loginService;
@CrossOrigin(origins = "*")
@PostMapping
public ResponseEntity<?> signIn(@RequestBody @Valid LoginCredDTO userCredentials)
{
	LoginResponseDTO finalResponse=loginService.verifyUser(userCredentials);
	if(finalResponse!=null)
	{
	return ResponseEntity.status(HttpStatus.OK).body(finalResponse);
	}
	else
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(finalResponse);
	}
}
}



