package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@ToString(exclude = {"freelancer","buyer"})
@Table(name = "payment")
public class Payment extends BaseEntity{
	
	
	private double amount;
	
	
	private  LocalDateTime transactionDate;
	
	@ManyToOne
	@JoinColumn(name = "freelancer_id",nullable = false)
	private Freelancer freelancer;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id",nullable = false)
	private Buyer buyer;
	
}
