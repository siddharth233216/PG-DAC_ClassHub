package com.classhub.dto;

import java.time.LocalDate;

import javax.validation.constraints.*;

import com.classhub.entities.Placement;
import com.classhub.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Studentdto {

	@NotBlank(message = "First name cannot be blank")
	@Size(min = 4, max = 20, message = "Invalid length of First name")
	private String firstname;
	
	
	@NotBlank(message = "First name cannot be blank")
	@Size(min = 4, max = 20, message = "Invalid length of last name")
	private String lastname;
	
	@Email
	private String email;
	
	@NotBlank(message = "Please enter a mobile number")
	private long mobile;
	
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})"
			, message = "Password must include atleast one smallcase and uppercase letters and also \"0-9\" ,Symbol")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Role role = Role.STUDENT;
	
	@JsonProperty(access = Access.READ_ONLY)
	private long Roll_no;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Placement status = Placement.NOT_PLACED;
	
	
	@NotBlank(message = "Provide a proper date")
	private LocalDate dob;
	
	@NotBlank(message = "Enter a valid PRN")
	@Pattern(regexp = "[0-9]")
	private long PRN;
	
	
	@NotBlank(message = "Course id cannot be null")
	private long Course_id;

}
