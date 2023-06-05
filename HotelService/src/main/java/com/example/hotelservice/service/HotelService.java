package com.example.hotelservice.service;

import com.example.hotelservice.dto.HotelRequestDto;
import com.example.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {
    HotelRequestDto create(HotelRequestDto hotel);
    List<HotelRequestDto> getAll();
    Hotel get(String id);
}
