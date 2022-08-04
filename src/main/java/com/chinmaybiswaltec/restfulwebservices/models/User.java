package com.chinmaybiswaltec.restfulwebservices.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 2, message = "Name should have minimum 2 chars")
	private String name;
	@Past
	private Date birthDate;
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public User() {
		
	}
	
	
}
