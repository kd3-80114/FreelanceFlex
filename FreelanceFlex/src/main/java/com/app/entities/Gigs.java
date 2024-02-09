package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@ToString(exclude = "")
@Table(name = "gigs")
public class Gigs extends BaseEntity{
	
	@Column(length = 50)
	private String title;
	
	@Lob
	private byte [] gigImage;
	
	@Column(length =1000)
	private String description;
	
	private double price;		
	
	@Enumerated(EnumType.STRING)
	private CategoryType category;
	
	@ManyToOne
	@JoinColumn(name = "freelancer_id",nullable = false)
	private Freelancer freelancer;
	
	
}
