package com.abhijit.config;

import java.util.ArrayList;

import javax.annotation.security.PermitAll;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.abhijit.authenticationProviders.MyCustomFormAuthenticationProvider;
import com.abhijit.service.CustomerUserDetailsServiceImpl;

@EnableWebSecurity(debug = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//I want to create some details for an user
//username,password, roles
//abhijit, abhi123, admin	

	@Autowired
	private DataSource theDataSource;
	@Autowired
	private PasswordEncoder thePasswordEncoder;
	@Autowired
	CustomerUserDetailsServiceImpl customerUserDetailsServiceImpl;
	@Autowired
	MyCustomFormAuthenticationProvider myCustomFormAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// load the users info from the db //username , password, role
		
		/*  auth.jdbcAuthentication(). dataSource(theDataSource).
		  usersByUsernameQuery("select username, password, enabled from customers where username=?"
		  ).
		  authoritiesByUsernameQuery("select username, role from customers where username=?"
		  ). passwordEncoder(thePasswordEncoder);
		 */
		
		
		
		/*
		  auth.userDetailsService(customerUserDetailsServiceImpl).passwordEncoder(thePasswordEncoder);
		 
		 */
		
		auth.authenticationProvider(myCustomFormAuthenticationProvider);
		
	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { //let's do some in memory auth /* ArrayList<GrantedAuthority>
	 * roles = new ArrayList<>(); SimpleGrantedAuthority role1 = new
	 * SimpleGrantedAuthority("ADMIN"); SimpleGrantedAuthority role2 = new
	 * SimpleGrantedAuthority("USER"); roles.add(role1); roles.add(role2); User anu
	 * = new User("abhijitsagar1204@gamil.com", "abhijit123", roles);
	 */

	/*
	 * InMemoryUserDetailsManager userDetailsManager = new
	 * InMemoryUserDetailsManager();
	 * 
	 * UserDetails abhijitUser =
	 * User.withUsername("abhijit").password("abhijit123").roles("ADMIN",
	 * "USER").build(); UserDetails SavithaUser =
	 * User.withUsername("savitha").password("savitha123").roles("USER").build();
	 * userDetailsManager.createUser(abhijitUser);
	 * userDetailsManager.createUser(SavithaUser);
	 * auth.userDetailsService(userDetailsManager);
	 * 
	 * }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/coder").hasAuthority("Coder").
		antMatchers("/trainer").hasAuthority("Trainer").
		antMatchers("/","/home").authenticated().
		and().formLogin().loginPage("/myCustomLogin")
				.loginProcessingUrl("/process-login").permitAll().and().httpBasic().and().logout().permitAll().
				and()
				.exceptionHandling().accessDeniedPage("/accessDenied");
	}

}
