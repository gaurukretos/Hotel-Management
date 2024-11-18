package com.lcwd.user.service.UserService.impl;

import com.lcwd.user.service.UserService.entities.Hotel;
import com.lcwd.user.service.UserService.entities.Rating;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.external.services.HotelService;
import com.lcwd.user.service.UserService.repositories.UserRepository;
import com.lcwd.user.service.UserService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User With given id is not found on server !! : " + userId));

       Rating[] forObject = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
       logger.info("{}", forObject);

        List<Rating> ratingList = Arrays.stream(forObject).toList();

       List<Rating> ratings = ratingList.stream().map(rating ->{
           //ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
          Hotel hotels = hotelService.getHotel(rating.getHotelId());
          rating.setHotel(hotels);

          return  rating;

       }).collect(Collectors.toList());


        user.setRatings(ratings);
        return  user;
    }

    @Override
    public User deleteByUserId(String userId) {
        return null;
    }

    @Override
    public List<User> updateUser(List<User> user) {
        return List.of();
    }
}
