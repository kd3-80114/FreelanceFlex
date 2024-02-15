package com.app.dto;
import lombok.Getter;
import lombok.Setter;


import com.app.entities.RoleType;
import com.app.entities.User;


@Getter
@Setter
public class LoginResponseDTO {
//		private Long id;
//	
//		private String email;
//	
//		private RoleType userRole;
	private SigninResponse signinResponse;
	private User user;
	
}
