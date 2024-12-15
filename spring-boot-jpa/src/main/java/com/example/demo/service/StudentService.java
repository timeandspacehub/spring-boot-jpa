package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.request.CreateStudentRequest;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	/**
	 * Take Payload/request-dto from user to create a Student Entity and 
	 * then store the entity in DB
	 * 
	 * @param createStudentRequest
	 */
	public Student createStudent(CreateStudentRequest createStudentRequest) {
		// Convert payload to entity class
		Student student = new Student(createStudentRequest);

		// save returns the object/row created in the DB along with ID field
		student = studentRepository.save(student);

		return student;
	}
}
