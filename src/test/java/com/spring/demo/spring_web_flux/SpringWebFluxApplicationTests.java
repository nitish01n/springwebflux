package com.spring.demo.spring_web_flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.spring.demo.spring_web_flux.controller.UserController;
import com.spring.demo.spring_web_flux.model.User;
import com.spring.demo.spring_web_flux.service.UserService;
import reactor.core.publisher.Mono;

@SpringBootTest
@WebFluxTest(UserController.class)
class SpringWebFluxApplicationTests {

	    @Autowired
	    private WebTestClient webTestClient;
	    
	    @MockBean
	    private UserService service;

	    @Test
	    public void addUserTest(){
			Mono<User> user=Mono.just(new User(1,"Name1",25,"email","country"));
			when(service.createUser(any(User.class))).thenReturn(user);
			webTestClient.post().uri("/users")
					.body(Mono.just(user),User.class)
					.exchange()
					.expectStatus().isOk();

		}


//	    @Test
//	    public void findAllUsersTest() {
//	        Flux<User> users = Flux.just(
//	                new User(1, "Name1", 25, "email1@example.com", "country1"),
//	                new User(2, "Name2", 30, "email2@example.com", "country2")
//	        );
//
//	        when(service.findAllUsers()).thenReturn(users);
//
//	        Flux<User> responseBody = webTestClient.get().uri("/users")
//	                .exchange()
//	                .expectStatus().isOk()  
//	                .returnResult(User.class)
//	                .getResponseBody();
//
//	        StepVerifier.create(responseBody)
//	                .expectSubscription()
//	                .expectNextMatches(user -> user.getId() == 1 && user.getName().equals("Name1"))
//	                .expectNextMatches(user -> user.getId() == 2 && user.getName().equals("Name2"))
//	                .verifyComplete();
//	    }


}
