package com.abhijit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abhijit.dto.Customer;
@Repository
public class CustomerDAOImpl implements CustomerDAO {
@Autowired	
private JdbcTemplate jdbcTemplate;

	@Override
	public Customer findCustomerByCustomerName(String name) {
	String theSql="SELECT * FROM customers where username=?";
	Object[] args= {name};
	Customer customer=	jdbcTemplate.queryForObject(theSql, args, new BeanPropertyRowMapper<Customer>(Customer.class));
		return customer;
	}

}
