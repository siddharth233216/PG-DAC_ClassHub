package com.classhub.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column(name = "Name")
	private String Type;
	@Column(nullable = false)
	private String batch;
	@Column(nullable = false)
	private Status status = Status.NOT_YET_STARTED;
	@OneToMany(mappedBy = "course_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Subject> subjects = new ArrayList<>();
	@OneToMany(mappedBy = "selected_course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<>();
	@Column(name = "Start_date")
	private LocalDate startDate;
	@Column(name = "End_date")
	private LocalDate endDate;

	public void addSubject(Subject new_subject) {
		subjects.add(new_subject);
		subjects.forEach(subject->subject.setCourse_id(this));
	}

	public Subject removeSubject(Subject s) {
		if (subjects.remove(s))
			return s;
		return null;
	}

	public void addStudent(Student new_student) {
		students.add(new_student);
		subjects.forEach(subject -> subject.getStudent_list().add(new_student));
	}

	public Student removeStudent(Student student) {
		if (students.remove(student)) {
			subjects.forEach(e -> e.getStudent_list().remove(student));
			return student;
		}
		return null;
	}
//	public void addSubject(Collection<Subject> new_subjects) {
//		subjects.addAll(new_subjects);
//		new_subjects.forEach(subject->subject.setCourse_id(this));
//	}
	
	public void removeSubject(Collection<Subject> subject){
		subjects.removeAll(subject);
	}
	
//	public void addStudent(Collection<Subject> new_students) {
//		subjects.addAll(new_students);
//		new_students.forEach(subject->subject.setCourse_id(this));
//	}
	
	public void removeStudent(Collection<Subject> students){
		subjects.removeAll(students);
	}
}
