package com.abhijit.dao;


import com.abhijit.dto.Customer;

public interface CustomerDAO {

	Customer	findCustomerByCustomerName( String name);
	
}
