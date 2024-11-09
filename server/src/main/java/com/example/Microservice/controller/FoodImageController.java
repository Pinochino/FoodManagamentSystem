package com.example.Microservice.controller;

import com.example.Microservice.service.image.FoodImageService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:4200")
public class FoodImageController {

    FoodImageService foodImageService;

    @Autowired
    public FoodImageController(FoodImageService foodImageService) {
        this.foodImageService = foodImageService;
    }
}
