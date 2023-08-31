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
@Table(name = "Subjects")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column(unique = true, nullable = false, length = 80)
	private String name;

	private LocalDate Startdate;

	private LocalDate EndDate;
	
	@Enumerated(EnumType.STRING)
	private Status subjectStatus;
	
	@ManyToOne
	@JoinColumn(name = "Course")
	private Course course_id;
	
	@ManyToMany
	@JoinTable(name ="Student_subjects",
	joinColumns = @JoinColumn(name="subject_id"),
	inverseJoinColumns = @JoinColumn(name="student_id"))
	private Set<Student> student_list = new HashSet<>();

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return Objects.equals(name, other.name);
	}

}
