package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString(exclude = {"permanentAddress","buyerPayment","buyerReview"})
@Table(name = "buyer")
public class Buyer extends BaseEntity implements User{
	
	@Column(name = "fname",length = 50)
	private String firstName;
	@Column(name = "lname",length = 50)
	private String lastName;
	@Column(length = 50, unique = true)
	private String email;
	@Column(length = 15, unique = true)
	private String contactNo;
	@Column(length =500)
	private String description;
	@Lob
	private byte [] profilePicture;
	@Column(name = "password",length = 500)
	private String password;
//	@Column(columnDefinition = "VARCHAR(255) DEFAULT 'BUYER'")
	final private String role = "BUYER";
	
	@Column
	private boolean isBlocked=false;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "buyerAddress",nullable = false)
	//@MapsId somechanges done here
	private Address permanentAddress;
	
	@JsonIgnore
	@OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Reviews> buyerReview = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Payment> buyerPayment = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Orders> buyerOrders = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Issues> buyerIssues = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = true)
	private SignIn signIn;


	}


