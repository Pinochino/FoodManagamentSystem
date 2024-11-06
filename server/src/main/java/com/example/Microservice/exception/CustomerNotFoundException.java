package com.example.Microservice.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String s) {
        super(s);
    }
}
