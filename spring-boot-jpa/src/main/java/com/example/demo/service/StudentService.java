package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.UpdateStudentRequest;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	/**
	 * Take Payload/request-dto from user to create a Student Entity and then store
	 * the entity in DB
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

	/**
	 * Takes DTO passed by controller, updates the data in database, and returns the
	 * updated record back to controller.
	 * 
	 * @param updateStudentRequest
	 * @return
	 */
	public Student updateStudent(UpdateStudentRequest updateStudentRequest) {

		// 1. First, fetch student from the database
		// findById uses primary key and returns Optional, so call get()
		Student student = studentRepository.findById(updateStudentRequest.getId()).get();

		// 2. Second, iff the value is passed by user in payload, then
		// set set that value in the student object we retrieved from DB.
		if (updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()) {
			student.setFirstName(updateStudentRequest.getFirstName());
		}

		student = studentRepository.save(student);

		return student;
	}

	public String deleteStudent(long id) {
		//deleteById method doesn't return anything. But if doesn't 
		//throw any error we will just return a string msg.
		studentRepository.deleteById(id);

		return "Student has been deleted successfully!";
	}
	
	public List<Student> getByFirstName (String firstName){
		return studentRepository.findByFirstName(firstName);
	}

}
