package com.san.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.san.model.Employee;

@Service
public class EmployeeDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp=employeeService.findByEmail(username);
		if(emp==null) {
			throw new UsernameNotFoundException("User not exist!");
		}else {
			return new EmployeeDetails(emp);
		}
		
	}

}
