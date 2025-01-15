package com.spring.demo.spring_web_flux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.spring.demo.spring_web_flux.model.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,Integer>{

}
