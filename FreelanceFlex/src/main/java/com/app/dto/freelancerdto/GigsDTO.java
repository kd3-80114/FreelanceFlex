package com.app.dto.freelancerdto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.app.entities.CategoryType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GigsDTO {
	

//	@JsonProperty(access = Access.READ_ONLY)
//	private Long id;
	
	@NotBlank(message = "title cannot be blank")
	private String title;
	
	
	private byte [] gigImage;
	
	@NotBlank(message = "description cannot be blank")
	private String description;
	
	@NotNull(message = "price cannot be blank")
	private double price;
	
	@NotBlank(message = "category cannot be blank")
	@Enumerated(EnumType.STRING)
	private CategoryType category;
	
	
	/* decide weather to write the @ManyToOne @JoinColumn(name = "freelancer_id",nullable = false)*/ 
//	private Freelancer freelancer; 
}

