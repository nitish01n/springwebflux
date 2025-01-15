package com.spring.demo.spring_web_flux.exceptionhandler;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String string) {
		super(string);
	}

}
