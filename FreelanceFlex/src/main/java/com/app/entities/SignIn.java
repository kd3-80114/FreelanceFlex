package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "signIn")

public class SignIn extends BaseEntity{	
	@Column(length = 50)
	private String email;
	@Column(length = 300,nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	private RoleType userRole;
}
