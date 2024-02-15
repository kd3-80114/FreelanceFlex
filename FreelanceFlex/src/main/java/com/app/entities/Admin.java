package com.app.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
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
@Table(name = "admin")
public class Admin extends BaseEntity implements User{
	
	@Column(name = "fname",length = 50)
	private String firstName;
	@Column(name = "lname",length = 50)
	private String lastName;
	@Column(length = 50)
	private String email;
	@Column(length = 15)
	private String contactNo;	
	@Lob
	private byte [] profilePicture;
	final private String role = "ADMIN";
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private SignIn signin;
	
//	@OneToMany(mappedBy = "admin",cascade = CascadeType.ALL,orphanRemoval = true)
//	private List<Issues> userIssues = new ArrayList<>();
	
	
}
