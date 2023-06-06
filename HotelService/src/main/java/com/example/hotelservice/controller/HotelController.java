package com.example.hotelservice.controller;

import com.example.hotelservice.dto.HotelRequestDto;
import com.example.hotelservice.entities.Hotel;
import com.example.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/savehotel")
    public ResponseEntity<HotelRequestDto> createHotel(@RequestBody HotelRequestDto hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }
    @GetMapping("/getById/{hotelId}")
    public ResponseEntity<Hotel> createHotel(@PathVariable("hotelId") String hotelId){
        return ResponseEntity.ok(hotelService.get(hotelId));

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<HotelRequestDto>>getAll(){
        return  ResponseEntity.ok(hotelService.getAll());

    }
}
