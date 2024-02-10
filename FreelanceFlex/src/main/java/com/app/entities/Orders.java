package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@ToString(exclude = "")
@Table(name = "orders")
public class Orders extends BaseEntity{
	
	private double amount;
	
	private LocalDateTime startDate;
	
	private LocalDateTime deliveryDate;
	
	@ManyToOne
	@JoinColumn(name = "freelancer_id",nullable = false)
	private Freelancer freelancer;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id",nullable = false)
	private Buyer buyer;
	
	@OneToOne
	@JoinColumn(name = "gigs_id",nullable = false)
	private Gigs gigs;
}
