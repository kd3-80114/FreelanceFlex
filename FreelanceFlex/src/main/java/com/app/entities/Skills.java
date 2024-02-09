package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "skills")
public class Skills extends BaseEntity{
	
	@NotNull
	private String primarySkill;
	
	private String secondarySkill;
	
	private String thirdSkill;
	
	private String fourthSkill;
	
	private String fifthSkill;
	
}
