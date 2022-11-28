package com.abhijit.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.abhijit.dao.SignupDAO;
import com.abhijit.dto.SignupDTO;

@Controller
public class LoginController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@GetMapping("/myCustomLogin")
	public String customLogin() {

		return "login";
	}

	@GetMapping("/signup")
	public String signup(@ModelAttribute("signupdto") SignupDTO signupDTO) {
		return "signup";
	}

	@PostMapping("/process-signup")
	public String processSignup(SignupDTO signupDTO) {
		String pass = passwordEncoder.encode(signupDTO.getPassword());// encode password
		signupDTO.setPassword(pass);// set Bcrypt password in dto class
		UserDetails user = User.withUsername(signupDTO.getUsername()).password(signupDTO.getPassword()).authorities("Coder")
				.build();
	    
		jdbcUserDetailsManager.createUser(user);

		System.out.println(pass);
		// save the dto : do DB call
		// signupDAO.saveUser(signupDTO);
		return "redirect:/myCustomLogin";
	}
}
