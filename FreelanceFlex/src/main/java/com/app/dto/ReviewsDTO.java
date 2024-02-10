package com.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReviewsDTO {
	//@NotNull
//	private Long buyerId;
//	//@NotNull
//	private Long freelancerId;

	@NotBlank(message = "country can not be blank")
	private String title;
	
	private String description;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private int rating;


}
