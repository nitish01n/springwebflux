package com.spring.demo.spring_web_flux.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.demo.spring_web_flux.exceptionhandler.UserNotFoundException;
import com.spring.demo.spring_web_flux.model.User;
import com.spring.demo.spring_web_flux.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Mono<ServerResponse> findAllUsers(ServerRequest request)
	{
		Flux<User> users = userRepository.findAll();
		return ServerResponse.ok()
//				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(users,User.class);
	}

	public Mono<ServerResponse> findUserById(ServerRequest request) {
		
		
		 int id = Integer.valueOf(request.pathVariable("id"));
		    Mono<User> user = userRepository.findById(id)
		            .switchIfEmpty(Mono.error(new UserNotFoundException("User not found with ID: " + id)));
		    return user.flatMap(u ->
		        ServerResponse.ok().bodyValue(u)
		    ).onErrorResume(UserNotFoundException.class, e -> 
		        ServerResponse.status(HttpStatus.NOT_FOUND)
		                .bodyValue(Map.of("Error Message", e.getMessage(), "Status", HttpStatus.NOT_FOUND.toString()))
		    );
	}

	public Mono<User> createUser(User user) {
		
		if (user.getId() != 0) {
	        user.setId(0);
	    }
		return userRepository.save(user);
	}

	public Mono<User> updateUser(User user, int id) {
		return userRepository.findById(id)
        .flatMap(user1 -> {
        	user1.setName(user.getName());
        	user1.setAge(user.getAge());
        	user1.setEmail(user.getEmail());
        	user1.setCountry(user.getCountry());
            return userRepository.save(user1);
        })
        .switchIfEmpty(Mono.error(new UserNotFoundException("User not found with ID:"+id)));
	}

	public Mono<Void> deleteUserById(int id) {
		return userRepository.findById(id)
				.switchIfEmpty(Mono.error(new UserNotFoundException("User not found with ID: " + id)))
	            .flatMap(user -> userRepository.deleteById(id));
	}
	
	

}
