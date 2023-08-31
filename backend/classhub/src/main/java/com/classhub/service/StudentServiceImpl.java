package com.classhub.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classhub.custom_exceptions.CustomException;
import com.classhub.custom_exceptions.ResourceNotFoundException;
import com.classhub.dto.ApiResponse;
import com.classhub.dto.Studentdto;
import com.classhub.entities.Course;
import com.classhub.entities.Status;
import com.classhub.entities.Student;
import com.classhub.repository.CourseRepository;
import com.classhub.repository.StudentRepository;

@Transactional
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentRepository studentrepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	CourseRepository courserepo;

	@Override
	public ApiResponse addStudent(Studentdto new_student) {
		Course course = courserepo.findById(new_student.getCourse_id()).orElseThrow(()-> new ResourceNotFoundException("course not available"));
		if(course.getStatus()==Status.COMPLETED) {
			throw new CustomException("Unable to enroll as the course is no longer available");
		}
		Student validated_student = mapper.map(new_student, Student.class);
		course.addStudent(validated_student);
		studentrepo.save(validated_student);
		return new ApiResponse("student successfully Enrolled");
	}

	@Override
	public Studentdto getStudent(String email) {
		// TODO Auto-generated method stub
		Student student = (Student)studentrepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("student with the following details doesnot exist"));
		Studentdto studentdto = mapper.map(student, Studentdto.class);
		studentdto.setCourse_id(student.getSelected_course().getId());
		return null;
	}

	@Override
	public ApiResponse deleteStudent(Long studentId) {
		// TODO Auto-generated method stub
		Student student = (Student)studentrepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student doesn't exist"));
		if(student.getSelected_course().getStatus()==Status.COMPLETED) {
			throw new CustomException("unable to delete student as the student has already finished the course");
		}
		studentrepo.delete(student);
		return new ApiResponse("Student succesfully deleted");
	}
	
	
	
	
	

}
