package com.lcwd.rating.Rating.Service.service;

import com.lcwd.rating.Rating.Service.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all rating
    List<Rating> getAllRatings();

    //get rating by id
    //Rating getRatingById(String ratingId);

    //get rating by userid
    List<Rating> getRatingByUserId(String userId);

    //get rating by hotelid
    List<Rating> getRatingByHotelId(String hotelId);
}
