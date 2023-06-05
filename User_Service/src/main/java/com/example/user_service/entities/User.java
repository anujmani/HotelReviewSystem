package com.example.user_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @Column(name="ID")
    private String userId;
    private String name;
    private String email;
    private String about;
    @Transient
    private List<Rating> ratings= new ArrayList<>();
}
