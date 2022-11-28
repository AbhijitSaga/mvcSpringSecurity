package com.abhijit.authenticationProviders;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.abhijit.dao.CustomerDAO;
import com.abhijit.dto.Customer;

@Component
public class MyCustomFormAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// write your authentication logic to authenticate an user
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		// look inside your database if the given user is exists
		Customer customer = customerDAO.findCustomerByCustomerName(username);

		if (customer != null) {
			// validate the user's password
			boolean isMatches = passwordEncoder.matches(password, customer.getPassword());
			if (isMatches) {
				// grant him/her the right roles and create an authentication object and return it
				
			    String role=customer.getRole();
			    ArrayList<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			    authorities.add(new SimpleGrantedAuthority(role));
				return new UsernamePasswordAuthenticationToken(password, customer,authorities);
				
			} else {
                  throw new BadCredentialsException("invalid username/password");
			}

		} else {
			throw new BadCredentialsException("user does not exist");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// check if the authenticate type that user want to authentication with support
		// or not
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
