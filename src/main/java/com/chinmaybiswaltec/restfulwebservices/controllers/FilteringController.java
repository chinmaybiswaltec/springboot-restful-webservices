package com.chinmaybiswaltec.restfulwebservices.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chinmaybiswaltec.restfulwebservices.models.Employee;

@RestController
public class FilteringController {
	
	@GetMapping(path="/employee")
	public Employee getEmployee() {
		return new Employee("Chinmay","Biswal",30,"Odisha");
	}
	
	@GetMapping(path="/employee-list")
	public List<Employee> getEmployeeList() {
		return Arrays.asList(new Employee("Chinmay","Biswal",30,"Odisha"),new Employee("Sushree","Biswal",25,"Odisha"));
	}

}
