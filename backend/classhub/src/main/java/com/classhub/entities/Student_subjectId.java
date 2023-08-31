package com.classhub.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Student_subjectId implements Serializable {



	private Long studentId;

	private Long subjectId;

	@Override
	public int hashCode() {
		return Objects.hash(studentId, subjectId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student_subjectId other = (Student_subjectId) obj;
		return Objects.equals(studentId, other.studentId) && Objects.equals(subjectId, other.subjectId);
	}

}
