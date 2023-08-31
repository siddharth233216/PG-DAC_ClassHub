package com.classhub.service;

import java.util.List;

import com.classhub.dto.ApiResponse;
import com.classhub.dto.Coursedto;

public interface CourseService {

	Coursedto addCourse(Coursedto new_course);

	ApiResponse deleteCourse(Long course_id);

	List<Coursedto> getCourses(String batch_id);
}
