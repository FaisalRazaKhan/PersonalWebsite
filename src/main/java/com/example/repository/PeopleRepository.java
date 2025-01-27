package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.People;

@Repository
public class PeopleRepository {

	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 
	 //Save People Response
	 public void saveRes(People people) {
		 String sql = "INSERT INTO [dbo].[tbl_people] (name, email, message) VALUES (?, ?, ?)";
	        jdbcTemplate.update(sql, people.getName(), people.getEmail(), people.getMessage());
	 }
}
