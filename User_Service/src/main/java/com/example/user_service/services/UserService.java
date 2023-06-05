package com.example.user_service.services;

import com.example.user_service.dto.UserDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.entities.User;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto user);

    List<UserResponseDto> getAllUser();
    User getUser(String userId);
}
