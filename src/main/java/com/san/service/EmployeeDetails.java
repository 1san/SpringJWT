package com.san.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.san.model.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeDetails implements UserDetails {
	
	private Employee emp;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return emp.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return emp.getPassword();
	}

	@Override
	public String getUsername() {
		return emp.getEmail();
	}

}
