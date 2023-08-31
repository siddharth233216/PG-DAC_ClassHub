package com.classhub.service;

import com.classhub.dto.ApiResponse;
import com.classhub.dto.Studentdto;

public interface StudentService {
	ApiResponse addStudent(Studentdto new_student);

	Studentdto getStudent(String email);

	ApiResponse deleteStudent(Long studentId);
}
