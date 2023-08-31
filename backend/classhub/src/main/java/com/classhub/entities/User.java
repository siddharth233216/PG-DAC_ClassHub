package com.classhub.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column(length = 20, nullable = false)
	private String firstname;
	@Column(length = 20, nullable = false)
	private String lastname;
	@Column(length = 80, nullable = false, unique = true)
	private String email;
	@Column(name = "Mobile_no", unique = true)
	private long mobile;
	@Column(length = 100, nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name="Role",nullable=false)
	private Role role;
}
