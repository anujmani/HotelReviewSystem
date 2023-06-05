package com.example.user_service.controller;

import com.example.user_service.dto.UserDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.entities.User;
import com.example.user_service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    @PostMapping("/saveHotelUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
         UserDto user1= userService.saveUser(user);

         return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBackMethod")
    @Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBackMethod")
    @GetMapping("/getuser/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId){
        User user= userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    public ResponseEntity<User> ratingHotelFallBackMethod(String userId,Exception ex){
        User user=User.builder().email("dummy@gmail.com").name("dummy").about("cannot work as a service is not working").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        List<UserResponseDto> allUser= userService.getAllUser();

        return ResponseEntity.ok(allUser);
    }
}
