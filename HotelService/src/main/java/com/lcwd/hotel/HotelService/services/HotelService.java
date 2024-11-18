package com.lcwd.hotel.HotelService.services;

import com.lcwd.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create hotel
    Hotel saveHotel(Hotel hotel);

    //get hotel
    List<Hotel> getAllHotel();

    //get hotel by id
    Hotel getHotelById(String hotelId);
}
