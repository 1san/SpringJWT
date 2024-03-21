package com.san.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.san.model.Employee;
import com.san.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	public Employee findByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}

	public void deleteById(long id) {
		employeeRepository.deleteById(id);
	}

	public void deleteByEmail(String email) {
		employeeRepository.deleteByEmail(email);		
	}

}
