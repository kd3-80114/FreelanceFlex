package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginCredDTO {
	
	@Email(message="Invalid email format")
	private String email;
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!?])(?=.*[a-zA-Z0-9@#$%^&+=!?]{8,})[a-zA-Z0-9@#$%^&+=!?]{8,20}$"
			,message = "Invalid password")
	private String password;
}
