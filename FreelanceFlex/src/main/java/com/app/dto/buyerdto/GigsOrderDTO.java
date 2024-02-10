package com.app.dto.buyerdto;

import javax.validation.constraints.NotNull;

import com.app.dto.freelancerdto.FreelancerGigsDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GigsOrderDTO {

	@NotNull
	private Long id;
	@NotNull
	private Integer deliveryTime;
	@NotNull
	private Double price; 
	@NotNull
	private FreelancerGigsDTO freelancer;
}
