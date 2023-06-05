package com.example.user_service.services;

import com.example.user_service.dto.UserDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.entities.Hotel;
import com.example.user_service.entities.Rating;
import com.example.user_service.entities.User;
import com.example.user_service.exception.ResourceNotFoundException;
import com.example.user_service.feignclient.HotelFeign;
import com.example.user_service.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelFeign hotelFeign;
    @Override
    public UserDto saveUser(UserDto user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User user1= new User();
        user1.setUserId(user.getUserId());
        user1.setName(user.getName());
        user1.setAbout(user.getAbout());
        user1.setEmail(user.getEmail());
        userRepo.save(user1);

        return user;
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<User>users= userRepo.findAll();
        List<UserResponseDto> userResponseDtos= new ArrayList<>();
        for(User user: users){
            UserResponseDto dto= new UserResponseDto();
            dto.setUserId(user.getUserId());
            dto.setName(user.getName());
            dto.setAbout(user.getAbout());
            dto.setEmail(user.getEmail());
            Rating[] ratinglist= restTemplate.getForObject("http://RATING-SERVICE/rate/user/"+ user.getUserId(),Rating[].class);
            List<Rating> ratings= Arrays.stream(ratinglist).toList();
            List<Rating> ratings1=ratings.stream().map(rating -> {
                ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/getById/"+rating.getHotelId(), Hotel.class);
                Hotel hotel= forEntity.getBody();
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            dto.setRatings(ratings1);
            userResponseDtos.add(dto);
        }
        return userResponseDtos;
    }

    @Override
    public User getUser(String userId) {
        User user =userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User is not available of id"+userId));

        Rating[] ratinglist= restTemplate.getForObject("http://RATING-SERVICE/rate/user/"+ user.getUserId(),Rating[].class);
        List<Rating> ratings= Arrays.stream(ratinglist).toList();
        List<Rating> ratings1=ratings.stream().map(rating -> {
            rating.setHotel(hotelFeign.getHotel(rating.getHotelId()));
            return rating;
        }).collect(Collectors.toList());
//        for(Rating oneRating: ratings){
//            oneRating.setHotel(restTemplate.getForObject("http://localhost:8081/"+oneRating.getHotelId(), Hotel.class));
//        }
        user.setRatings(ratings1);
        return user;




    }
}
