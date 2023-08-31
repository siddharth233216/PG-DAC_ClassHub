package com.classhub.dto;

import com.classhub.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Userdto {

	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private long mobile;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private Role role;
}
