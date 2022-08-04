package com.chinmaybiswaltec.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chinmaybiswaltec.restfulwebservices.models.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
