package com.example.user_service.dto;

import com.example.user_service.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String userId;
    private String name;
    private String email;
    private String about;
    private List<Rating> ratings= new ArrayList<>();
}
