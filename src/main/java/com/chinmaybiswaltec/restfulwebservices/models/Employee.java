package com.chinmaybiswaltec.restfulwebservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
	
	private String firstName;
	private String lasttName;
	private Integer age;
	@JsonIgnore
	private String state;

}
