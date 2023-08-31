package com.classhub.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.classhub.entities.Status;
import com.classhub.entities.Student;
import com.classhub.entities.Subject;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Coursedto {
	@NotBlank(message = "Name of the course cannot be blank")
	private String Type;
	@NotBlank(message = "BatchID cannot beb blank")
	private String batch;
	@NotBlank(message = "please select the status fro the course")
	private Status status = Status.NOT_YET_STARTED;
	
	private List<Student> students = new ArrayList<>();
	
	private List<Subject> subjects = new ArrayList<>();
	
	@NotBlank(message = "Enter a start date")
	private LocalDate startDate;
	
	@NotBlank(message = "Enter a start date")
	private LocalDate endDate;
}
