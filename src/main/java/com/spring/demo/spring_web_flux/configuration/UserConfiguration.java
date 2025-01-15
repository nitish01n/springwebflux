package com.spring.demo.spring_web_flux.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.demo.spring_web_flux.service.UserService;

@Configuration
public class UserConfiguration {
	
	@Autowired
	private UserService userService;
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction()
	{
		return RouterFunctions.route()
				.GET("/users",userService::findAllUsers)
				.GET("/users/{id}",userService::findUserById)
				.build();
	}

}
