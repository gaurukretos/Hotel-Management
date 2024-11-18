package com.lcwd.rating.Rating.Service.impl;

import com.lcwd.rating.Rating.Service.entities.Rating;
import com.lcwd.rating.Rating.Service.repositories.RatingRepository;
import com.lcwd.rating.Rating.Service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Rating createRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

//    @Override
//    public Rating getRatingById(String ratingId) {
//        return ratingRepository.findById(ratingId).orElseThrow(() ->
//                new ResourceNotFoundException("User With given id is not found on server !! : " + userId));;
//    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
