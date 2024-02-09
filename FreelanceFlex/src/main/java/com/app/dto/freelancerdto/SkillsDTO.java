package com.app.dto.freelancerdto;


import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class SkillsDTO 
{
//	@JsonProperty(access = Access.READ_ONLY)
//	private Long id;
	
	@NotBlank(message = "primarySkill cannot be blank")
	private String primarySkill;
	
	private String secondarySkill;
	
	private String thirdSkill;
	
	private String fourthSkill;
	
	private String fifthSkill;

}