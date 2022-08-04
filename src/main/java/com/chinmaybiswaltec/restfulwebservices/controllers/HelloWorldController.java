package com.chinmaybiswaltec.restfulwebservices.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.chinmaybiswaltec.restfulwebservices.models.HelloWorld;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorld helloWorldBean() {
		return new HelloWorld("Hello World!");
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorld helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorld(String.format("Hello World! %s",name));
	}
	
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name="Accept-Language", required = false)Locale locale) {
		return messageSource.getMessage("good.morning.message", null,"Default Message", locale);
	}
}
