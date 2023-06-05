package com.example.ratingsystem.service;

import com.example.ratingsystem.dto.RatingResponse;
import com.example.ratingsystem.entities.Rating;
import com.example.ratingsystem.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{
    @Autowired
    private RatingRepo ratingRepo;
    @Override

    public RatingResponse create(Rating rating) {
        String randomUserId= UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        RatingResponse response= new RatingResponse();
        response.setRating(rating.getRating());
        response.setRemark(rating.getRemark());
        response.setHotelId(rating.getHotelId());
        response.setUserId(rating.getUserId());
        ratingRepo.save(rating);
        return  response;
    }

    @Override
    public List<RatingResponse> getRatings() {
        List<RatingResponse> responses= new ArrayList<>();
        List<Rating>ratings=  ratingRepo.findAll();
        for(Rating rating: ratings){
            RatingResponse rating1= new RatingResponse();

            rating1.setRating(rating.getRating());
            rating1.setRemark(rating.getRemark());
            rating1.setUserId(rating.getUserId());
            rating1.setHotelId(rating.getHotelId());
            responses.add(rating1);
        }
        return responses;
    }

    @Override
    public List<RatingResponse> getRatingByUserId(String userId) {
        List<Rating>ratings= ratingRepo.findByUserId(userId);
        List<RatingResponse> ratesDto= new ArrayList<>();
        for(Rating rating:ratings){
            RatingResponse ratingResponse= new RatingResponse();
            ratingResponse.setUserId(rating.getUserId());
            ratingResponse.setRemark(rating.getRemark());
            ratingResponse.setHotelId(rating.getHotelId());
            ratingResponse.setRating(rating.getRating());
            ratesDto.add(ratingResponse);
        }
        return ratesDto;
    }

    @Override
    public List<RatingResponse> getRatingByHotelId(String hotelId) {
        List<Rating>ratings= ratingRepo.findByHotelId(hotelId);
        List<RatingResponse> ratesDto= new ArrayList<>();
        for(Rating rating:ratings){
            RatingResponse ratingResponse= new RatingResponse();
            ratingResponse.setUserId(rating.getUserId());
            ratingResponse.setRemark(rating.getRemark());
            ratingResponse.setHotelId(rating.getHotelId());
            ratingResponse.setRating(rating.getRating());
            ratesDto.add(ratingResponse);
        }
        return ratesDto;

    }
}
