package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/v2/student/")
public class StudentJDBCTemplateController {
	
	@Autowired
	StudentService studentService;
	
	
	@GetMapping("getAll")
	public List<StudentResponse> getAllStudent() {
		List<Student> studentList = studentService.getAllStudentsUsingJDBCTemplate();

		List<StudentResponse> studentResponseList = new ArrayList<>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});

		return studentResponseList;
	}

}
