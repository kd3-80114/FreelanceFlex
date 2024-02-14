package com.app.dto.freelancerdto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.app.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
	public class FreelancerDTO {
		
		@JsonProperty(access = Access.READ_ONLY)
		private Long id;
		
		@NotBlank(message = "name can not be blank")
		private String firstName;

		@NotBlank(message = "name can not be blank")
		private String lastName;
		
		@NotBlank(message = "email can not be blank")
		@Email(message="Invalid email format")
		private String email;
//		
//		@NotBlank(message = "password can not be blank")
//		@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!?])(?=.*[a-zA-Z0-9@#$%^&+=!?]{8,})[a-zA-Z0-9@#$%^&+=!?]{8,20}$"
//		,message = "Invalid password")
//		private String password;
		
//		@NotBlank(message = "password can not be blank")
//		@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!?])(?=.*[a-zA-Z0-9@#$%^&+=!?]{8,})[a-zA-Z0-9@#$%^&+=!?]{8,20}$"
//		,message = "Invalid password")
//		private String password;
		
		@NotBlank(message = "contactNo can not be blank")
		private String contactNo;
		
		@NotBlank(message = "description can not be blank")
		private String description;
		
		private byte [] profilePicture; 

		@JsonProperty(access = Access.READ_ONLY)
		final private String role = "FREELANCER";
		
		@JsonIgnore
		private boolean isBlocked=false;
		
		@NotBlank(message = "permanentAddress can not be blank")
		private AddressDTO permanentAddress;
		
		@NotBlank(message = "skills can not be blank")
		private SkillsDTO skills;
		
		

		
	}

