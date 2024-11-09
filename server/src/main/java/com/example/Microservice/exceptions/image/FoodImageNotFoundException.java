package com.example.Microservice.exceptions.image;

public class FoodImageNotFoundException extends RuntimeException{
    public FoodImageNotFoundException(String s) {
        super(s);
    }
}
