package com.example.Microservice.exceptions.customer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerException extends RuntimeException{

    Integer status;
    String message;
    Timestamp timestamp;

    public CustomerException(Integer status, String message, Timestamp timestamp) {
        super(message);
        this.status = status;
        this.timestamp = timestamp;
    }
}
