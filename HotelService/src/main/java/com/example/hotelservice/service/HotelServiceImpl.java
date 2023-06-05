package com.example.hotelservice.service;

import com.example.hotelservice.dto.HotelRequestDto;
import com.example.hotelservice.entities.Hotel;
import com.example.hotelservice.exception.ResourceNotFoundException;
import com.example.hotelservice.repository.HotelRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class HotelServiceImpl implements HotelService{
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public HotelRequestDto create(HotelRequestDto hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        Hotel hotel1= objectMapper.convertValue(hotel, Hotel.class);

        hotelRepo.save(hotel1);
        return hotel;
    }

    @Override

    public List<HotelRequestDto> getAll() {
        List<Hotel> hotelList = hotelRepo.findAll();

        List<HotelRequestDto> hotelRequestDtos = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            HotelRequestDto hotelDto = new HotelRequestDto();
            hotelDto.setId(hotel.getId());
            hotelDto.setName(hotel.getName());
            hotelDto.setLocation(hotel.getLocation());
            hotelDto.setAbout(hotel.getAbout());
            hotelRequestDtos.add(hotelDto);
        }
        return hotelRequestDtos;

    }

    @Override
    public Hotel get(String id) {
        return hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
    }
}
