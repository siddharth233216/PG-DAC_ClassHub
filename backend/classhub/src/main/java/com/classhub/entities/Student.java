package com.classhub.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
@Table(name="Students")
public class Student extends User {
	
	@Column(name = "Roll_no",unique=true,nullable = false)
private long Roll_no;
	
	@Enumerated(EnumType.STRING)
private Placement status=Placement.NOT_PLACED;
	
	@Column(name="Dob",nullable=false)
private LocalDate dob;
	
@Column(unique=true,nullable = false)
private long PRN;

@ManyToOne
@JoinColumn(name="Course",nullable=false)
private Course selected_course;

@Override
public int hashCode() {
	return Objects.hash(PRN, Roll_no);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Student other = (Student) obj;
	return PRN == other.PRN && Roll_no == other.Roll_no;
}



}
