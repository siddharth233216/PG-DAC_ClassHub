package com.classhub.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classhub.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	ArrayList<Course> findByBatch(String batch);

}
