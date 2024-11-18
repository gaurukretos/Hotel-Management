package com.lcwd.user.service.UserService.controller;

import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    //create user
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
       User users =  userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(users);

    }

    int retryCount = 1;

    //get user
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotel", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public  ResponseEntity<User> getUser(@PathVariable  String userId){
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        User users = userService.getUserById(userId);
        return ResponseEntity.ok(users);
    }

    //create rating fallback method for circuit breaker

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        //logger.info("Fallback is executed because service is down : "+ ex.getMessage());

       User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This is user is dummy")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Get ALl Users
@GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> users =  userService.getAllUser();
       return ResponseEntity.ok(users);
    }
}
