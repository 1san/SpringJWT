package com.san.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.san.model.Employee;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByEmail(String email);

	public void deleteByEmail(String email);
}
