package com.chinmaybiswaltec.restfulwebservices.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chinmaybiswaltec.restfulwebservices.exceptions.UserNotFoundException;
import com.chinmaybiswaltec.restfulwebservices.models.Post;
import com.chinmaybiswaltec.restfulwebservices.models.User;
import com.chinmaybiswaltec.restfulwebservices.repositories.PostJpaRepository;
import com.chinmaybiswaltec.restfulwebservices.repositories.UserJpaRepository;

@RestController
public class UserJpaController {
	@Autowired
	private UserJpaRepository userRepository;
	
	@Autowired
	private PostJpaRepository postRepository;

	@GetMapping(path = "/jpa/users")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id: " + id);
		}
		EntityModel model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getAllUser());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> getAllUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id: " + id);
		}
		return user.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Post post) {
		Optional<User> userOpt = userRepository.findById(id);
		if (!userOpt.isPresent()) {
			throw new UserNotFoundException("id: " + id);
		}
		User user = userOpt.get();
		post.setUser(user);
		postRepository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
