package com.abhijit.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.abhijit.dto.SignupDTO;

@Repository
public class SignupDAOImpl implements SignupDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void saveUser(SignupDTO signupDTO) {
		String theSql = "insert into users values(?,?,?)";
		String theSqlSecond = "insert into authorities values(?,?)";

	/*	jdbcTemplate.update(theSql, signupDTO.getUsername(), signupDTO.getPassword(), 1);
		jdbcTemplate.update(theSqlSecond, signupDTO.getUsername(), "USER"); 
		*/

		
		jdbcTemplate.update(theSql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, signupDTO.getUsername());
				ps.setString(2, signupDTO.getPassword());
				ps.setInt(3, 1);
			}
		});
		
		jdbcTemplate.update(theSqlSecond, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, signupDTO.getUsername());
				ps.setString(2, "USER");
			}
		});
	}

}
