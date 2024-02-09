package com.app.dto.admindto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminDTO {

		@NotBlank(message = "name can not be blank")
		private String firstName;
		@NotBlank(message = "name can not be blank")
		private String lastName;
		@NotBlank(message = "email can not be blank")
		private String email;
		@NotBlank(message = "contactNo can not be blank")
		private String contactNo;	
	
		private byte [] profilePicture;
		
		@JsonProperty(access = Access.READ_ONLY)
		final private String role = "ADMIN";

}
