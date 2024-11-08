package com.example.Microservice.exceptions;

public class AlreadyExistException extends RuntimeException{

    public AlreadyExistException(String message) {
        super(message);
    }
}
