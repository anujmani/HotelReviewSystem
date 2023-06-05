package com.example.ratingsystem.controller;

import com.example.ratingsystem.dto.RatingResponse;
import com.example.ratingsystem.entities.Rating;
import com.example.ratingsystem.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @PostMapping("/saveRatings")
    public ResponseEntity<RatingResponse> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }
    @GetMapping("/getAllRatings")
    public ResponseEntity<List<RatingResponse>> getRatings(){
        return ResponseEntity.ok(ratingService.getRatings());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingResponse>> getRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
    @GetMapping("/hotels/{hotelid}")
    public ResponseEntity<List<RatingResponse>> getRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}
