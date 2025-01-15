package com.spring.demo.spring_web_flux.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException userNotFoundException)
	{
		Map<String,String> errorMsg = new HashMap<>();
		errorMsg.put("Error Message: ", userNotFoundException.getMessage());
		errorMsg.put("Status", HttpStatus.NOT_FOUND.toString());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
	}

}
