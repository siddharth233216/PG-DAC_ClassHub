package com.classhub.entities;

import javax.persistence.Embeddable;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Exam {
	private Exam_type type;
	private int marksObtained;
	private int total_marks;
}
