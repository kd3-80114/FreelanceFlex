package com.app.dto;

import com.app.entities.RoleType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class SignInDTO 
{
	private String email;
	private String password;
	private RoleType userRole;

}
