package com.example.user_service.feignclient;

import com.example.user_service.entities.Hotel;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelFeign {
    @GetMapping("/getById/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
