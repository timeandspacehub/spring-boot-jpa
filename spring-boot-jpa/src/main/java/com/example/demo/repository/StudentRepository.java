package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	
	List<Student> findByFirstName(String firstName);
	
	//Whatever order is in the method name, same name should be provided
	//as the parameter name
	Student findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Student> findByFirstNameOrLastName(String firstName, String lastName);
	
	List<Student> findByFirstNameIn(List<String> firstNames);
	
	List<Student> findByFirstNameContains(String firstNameSubString);
	
	List<Student> findByFirstNameStartsWith(String value);
	
	//1. JPQL takes entity class's name and fields NOT table name and columns
	//2. Whatever value you want to query with should be provided with a colon and variable name
	//3. If the variable inside the JPQL query doesn't match method parameter EXACTLY, then you 
	//need to use @Param annotation.
	//4. Another way to write this JPQL is using index - Not Recommended - Check Notes!!!
	@Query("FROM Student WHERE firstName = :firstname AND lastName = :lastName")
	Student getByLastNameAndFirstName(String lastName, @Param("firstname") String firstName);

}
