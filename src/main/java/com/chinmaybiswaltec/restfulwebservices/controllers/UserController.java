package com.chinmaybiswaltec.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chinmaybiswaltec.restfulwebservices.exceptions.UserNotFoundException;
import com.chinmaybiswaltec.restfulwebservices.models.User;
import com.chinmaybiswaltec.restfulwebservices.repositories.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path="/users")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public EntityModel<User> getUser(@PathVariable Integer id) {
		User user = userRepository.findOne(id);
		EntityModel model = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getAllUser());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}").buildAndExpand(savedUser.getId()).toUri();	
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User user = userRepository.deleteById(id);
		if(user == null ) {
			throw new UserNotFoundException("id->"+id);
		}
	}

}
