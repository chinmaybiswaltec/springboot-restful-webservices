package com.chinmaybiswaltec.restfulwebservices.models;

import lombok.Data;

@Data
public class HelloWorld {

	private String message;
	public HelloWorld(String message) {
		this.message = message;
	}
}
