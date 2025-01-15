package com.spring.demo.spring_web_flux.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class User {
	
	@Id
	private int id;
	private String name;
	private int age;
	private String email;
	private String country;

}
