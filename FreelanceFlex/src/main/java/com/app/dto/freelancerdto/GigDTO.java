package com.app.dto.freelancerdto;

import com.app.entities.CategoryType;
import com.app.entities.Freelancer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class GigDTO 
{

	@NotNull
	private FreelancerGigsDTO freelancer;
	@NotNull
	private String title;
	@NotNull
	private String description;
	@NotNull
	private CategoryType category;
	@NotNull
	private Double price;
}
