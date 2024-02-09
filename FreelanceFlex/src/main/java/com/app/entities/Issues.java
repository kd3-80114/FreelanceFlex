package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@ToString(exclude = "")
@Table(name = "issues")
public class Issues extends BaseEntity{
	
	private LocalDateTime issueRaisedDate;
	
	private LocalDateTime issueResolvedDate;
	
	@Column(length =1000)
	private String description;
	
	@Column(length = 50)
	private String title;
	
	@Enumerated(EnumType.STRING)
	private IssueType issueStatus;
	
	
	@Enumerated(EnumType.STRING)
	private RoleType issuerRole;
	

	@ManyToOne
	@JoinColumn(name = "freelancer_id",nullable = false)
	private Freelancer freelancer;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id",nullable = false)
	private Buyer buyer;
	
	@ManyToOne
	@JoinColumn(name = "admin_id",nullable = false)
	private Admin admin;
	
}
