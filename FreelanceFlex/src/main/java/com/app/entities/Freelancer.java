package com.app.entities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.dto.AddressDTO;

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
@ToString(exclude = "permanentAddress,gigs")
@Table(name = "freelancer")
public class Freelancer extends BaseEntity{
	
	@Column(name = "fname",length = 50)
	private String firstName;
	@Column(name = "lname",length = 50)
	private String lastName;
	@Column(length = 50, unique = true)
	private String email;
	@Column(name = "password",length = 500)
	private String password;
	@Column(length = 15, unique = true)
	private String contactNo;
	@Column(length =500)
	private String description;
	@Lob
	private byte [] profilePicture;
	
//	@Column(columnDefinition = "VARCHAR(255) DEFAULT 'FREELANCER'")
	final private String role = "FREELANCER";
	
	@Column
	private boolean isBlocked=false;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "freelancerAddress",nullable = true)
	//@MapsId
	private Address permanentAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = true)
	//@MapsId
	private Skills skills;
	

	@OneToMany(mappedBy = "freelancer",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	private List <Gigs> gigs = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "freelancer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Reviews> freelancerReview = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "freelancer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Payment> freelancerPayment = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "freelancer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Orders> freelancerOrders = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "freelancer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Issues> freelanceIssues = new ArrayList<>();
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = true)
	//@MapsId
	private SignIn signin;
	

	

}


