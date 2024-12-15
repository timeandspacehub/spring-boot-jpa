package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateStudentRequest {

	@JsonProperty("first_name")
	private String firstName;

	private String lastName;

	private String email;

}
