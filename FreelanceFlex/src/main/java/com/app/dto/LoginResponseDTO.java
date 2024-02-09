package com.app.dto;
import lombok.Getter;
import lombok.Setter;

import com.app.entities.RoleType;


@Getter
@Setter
public class LoginResponseDTO {
		private Long id;
	
		private String email;
	
		private RoleType role;
}
