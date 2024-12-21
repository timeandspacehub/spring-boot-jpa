package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	//1. Whenever your query is updating data in the table, then you need to mark it with 
	// @Modify annotation in JPQL.
	//2. Make sure @Transactional is imported from Spring Framework NOT Jakarta
	//3. If you don't provide @Modifying and @Transcational, you will get an exception
	//4. When JPQL updates it returns either void or integer ONLY when you use @Modifying.
	// The number returned is the no. of rows affected by this update query. 
	@Modifying
	@Transactional
	@Query("UPDATE Student SET firstName = :firstName WHERE id = :id")
	Integer updateFirstName(Long id, String firstName);
	
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Student WHERE firstName = :firstName")
	Integer deleteByFirstName(String firstName);
}
