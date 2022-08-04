package com.chinmaybiswaltec.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chinmaybiswaltec.restfulwebservices.models.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Integer> {

}
