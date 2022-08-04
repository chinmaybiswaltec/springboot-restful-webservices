package com.chinmaybiswaltec.restfulwebservices.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.chinmaybiswaltec.restfulwebservices.exceptions.UserNotFoundException;
import com.chinmaybiswaltec.restfulwebservices.models.User;

@Repository
public class UserRepository {

	private static List<User> users = new ArrayList<>();
	private static int idCounter=4;
	static {
		users.add(new User(1,"Chinmay", new Date()));
		users.add(new User(2,"Sushree", new Date()));
		users.add(new User(3,"Saina", new Date()));
		users.add(new User(4,"Eshani", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++idCounter);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(Integer id) {
		Optional<User> opt = users.stream().filter(user-> user.getId()==id).findAny();
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new UserNotFoundException("id->"+id);
		}
	}
	
	public User deleteById(Integer id) {
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.getId()==id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}
}
