package com.lcwd.user.service.UserService;

import com.lcwd.user.service.UserService.entities.Rating;
import com.lcwd.user.service.UserService.external.services.RatingService;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest

class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating(){
//
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is created using fiegn client").build();
//		ResponseEntity<Rating> response = ratingService.createRating(rating);
//		response.getStatusCode();
//		response.getBody();
//		System.out.printf("new Rating createddddddd"+response);
//
//	}

}
