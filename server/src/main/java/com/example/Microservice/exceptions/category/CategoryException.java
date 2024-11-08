package com.example.Microservice.exceptions.category;

import java.sql.Timestamp;

public class CategoryException {
    Integer status;
    String message;
    Timestamp timestamp;

    public CategoryException(Integer status, String message, Timestamp timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
