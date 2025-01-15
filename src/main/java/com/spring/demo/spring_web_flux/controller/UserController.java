package com.spring.demo.spring_web_flux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.demo.spring_web_flux.model.User;
import com.spring.demo.spring_web_flux.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@GetMapping()
//	public Flux<User> getAllusers()
//	{
//		return userService.findAllUsers();
//	}
	
//	@GetMapping("/{id}")
//	public Mono<User> getUserById(@PathVariable("id") int id)
//	{
//		return userService.findUserById(id);
//	}
	
	@PostMapping()
	public Mono<User> addUser(@RequestBody User user)
	{
		return userService.createUser(user);
	}
	
	@PutMapping("/{id}")
	public Mono<User> modifyUser(@RequestBody User user,@PathVariable("id") int id)
	{
		return userService.updateUser(user,id);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable("id") int id)
	{
		return userService.deleteUserById(id);
	}

}
