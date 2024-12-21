package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.InQueryRequest;
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
		// deleteById method doesn't return anything. But if doesn't
		// throw any error we will just return a string msg.
		studentRepository.deleteById(id);

		return "Student has been deleted successfully!";
	}

	public List<Student> getByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName);
	}

	public Student getByFirstNameAndLastName(String firstName, String lastName) {
//		return studentRepository.findByFirstNameAndLastName(firstName, lastName);
		return studentRepository.getByLastNameAndFirstName(lastName, firstName);
	}

	public List<Student> getByFirstNameOrLastName(String firstName, String lastName) {
		return studentRepository.findByFirstNameOrLastName(firstName, lastName);
	}

	public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest) {
		return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
	}

	public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize) {
		// Spring data provides pageable interface, select it from springframework
		// not from awt.
		// PageRequest.of is zero based page index, so you need to pass 0 for page 1.
		// So, you need to do -1
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return studentRepository.findAll(pageable).getContent();
	}

	public List<Student> getAllStudentsWithSorting(){	
		//1. Sort class provided by Spring Data, which contains "by" static method
		//2. First param is direction(ascending, descending)
		//3. Second param is property name from Entity class & it must match exactly!!!!
		//4. Separate as many fields as needed separated by comma
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName");
		
		return studentRepository.findAll(sort);
	}
	
	public List<Student> like(String firstNameSubString){	
		return studentRepository.findByFirstNameContains(firstNameSubString);
	}
	
	public List<Student> startsWith(String value){	
		return studentRepository.findByFirstNameStartsWith(value);
	}
	
	public Integer updateStudentWithJpql(Long id, String firstName) {
		return studentRepository.updateFirstName(id, firstName);
	}
	
	public Integer deleteStudent(String firstName) {
		return studentRepository.deleteByFirstName(firstName);
	}
	
}
