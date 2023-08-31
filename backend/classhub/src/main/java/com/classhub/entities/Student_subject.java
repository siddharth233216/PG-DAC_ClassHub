package com.classhub.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "student_subject")
public class Student_subject implements Serializable {


	@EmbeddedId
    private Student_subjectId id = new Student_subjectId();

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @MapsId("subjectId")
    private Subject subject;
    
    @Embedded
    @ElementCollection
    @CollectionTable
    private List<Exam> exam = new ArrayList<>();
    
    @Embedded
    @ElementCollection
    @CollectionTable
    private List<Attendence> attendance = new ArrayList<>();

    
}
