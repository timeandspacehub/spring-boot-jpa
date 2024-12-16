package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;
import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	
	List<Student> findByFirstName(String firstName);

}
