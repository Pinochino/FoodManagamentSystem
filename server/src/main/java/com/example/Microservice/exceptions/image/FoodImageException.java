package com.example.Microservice.exceptions.image;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FoodImageException {

    Integer status;
    String message;
    Timestamp timestamp;

    public FoodImageException(Integer status, String message, Timestamp timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
