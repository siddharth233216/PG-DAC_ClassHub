package com.classhub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classhub.dto.AuthRequest;
import com.classhub.dto.Studentdto;
import com.classhub.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/home")
	public ResponseEntity<?> StudentHome(@RequestHeader("Authorization") String authorizationHeader,@RequestBody AuthRequest dto) {
			String jwtToken = authorizationHeader.substring(7);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + jwtToken);
		return new ResponseEntity<>(studentService.getStudent(dto.getEmail()), headers ,HttpStatus.OK);
	}
	
	@PostMapping("/registration")
	public ResponseEntity<?> addStudent(@RequestBody @Valid Studentdto new_student){
		return ResponseEntity.ok(studentService.addStudent(new_student));
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteStudent(@RequestBody Long studentId){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(studentService.deleteStudent(studentId));
		
	}
}
