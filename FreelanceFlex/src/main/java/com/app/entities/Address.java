package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@ToString
@Table(name = "address")
public class Address extends BaseEntity{
	
	@Column(length = 50)
	private String country;
	@Column(length = 50)
	private String state;
	@Column(length = 50)
	private String city;
	@Column(length = 200)
	private String landmark;
	@Column(length = 10)
	private String pincode;
	
}
