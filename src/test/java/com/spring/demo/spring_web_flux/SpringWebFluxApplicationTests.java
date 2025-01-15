package com.spring.demo.spring_web_flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spring.demo.spring_web_flux.controller.UserController;
import com.spring.demo.spring_web_flux.model.User;
import com.spring.demo.spring_web_flux.service.UserService;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebFluxTest(UserController.class)
class SpringWebFluxApplicationTests {
	    
	    @Autowired
        private UserController userController;
	    
	    @Autowired
	    private WebTestClient webTestClient;
	    
	    private User user;
	    
	    @Mock
	    private UserService service;
	    
	    @BeforeEach
	    public void setUp() {
	        user = new User();
	        user.setId(1);
	        user.setName("John Doe");
	        user.setAge(26);
	        user.setEmail("JohnD@gmail.com");
	        user.setCountry("India");
	    }

	    @Test
	    public void addUserTest(){
	    	
	    	when(service.createUser(any(User.class)))
            .thenReturn(Mono.just(user));

        
        Mono<User> result = userController.addUser(user);

        StepVerifier.create(result)
            .expectNext(user)
            .verifyComplete(); 
		    }
	    
	        @Test
	        public void modifyUserTest() {
	            when(service.updateUser(any(User.class), any(Integer.class)))
	                .thenReturn(Mono.just(user));
	            webTestClient.put()
	                    .uri("/users/{id}", 1) 
	                    .bodyValue(user)
	                    .exchange()
	                    .expectStatus().isOk()
	                    .expectBody(User.class)
	                    .isEqualTo(user);
	        }
}
