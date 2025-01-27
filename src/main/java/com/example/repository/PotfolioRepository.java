package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.model.Potfolio;
import com.example.model.User;

@Repository
public class PotfolioRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public List<Potfolio> findAll() {
	    return jdbcTemplate.query("SELECT * FROM [tbl_work_potfolio]", new RowMapper<Potfolio>() {
	        @Override
	        public Potfolio mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Potfolio potfolio = new Potfolio();
	            potfolio.setId(rs.getLong("id"));
	            potfolio.setName(rs.getString("name"));
	            potfolio.setDescription(rs.getString("description"));
	            return potfolio;
	        }
	    });
	}
}