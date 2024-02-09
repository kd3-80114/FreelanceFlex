package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
@ToString(exclude = "freelancer,buyer")
@Table(name = "reviews")
public class Reviews extends BaseEntity{
	
	@Column(length = 50)
	private String title;
	
	@Column(length = 500)
	private String description;
	
	@Min(value = 1)
	@Max(value = 5)
	private int rating;
	
	
	@ManyToOne
	@JoinColumn(name = "freelancer_id",nullable = false)
	private Freelancer freelancer;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id",nullable = false)
	private Buyer buyer;
	
	
	
}
