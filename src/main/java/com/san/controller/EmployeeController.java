package com.san.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.san.model.Employee;
import com.san.service.EmployeeService;


@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/findbyemail/{email}")
	@PreAuthorize("hasAuthority('USER')")
	public Employee getByid(@PathVariable String email) {
		return employeeService.findByEmail(email);
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('USER')")
	public Employee getByid(@RequestBody Employee emp) {
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		return employeeService.save(emp);
	}
	
	@DeleteMapping("/deletebyemail/{email}")
	@PreAuthorize("hasAuthority('USER')")
	public String deleteById(@PathVariable String email, Principal principal) {
		if(email.equals(principal.getName())) {
		employeeService.deleteByEmail(email);
		return "delete successful for : "+email;
		}
		return "Sorry you don't have permission to delete user for : "+email;
	}
	
}
