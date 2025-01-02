package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository
public class StudentJDBCTemplateRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	/*
	 * An instance of JdbcTemplate is provided by Spring Boot as long as all DB
	 * connection properties are provided in application.properties file.
	 * 
	 * This is an example of constructor based DI.
	 */
	public StudentJDBCTemplateRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    public List<Student> findAllUsers() {
        String sql = "SELECT * FROM student"; //This is a Native MySQL query
        return jdbcTemplate.query(sql, (rs, rowNum) -> 
        
            new Student(rs.getLong("id"), 
            		    rs.getString("first_name"),
            		    rs.getString("last_name"),
            		    rs.getString("email")
            		)
        );
    }

}
