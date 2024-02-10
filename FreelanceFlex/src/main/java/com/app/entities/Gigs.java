package com.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "gigs")
public class Gigs extends BaseEntity{
	
	@Column(length = 50)
	private String title;
	
	@Lob
	private byte [] gigImage;
	
	@Column(length =1000)
	private String description;
	
	private Double price;		
	
	private Integer deliveryTime;
	
	@Enumerated(EnumType.STRING)
	private CategoryType category;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "freelancer_id",nullable = true)
	private Freelancer freelancer;
	
}
