package com.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentDTO {
	
//	@NotNull
// 	private Long buyerId;
//	@NotNull
//	private Long freelancerId;
	// this is for testing
	
	@NotNull(message = "Payment cannot be null")
	private double amount;
	
	@DateTimeFormat
	private  LocalDateTime transactionDate;
	
	@JsonIgnore
	private Buyer buyer;
	@JsonIgnore
	private Freelancer freelancer;

}
