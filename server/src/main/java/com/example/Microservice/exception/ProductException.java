package com.example.Microservice.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor

public class ProductException extends RuntimeException {

    Integer status;
    String message;
    Timestamp timestamp;

    public ProductException(Integer status, String message, Timestamp timestamp) {
        super(message);
        this.status = status;
        this.timestamp = timestamp;
    }
}
