package com.classhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classhub.dto.ApiResponse;
import com.classhub.dto.Coursedto;
import com.classhub.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/view{batch_id}")
	public ResponseEntity<?> getAllCourses(@PathVariable String batch_id){
		return ResponseEntity.ok(courseService.getCourses(batch_id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addCourse(@RequestBody Coursedto new_course){
		Coursedto course = courseService.addCourse(new_course);
		return ResponseEntity.ok("Course has been successfully added");
	}
	
	@DeleteMapping("/delete{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long Course_id){
		ApiResponse response  = courseService.deleteCourse(Course_id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

}
