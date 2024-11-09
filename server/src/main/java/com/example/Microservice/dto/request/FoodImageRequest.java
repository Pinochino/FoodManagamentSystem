package com.example.Microservice.dto.request;

import com.example.Microservice.model.Food;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.util.UUID;

@Data
public class FoodImageRequest {

    UUID imageId;


    String imageName;

    String imageType;
    Blob image;

    String downloadUrl;


    Food food;
}
