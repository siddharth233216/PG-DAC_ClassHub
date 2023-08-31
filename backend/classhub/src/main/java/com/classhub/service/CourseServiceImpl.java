package com.classhub.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classhub.custom_exceptions.CustomException;
import com.classhub.custom_exceptions.ResourceNotFoundException;
import com.classhub.dto.ApiResponse;
import com.classhub.dto.Coursedto;
import com.classhub.entities.Course;
import com.classhub.entities.Status;
import com.classhub.repository.CourseRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseRepository courserepo;
	
	@Autowired 
	ModelMapper mapper;

	@Override
	public Coursedto addCourse(Coursedto new_course) {
		// TODO Auto-generated method stub
		if(new_course.getStartDate().isAfter(new_course.getEndDate())){
			throw new CustomException("End Date cannot be before start date");
		}
		Course newCourse = mapper.map(new_course, Course.class);
		return mapper.map(courserepo.save(newCourse),Coursedto.class);
	}

	@Override
	public ApiResponse deleteCourse(Long course_id) {
		// TODO Auto-generated method stub
		Course course = courserepo.findById(course_id).orElseThrow(()->new ResourceNotFoundException("Course Id mismatch"));
		if(course.getStatus()==Status.COMPLETED) {
			return new ApiResponse("Course is already over unable to delete the course");
		}
		return new ApiResponse(course.getType()+course.getBatch()+"successfully deleted");
	}

	@Override
	public List<Coursedto> getCourses(String batch_id) {
		// TODO Auto-generated method stub
		ArrayList<Coursedto> response = new ArrayList<>();
		ArrayList<Course> courses = courserepo.findByBatch(batch_id);
		if(courses.size()==0) {
			throw new CustomException("Course for the batch id doesn't exist");
		}
		courses.forEach((course)->response.add(mapper.map(course, Coursedto.class)));
		return response;
	}

}
