package com.example.ratingsystem.service;

import com.example.ratingsystem.dto.RatingResponse;
import com.example.ratingsystem.entities.Rating;

import java.util.List;

public interface RatingService {
    RatingResponse create(Rating rating);
    List<RatingResponse> getRatings();
    List<RatingResponse> getRatingByUserId(String userId);
    List<RatingResponse> getRatingByHotelId(String hotelId);


}
