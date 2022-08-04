package com.chinmaybiswaltec.restfulwebservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chinmaybiswaltec.restfulwebservices.models.Name;
import com.chinmaybiswaltec.restfulwebservices.models.PersonV1;
import com.chinmaybiswaltec.restfulwebservices.models.PersonV2;

@RestController
public class PersonVersioningController {
	
	// URI versioning (pollutes the URI)
	//Possible to cache
	@GetMapping(path="v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Chinmay Biswal");
	}
	
	@GetMapping(path="v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Chinmay", "Biswal"));
	}
	//Versioning using param. Request Param Versioning (pollutes the URI)
	//Possible to cache
	
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 getPersonParam1() {
		return new PersonV1("Chinmay Biswal");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 getPersonParam2() {
		return new PersonV2(new Name("Chinmay", "Biswal"));
	}

	//Versioning using Header
	// Doesnt pollute URI but header is not meant for versioning and doesnt cache
	
		@GetMapping(value="/person/header", headers = "X-API-VERSION=1")
		public PersonV1 getPersonHeader1() {
			return new PersonV1("Chinmay Biswal");
		}
		
		@GetMapping(value="/person/header", headers = "X-API-VERSION=2")
		public PersonV2 getPersonHeader2() {
			return new PersonV2(new Name("Chinmay", "Biswal"));
		}
		//Versioning using Produces MIME Type Versioning
		
			@GetMapping(value="/person/produces", produces = "application/company.app-v1+json")
			public PersonV1 getPersonProduces1() {
				return new PersonV1("Chinmay Biswal");
			}
			
			@GetMapping(value="/person/produces", produces = "application/company.app-v2+json")
			public PersonV2 getPersonProduces2() {
				return new PersonV2(new Name("Chinmay", "Biswal"));
			}	
}
