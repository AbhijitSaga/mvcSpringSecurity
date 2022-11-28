package com.abhijit.controller;

import java.security.Principal;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abhijit.dto.ChangePasswordDTO;

@Controller
public class HelloWorldController {

	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Principal means username
	@GetMapping("/home")
	public String helloWorld(Principal principal, Authentication auth, Model model) {
		String username = principal.getName();
		System.out.println("loged in user is: " + username);

		Collection<?> authorities = auth.getAuthorities();
		System.out.println("loged in user authoritys is: " + authorities);

		model.addAttribute("username", username);
		model.addAttribute("role", authorities);
		return "home-page";
	}

	// deleteUser
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username") String username) {
		jdbcUserDetailsManager.deleteUser(username);
		System.out.println("deleted user : " + username);
		return "redirect:/myCustomLogin";
	}

	@GetMapping("/changePassword")
	public String changePassword(Model model) {
		model.addAttribute("password-chng", new ChangePasswordDTO());

		return "change-password";
	}

	@PostMapping("/save-change-password")
	public String saveChangePassword(Principal principal, ChangePasswordDTO changePasswordDTO) {

		//get user data by username , principal object get currentlogin username
		UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(principal.getName());

		// check the old password is correct
		boolean theMatches = passwordEncoder.matches(changePasswordDTO.getOldPassword(), userDetails.getPassword());

		// check wheather the new password and confirm password matches 
		if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
			return "redirect:/changePassword?newPasswordNotMatch";
		}

		if (theMatches) {
			String encodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());

			// write some logic to save the new password inside the database
			jdbcUserDetailsManager.changePassword(changePasswordDTO.getOldPassword(), encodedPassword);
			System.out.println(changePasswordDTO);
			return "redirect:/home";
		}
		return "redirect:/changePassword?invalidPassword";
	}

	@GetMapping("/trainer")
	public String showTrainerDashBoard() {
		return "trainer-dashboard";
	}

	@GetMapping("/coder")
	public String showCoderDashBoard() {
		return "coder-dashboard";
	}

	@GetMapping("/accessDenied")
	public String error() {
		return "access-denied";
	}
}
