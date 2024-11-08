package com.example.Microservice.exceptions.food;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor

public class FoodException extends RuntimeException {

    Integer status;
    String message;
    Timestamp timestamp;

    public FoodException(Integer status, String message, Timestamp timestamp) {
        super(message);
        this.status = status;
        this.timestamp = timestamp;
    }
}
