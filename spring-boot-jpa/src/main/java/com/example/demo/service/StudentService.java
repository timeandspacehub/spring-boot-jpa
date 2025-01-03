package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentJDBCTemplateRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.CreateSubjectRequest;
import com.example.demo.request.InQueryRequest;
import com.example.demo.request.UpdateStudentRequest;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentJDBCTemplateRepository studentJDBCTemplateRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	


	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	/**
	 * Take Payload/request-dto from user to create a Student Entity and then store
	 * the entity in DB. 
	 * 
	 * Student Entity "Has-a-Relationship" with Address Entity.
	 * 
	 * @param createStudentRequest
	 */
	public Student createStudent(CreateStudentRequest createStudentRequest) {
		//1.  Convert payload to entity class
		Student student = new Student(createStudentRequest);
		
		//2. Before we persist the student object, create a new object of Address
		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		
		address = addressRepository.save(address);
		
		//3. id value is returned after saving the address object, which
		// will be the Foreign Key for the student table record that we are going 
		// to insert next.
		student.setAddress(address);

		// save returns the object/row created in the DB along with ID field
		student = studentRepository.save(student);

		//4. Save all the subjects the student is studying in the Subject table.
		List<Subject> subjectsList = new ArrayList<>();

		if (createStudentRequest.getSubjectsLearning() != null) {
			for (CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjectsLearning()) {
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarksObtained(createSubjectRequest.getMarksObtained());
				subject.setStudent(student);

				subjectsList.add(subject);
			}
		}
		subjectRepository.saveAll(subjectsList);
		
		//When the student is saved, the student object returned doesn't contain
		//subjects associated with it, so we need to manually set it. 
		student.setLearningSubjects(subjectsList);
		
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
	
	public List<Student> getByCity(String city) {
//		return studentRepository.findByAddressCity(city);
		return studentRepository.getByAddressCity(city);
	}
	
	public List<Student> getAllStudentsUsingJDBCTemplate(){
		return studentJDBCTemplateRepository.findAllUsers();
	}
	
}
