package com.example.demo.response;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentResponse {

	private long id;

	@JsonProperty("first_name")
	private String firstName;

	private String lastName;

	private String email;

	private String fullName;

	private String street;

	private String city;

	private List<SubjectResponse> learningSubjects;

	public StudentResponse(Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		this.fullName = student.getFirstName() + " " + student.getLastName();

		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();

		if (student.getLearningSubjects() != null) {
			learningSubjects = new ArrayList<SubjectResponse>();
			for (Subject subject : student.getLearningSubjects()) {
				learningSubjects.add(new SubjectResponse(subject));
			}

		}
	}

}
