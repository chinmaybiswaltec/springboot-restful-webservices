package com.chinmaybiswaltec.restfulwebservices.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	private String description;
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
