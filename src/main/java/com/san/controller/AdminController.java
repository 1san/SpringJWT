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
import org.springframework.web.bind.annotation.RestController;

import com.san.model.Employee;
import com.san.service.EmployeeService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/findbyid/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Optional<Employee> getByid(@PathVariable long id) {
		return employeeService.findById(id);
	}
	
	@GetMapping("/findbyemail/{email}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Employee getByid(@PathVariable String email) {
		return employeeService.findByEmail(email);
	}
	
	@GetMapping("/findall")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Employee> getAll() {
		return employeeService.findAll();
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Employee getByid(@RequestBody Employee emp) {
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		return employeeService.save(emp);
	}
	
	@DeleteMapping("/deletebyid/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteById(@PathVariable long id) {
		employeeService.deleteById(id);
		return "delete successful : "+id;
	}
	
	@DeleteMapping("/deletebyemail/{email}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteById(@PathVariable String email) {
		employeeService.deleteByEmail(email);
		return "delete successful for : "+email;
	}
	
}
