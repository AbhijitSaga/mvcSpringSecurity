package com.abhijit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhijit.dao.CustomerDAO;
import com.abhijit.dto.Customer;
import com.abhijit.dto.MySecurityUser;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// database call with the username to check user present inside database
	Customer customer=	customerDAO.findCustomerByCustomerName(username);
	
	
	if(customer==null){
		throw new UsernameNotFoundException(username+" not there in the database");
	} 
	
	
	/* first process */
	 /*ArrayList<GrantedAuthority> authorities=new ArrayList<>();
	 SimpleGrantedAuthority role1=new SimpleGrantedAuthority(customer.getRole());
	  authorities.add(role1);
		return new User(customer.getUsername(),customer.getPassword(), authorities);
	*/
	
	/*second process */ 
     /*	UserDetails userDetails=User.withUsername(customer.get(0).getUsername()).password(customer.get(0).getPassword()).roles(customer.get(0).getRole()).build();
     return userDetails; 
     */
		
	/* Third Process */	
	return new MySecurityUser(customer.getUsername(), customer.getPassword(), customer.getRole());
}
}