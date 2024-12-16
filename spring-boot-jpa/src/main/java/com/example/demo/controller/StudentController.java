package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("getAll")
	public List<StudentResponse> getAllStudent() {
		List<Student> studentList = studentService.getAllStudents();

		List<StudentResponse> studentResponseList = new ArrayList<>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});

		return studentResponseList;

	}

	@PostMapping("create")
	public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
		Student student = studentService.createStudent(createStudentRequest);

		return new StudentResponse(student);
	}

	@PutMapping("update")
	public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
		Student student = studentService.updateStudent(updateStudentRequest);

		return new StudentResponse(student);
	}

	/*
	 * @DeleteMapping("delete") public String deleteStudent(@RequestParam long id) {
	 * return studentService.deleteStudent(id); }
	 */

	@DeleteMapping("delete/{id}")
	public String deleteStudent(@PathVariable long id) {
		return studentService.deleteStudent(id);
	}

	@GetMapping("getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName(@PathVariable String firstName) {
		// 1. Get list of Students
		List<Student> studentList = studentService.getByFirstName(firstName);

		// 2. Convert list of Students to list of StudentResponse
		List<StudentResponse> studentResponseList = new ArrayList<>();
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});

		return studentResponseList;
	}

	@GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
	public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
		// 1. Get student
		Student student = studentService.getByFirstNameAndLastName(firstName, lastName);

		// 2. Convert Student object to StudentResponse
		return new StudentResponse(student);
	}

	@GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName,
			@PathVariable String lastName) {
		// 1. Get list of Students
		List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);

		// 2. Convert list of Students to list of StudentResponse
		List<StudentResponse> studentResponseList = new ArrayList<>();
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});

		return studentResponseList;
	}

}
