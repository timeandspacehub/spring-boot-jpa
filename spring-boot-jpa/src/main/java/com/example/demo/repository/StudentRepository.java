package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;
import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	
	List<Student> findByFirstName(String firstName);
	
	//Whatever order is in the method name, same name should be provided
	//as the parameter name
	Student findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Student> findByFirstNameOrLastName(String firstName, String lastName);
	
	List<Student> findByFirstNameIn(List<String> firstNames);

}
