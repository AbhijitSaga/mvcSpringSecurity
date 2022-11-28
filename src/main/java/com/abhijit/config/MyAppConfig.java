package com.abhijit.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com")
public class MyAppConfig {

	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public JdbcTemplate MyJdbcTemplate() {
		JdbcTemplate theJdbcTemplate = new JdbcTemplate(dataSource());
		return theJdbcTemplate;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	//	driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/evening");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/customer");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return driverManagerDataSource;
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource());
		jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from customers where username=?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, role from customers where username=?");
		jdbcUserDetailsManager.setChangePasswordSql("update customers set password = ? where username = ?");
		jdbcUserDetailsManager.setDeleteUserAuthoritiesSql("delete from customers where username = ?");
		jdbcUserDetailsManager.setCreateUserSql("insert into customers(username, password, enabled) values (?,?,?)");
		jdbcUserDetailsManager.setCreateAuthoritySql("update customers set role =? where username=?");;
		return jdbcUserDetailsManager;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
